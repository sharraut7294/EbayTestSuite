package Pages;

import Utils.BrowserDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    @FindBy(xpath = "//button[@data-test-id='CONFIRM_AND_PAY_BUTTON']")
    private WebElement confirmAndPayButton;

    @FindBy(xpath = "//section[@class='page-notice page-notice--attention']//div[1]")
    private WebElement addShippingDetailsWarning;

    @FindBy(id = "cta-disabled-message")
    private WebElement disabledButtonMsg;

    public CheckoutPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

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
}
