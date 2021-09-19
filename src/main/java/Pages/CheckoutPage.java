package Pages;

import Utils.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class CheckoutPage {

    @FindBy(xpath = "//button[@data-test-id='CONFIRM_AND_PAY_BUTTON']")
    private WebElement confirmAndPayButton;

    @FindBy(xpath = "//section[@class='page-notice page-notice--attention']//div[1]")
    private WebElement addShippingDetailsWarning;

    @FindBy(id = "cta-disabled-message")
    private WebElement disabledButtonMsg;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "addressLine1")
    private WebElement streetAddress;

    @FindBy(id = "addressLine2")
    private WebElement streetAddress2;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "stateOrProvince")
    private WebElement stateDropdown;

    @FindBy(id = "postalCode")
    private WebElement zipCode;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "emailConfirm")
    private WebElement confirmEmail;

    @FindBy(id = "phoneNumber")
    private  WebElement phoneNumber;

    @FindBy(xpath = "//button[@data-test-id='ADD_ADDRESS_SUBMIT']")
    private  WebElement shippingAddressSubmitBtn;

    @FindBy(id = "selectable-render-summary1")
    private WebElement addNewCardRadioBtn;

    @FindBy(id = "cardNumber")
    private  WebElement cardNumber;

    @FindBy(id = "cardExpiryDate")
    private WebElement cardExpiryDate;

    @FindBy(id = "securityCode")
    private WebElement securityCode;

    @FindBy(xpath = "//button[@data-action-name='ADD_CARD']")
    private  WebElement submitCardDetails;

    @FindBy(id = "cardNumber-error")
    private  WebElement cardNumberError;

    @FindBy(xpath = "//form[@id='credit-card-details']/section[1]/div[1]/div[1]/span[2]/p[1]/span[1]/span[1]")
    private WebElement enterPaymentDetailsAgain;

    @FindBy(id = "country")
    private WebElement country;

    public CheckoutPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    @Step("User has clicked on Confirm and Pay button")
    public void clickOnConfirmAndPayBtn(){
        this.confirmAndPayButton.click();
    }

    public boolean isAddShippingDetailsWarningDisplayed(){
        return this.addShippingDetailsWarning.isDisplayed();
    }

    public String getAddShippingDetailsWarningText(){
        return this.addShippingDetailsWarning.getText();
    }

    public boolean isDisabledConfirmPayErrorMsgDisplayed(){
        return this.disabledButtonMsg.isDisplayed();
    }

    public String getDisabledConfirmPayErrorMsg(){
        return this.disabledButtonMsg.getText();
    }

    public void enterFirstName(String firstName){
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        this.lastName.sendKeys(lastName);
    }

    public void enterStreetAddress(String streetAddress){
        this.streetAddress.sendKeys(streetAddress);
    }

    public void enterStreetAddress2(String streetAddress){
        this.streetAddress2.sendKeys(streetAddress);
    }

    public void enterCity(String cityName){
        this.city.sendKeys(cityName);
    }

    public void selectState(String stateName){
        Select select = new Select(this.stateDropdown);
        select.selectByVisibleText(stateName);
    }

    public void enterZipCode(String zipCode){
        this.zipCode.sendKeys(zipCode);
    }

    public void enterEmailId(String emailId){
        this.email.sendKeys(emailId);
    }

    public void confirmEmailId(String emailId){
        this.confirmEmail.sendKeys(emailId);
    }

    public void enterPhoneNumber(String number){
        this.phoneNumber.sendKeys(number);
    }

    public void submitAddressDetails(){
        this.shippingAddressSubmitBtn.click();
    }

    public void selectAddNewCardOption(){
        this.addNewCardRadioBtn.click();
    }

    public void enterCardNumber(String cardNumber){
        this.cardNumber.sendKeys(cardNumber);
    }

    public void enterCardExpiryDate(String cardExpiry){
        this.cardExpiryDate.sendKeys(cardExpiry);
    }

    public void enterSecurityCode(String securityCode){
        this.securityCode.sendKeys(securityCode);
    }

    public void submitCardDetails(){
        this.submitCardDetails.click();
    }

    public boolean isCardNumberErrorDisplayed(){
        return this.cardNumberError.isDisplayed();
    }

    public String getCardNumberErrorText(){
        return this.cardNumberError.getText();
    }

    public boolean isEnterPaymentDetailsAgainErrorDisplayed(){
        return this.enterPaymentDetailsAgain.isDisplayed();
    }

    public String getEnterPaymentDetailsAgainErrorText(){
        return this.enterPaymentDetailsAgain.getText();
    }

    public void selectCountry(String countryName){
        Select select = new Select(this.country);
        select.selectByVisibleText(countryName);
        BrowserDriver.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
    }



}
