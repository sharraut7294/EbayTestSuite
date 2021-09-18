package Pages;

import Utils.BrowserDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {

    @FindBy(id = "isCartBtn_btn")
    private WebElement addToCartButton;

    public  ProductInfoPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    public void clickOnAddToCartButton(){
        this.addToCartButton.click();
    }
}
