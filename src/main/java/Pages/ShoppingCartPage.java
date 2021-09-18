package Pages;

import Utils.BrowserDriver;
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

    public void clickOnGoToCheckoutButton(){
        this.goToCheckoutButton.click();
    }

    public void clickOnContinueAsGuestButton(){
        this.continueAsGuestButton.click();
    }
}
