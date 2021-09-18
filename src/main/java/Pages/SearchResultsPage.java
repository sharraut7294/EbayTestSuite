package Pages;

import Utils.BrowserDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    @FindBy(xpath = "//h3[@class='s-item__title']")
    private WebElement firstProduct;

    public SearchResultsPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    public void selectFirstProduct(){
        this.firstProduct.click();
    }
}
