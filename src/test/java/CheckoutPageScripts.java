import io.qameta.allure.internal.shadowed.jackson.annotation.JsonTypeInfo;
import pojo.*;
import pages.*;
import utils.JsonParser;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.Thread;

public class CheckoutPageScripts {

    Logger log = Logger.getLogger(CheckoutPageScripts.class);

    JsonParser parser = new JsonParser();
    SiteAndBrowserDetails siteAndBrowserDetails;
    ShippingDetails shippingDetails;
    CardDetails cardDetails;
    ErrorMessages errors;
    ProductDetails productDetails;

    public CheckoutPageScripts() throws IOException {
        parser = new JsonParser();
        siteAndBrowserDetails = parser.readSiteBrowserDetailsFromJSON();
        shippingDetails = parser.readShippingDetailsFromJSON();
        cardDetails = parser.readCardDetailsFromJSON();
        errors = parser.readErrorMessagesFromJSON();
        productDetails = parser.readProductDetailsFromJSON();
    }

    @Test (priority = 0, description="User has added product in cart and navigated to check out page as a guest user")
    @Description("Test Description: Search for a product, Select product, Add to Cart, Navigate to Checkout page")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCartAndGoToCheckout() throws IOException {
        HomePage home = new HomePage();
        SearchResultsPage results = new SearchResultsPage();
        ProductInfoPage productInfo = new ProductInfoPage();
        ShoppingCartPage shoppingCart = new ShoppingCartPage();
        VerifyViaCaptchaPage captcha = new VerifyViaCaptchaPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        home.enterProductNameInSearchBar(productDetails.searchProductName);
        home.clickOnSearchButton();

        results.selectFirstProduct();
        productInfo.clickOnAddToCartButton();
        log.info("Product has been added to cart");

        shoppingCart.clickOnGoToCheckoutButton();
        shoppingCart.clickOnContinueAsGuestButton();
        log.info("User has navigated to checkout page as a guest user");

        if(captcha.isCaptchaPageDisplayed()){
            log.info("Test case will fail as captcha can't get verified through automated software");
            captcha.verifyYouAreHuman();

        }
        Assert.assertEquals(checkoutPage.isCheckoutHeadingDisplayed(),true);
    }


    @Test (priority = 1, description="Verify shipping details failure reason when user clicks on Confirm & Pay button without entering any shipping details")
    @Description("Test Description: Click on confirm & pay button on checkout page without entering any shipping details")
    @Severity(SeverityLevel.NORMAL)
    public void verifyShippingDetailsFailure() throws IOException {
        CheckoutPage checkout = new CheckoutPage();
        checkout.clickOnConfirmAndPayBtn();
        log.info("User has directly clicked on Confirm & Pay button");

        Assert.assertEquals(checkout.isAddShippingDetailsWarningDisplayed(), true);
        Assert.assertEquals(checkout.getAddShippingDetailsWarningText(), errors.addShippingDetailsWarning);
        Assert.assertEquals(checkout.isDisabledConfirmPayErrorMsgDisplayed(),true);
        Assert.assertEquals(checkout.getDisabledConfirmPayErrorMsg(), errors.enterShippingAddressError);
        log.info("User has verified errors related to shipping details");
    }

    @Test (priority = 2, description="Verify payment details failure reason when user enters dummy values for card number and security code fields")
    @Description("Test Description: Enter dummy values in Card number and Security code fields in pay with card payment method and click on Done button")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCardNotSupportedFailure() throws IOException {
        CheckoutPage checkout = new CheckoutPage();

        checkout.addShippingDetails(shippingDetails);

        log.info("Calling thread.sleep as implicit and explicit wait doesn't seem to work for above method");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkout.selectAddNewCardOption();
        log.info("User has selected Pay with card option");

        checkout.addCardDetails(cardDetails);
        log.info("User has added dummy card details");

        Assert.assertEquals(checkout.isEnterPaymentDetailsAgainErrorDisplayed(), true);
        Assert.assertEquals(checkout.getEnterPaymentDetailsAgainErrorText(),errors.enterPaymentDetailsAgainError);
        Assert.assertEquals(checkout.isCardNumberErrorDisplayed(), true);
        Assert.assertEquals(checkout.getCardNumberErrorText(), errors.cardNumberError);
        log.info("User has verified errors related to card details");
    }
}
