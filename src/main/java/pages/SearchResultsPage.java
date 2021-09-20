package pages;

import utils.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

    @FindBy(xpath = "//h3[@class='s-item__title']")
    private WebElement firstProduct;

    public SearchResultsPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    @Step("User has selected first product from product list")
    public void selectFirstProduct(){
        this.firstProduct.click();
    }
}
