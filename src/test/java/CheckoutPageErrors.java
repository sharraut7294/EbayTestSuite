import Pages.*;
import Utils.BrowserControl;
import Utils.BrowserDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class CheckoutPageErrors {

    @BeforeMethod
    public void launchBrowser(){
        BrowserControl.startBrowser("chrome");
        BrowserControl.accessUrl("https://www.ebay.com/");
        BrowserControl.maximizeBrowser();

        HomePage home = new HomePage();
        SearchResultsPage results = new SearchResultsPage();
        ProductInfoPage productInfo = new ProductInfoPage();
        ShoppingCartPage shoppingCart = new ShoppingCartPage();
        VerifyViaCaptchaPage captcha = new VerifyViaCaptchaPage();

        home.enterProductNameInSearchBar("iphone 12 pro max");
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
    public void verifyShippingDetailsFailure()
    {
        CheckoutPage checkout = new CheckoutPage();
        checkout.clickOnConfirmAndPayBtn();

        Assert.assertEquals(checkout.isAddShippingDetailsWarningDisplayed(), true);
        Assert.assertEquals(checkout.getAddShippingDetailsWarningText(), "Add your shipping details and click Done");
        Assert.assertEquals(checkout.isDisabledConfirmPayErrorMsgDisplayed(),true);
        Assert.assertEquals(checkout.getDisabledConfirmPayErrorMsg(), "Enter shipping address");
    }

    @Test
    public void verifyCardNotSupportedFailure(){
        CheckoutPage checkout = new CheckoutPage();
        addShippingDetails("Test", "Ebayuser", "3133 E Main St", "Mohegan Lake","New york","New York","10547","sharraut7294@gmail.com", "6465554087");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Add implicit wait here
        checkout.selectAddNewCardOption();
        addCardDetails("123456789","0119","1234");

        Assert.assertEquals(checkout.isEnterPaymentDetailsAgainErrorDisplayed(), true);
        Assert.assertEquals(checkout.getEnterPaymentDetailsAgainErrorText(),"Looks like something wasn't correct. Please enter the payment details again.");
        Assert.assertEquals(checkout.isCardNumberErrorDisplayed(), true);
        Assert.assertEquals(checkout.getCardNumberErrorText(), "We don't support this card. Please use a different one.");
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
