package Pages;

import Utils.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    @FindBy(xpath = "//button[@data-test-id='cta-top']")
    private WebElement goToCheckoutButton;

    @FindBy(id = "gxo-btn")
    private  WebElement continueAsGuestButton;

    public ShoppingCartPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    @Step("User has clicked on Go to checkout button")
    public void clickOnGoToCheckoutButton(){
        this.goToCheckoutButton.click();
    }

    @Step("User has clicked on Continue as Guest")
    public void clickOnContinueAsGuestButton(){
        this.continueAsGuestButton.click();
    }
}
