import POJO.CardDetails;
import POJO.ErrorMessages;
import POJO.ShippingDetails;
import POJO.SiteAndBrowserDetails;
import Pages.*;
import Utils.BrowserControl;
import Utils.BrowserDriver;
import Utils.Helper;
import Utils.JsonParser;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class CheckoutPageScripts {

    Logger log = Logger.getLogger(CheckoutPageScripts.class);

    @Test (priority = 0, description="User has added product in cart and navigated to check out page as a guest user")
    @Description("Test Description: Search for a product, Select product, Add to Cart, Navigate to Checkout page")
    public void addProductToCartAndGoToCheckout() throws IOException {
        JsonParser parser = new JsonParser();
        SiteAndBrowserDetails siteAndBrowserDetails = parser.readSiteBrowserDetailsFromJSON();

        HomePage home = new HomePage();
        SearchResultsPage results = new SearchResultsPage();
        ProductInfoPage productInfo = new ProductInfoPage();
        ShoppingCartPage shoppingCart = new ShoppingCartPage();
        VerifyViaCaptchaPage captcha = new VerifyViaCaptchaPage();

        home.enterProductNameInSearchBar(siteAndBrowserDetails.searchProductName);
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
    }


    @Test (priority = 1, description="Verify shipping details failure reason when user clicks on Confirm & Pay button without entering any shipping details")
    @Description("Test Description: Click on confirm & pay button on checkout page without entering any shipping details")
    @Severity(SeverityLevel.NORMAL)
    public void verifyShippingDetailsFailure() throws IOException {
        CheckoutPage checkout = new CheckoutPage();
        checkout.clickOnConfirmAndPayBtn();
        log.info("User has directly clicked on Confirm & Pay button");

        JsonParser parser = new JsonParser();
        ErrorMessages errors = parser.readErrorMessagesFromJSON();

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
        JsonParser parser = new JsonParser();
        Helper helper = new Helper();

        ShippingDetails shippingDetails = parser.readShippingDetailsFromJSON();
        CardDetails cardDetails = parser.readCardDetailsFromJSON();
        ErrorMessages errors = parser.readErrorMessagesFromJSON();

        helper.addShippingDetails(shippingDetails);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //To Do: Add implicit wait here instead of Thread.sleep();
        checkout.selectAddNewCardOption();
        log.info("User has selected Pay with card option");

        helper.addCardDetails(cardDetails);
        log.info("User has added dummy card details");

        Assert.assertEquals(checkout.isEnterPaymentDetailsAgainErrorDisplayed(), true);
        Assert.assertEquals(checkout.getEnterPaymentDetailsAgainErrorText(),errors.enterPaymentDetailsAgainError);
        Assert.assertEquals(checkout.isCardNumberErrorDisplayed(), true);
        Assert.assertEquals(checkout.getCardNumberErrorText(), errors.cardNumberError);
        log.info("User has verified errors related to card details");
    }
}
