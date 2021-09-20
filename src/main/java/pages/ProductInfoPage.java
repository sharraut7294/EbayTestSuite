package pages;

import utils.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {

    @FindBy(id = "isCartBtn_btn")
    private WebElement addToCartButton;

    public  ProductInfoPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    @Step("User has clicked on Add to cart button")
    public void clickOnAddToCartButton(){
        this.addToCartButton.click();
    }
}
