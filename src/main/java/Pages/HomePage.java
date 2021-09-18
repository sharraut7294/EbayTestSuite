package Pages;

import Utils.BrowserDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//div[@id='gh-ac-box2']//input[1]")
    private WebElement searchInputBar;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    public HomePage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    public void enterProductNameInSearchBar(String productName){
        searchInputBar.sendKeys(productName);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }


}
