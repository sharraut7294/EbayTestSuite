import POJO.CardDetails;
import POJO.ErrorMessages;
import POJO.ShippingDetails;
import POJO.SiteAndBrowserDetails;
import Pages.*;
import Utils.BrowserControl;
import Utils.BrowserDriver;
import Utils.JsonParser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class CheckoutPageErrors {

    @BeforeMethod
    public void launchBrowser() throws IOException {
        JsonParser parser = new JsonParser();
        SiteAndBrowserDetails siteAndBrowserDetails = parser.readSiteBrowserDetailsFromJSON();

        BrowserControl.startBrowser(siteAndBrowserDetails.browserName);
        BrowserControl.accessUrl(siteAndBrowserDetails.baseUrl);
        BrowserControl.maximizeBrowser();

        HomePage home = new HomePage();
        SearchResultsPage results = new SearchResultsPage();
        ProductInfoPage productInfo = new ProductInfoPage();
        ShoppingCartPage shoppingCart = new ShoppingCartPage();
        VerifyViaCaptchaPage captcha = new VerifyViaCaptchaPage();

        home.enterProductNameInSearchBar(siteAndBrowserDetails.searchProductName);
        home.clickOnSearchButton();

        results.selectFirstProduct();

        productInfo.clickOnAddToCartButton();

        shoppingCart.clickOnGoToCheckoutButton();
        shoppingCart.clickOnContinueAsGuestButton();

        if(captcha.isCaptchaPageDisplayed()){
            captcha.verifyYouAreHuman();
        }
    }

    @AfterMethod
    public void closeBrowser(){
        BrowserControl.closeBrowser();
    }

    @Test
    public void verifyShippingDetailsFailure() throws IOException {
        CheckoutPage checkout = new CheckoutPage();
        checkout.clickOnConfirmAndPayBtn();

        JsonParser parser = new JsonParser();
        ErrorMessages errors = parser.readErrorMessagesFromJSON();

        Assert.assertEquals(checkout.isAddShippingDetailsWarningDisplayed(), true);
        Assert.assertEquals(checkout.getAddShippingDetailsWarningText(), errors.addShippingDetailsWarning);
        Assert.assertEquals(checkout.isDisabledConfirmPayErrorMsgDisplayed(),true);
        Assert.assertEquals(checkout.getDisabledConfirmPayErrorMsg(), errors.enterShippingAddressError);
    }

    @Test
    public void verifyCardNotSupportedFailure() throws IOException {
        CheckoutPage checkout = new CheckoutPage();
        JsonParser parser = new JsonParser();

        ShippingDetails shippingDetails = parser.readShippingDetailsFromJSON();
        CardDetails cardDetails = parser.readCardDetailsFromJSON();
        ErrorMessages errors = parser.readErrorMessagesFromJSON();

        addShippingDetails(shippingDetails.firstName, shippingDetails.lastName, shippingDetails.streetAddress, shippingDetails.streetAddress2, shippingDetails.city, shippingDetails.state,shippingDetails.zipCode, shippingDetails.email, shippingDetails.phoneNumber);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //To Do: Add implicit wait here
        checkout.selectAddNewCardOption();
        addCardDetails(cardDetails.cardNumber,cardDetails.expirationDate,cardDetails.securityCode);

        Assert.assertEquals(checkout.isEnterPaymentDetailsAgainErrorDisplayed(), true);
        Assert.assertEquals(checkout.getEnterPaymentDetailsAgainErrorText(),errors.enterPaymentDetailsAgainError);
        Assert.assertEquals(checkout.isCardNumberErrorDisplayed(), true);
        Assert.assertEquals(checkout.getCardNumberErrorText(), errors.cardNumberError);
    }

    //Pass json object with all details
    public void addShippingDetails(String firstName,String lastName, String streetAddress,String streetAddress2,String city, String state,String zipCode, String email, String phoneNumber){
        CheckoutPage checkout = new CheckoutPage();
        //checkout.selectCountry(country);
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterStreetAddress(streetAddress);
        checkout.enterStreetAddress2(streetAddress2);
        checkout.enterCity(city);
        checkout.selectState(state);
        checkout.enterZipCode(zipCode);
        checkout.enterEmailId(email);
        checkout.confirmEmailId(email);
        checkout.enterPhoneNumber(phoneNumber);
        checkout.submitAddressDetails();
    }

    public void addCardDetails(String cardNumber, String expirationDate, String securityCode){
        CheckoutPage checkout = new CheckoutPage();
        checkout.enterCardNumber(cardNumber);
        checkout.enterCardExpiryDate(expirationDate);
        checkout.enterSecurityCode(securityCode);
        checkout.submitCardDetails();
    }
}
