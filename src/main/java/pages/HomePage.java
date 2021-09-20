package pages;

import utils.BrowserDriver;
import io.qameta.allure.Step;
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

    @Step("User has entered product name in search bar")
    public void enterProductNameInSearchBar(String productName){
        this.searchInputBar.sendKeys(productName);
    }

    @Step("User has clicked on search button")
    public void clickOnSearchButton(){
        this.searchButton.click();
    }


}
