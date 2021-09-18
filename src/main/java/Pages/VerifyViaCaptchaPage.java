package Pages;

import Utils.BrowserDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class VerifyViaCaptchaPage {

    @FindBy(xpath = "//div[@role='checkbox']")
    private WebElement captchaCheckBox;

    @FindBy(xpath = "//h1[text()='Please verify yourself to continue']")
    private WebElement pleaseVerifyYourselfText;

    @FindBy(xpath = "//iframe[@frameborder='0']")
    private WebElement captchaIframe;

    public VerifyViaCaptchaPage(){
        PageFactory.initElements(BrowserDriver.getWebDriver(), this);
    }

    public boolean isCaptchaPageDisplayed(){
        try {
            return this.pleaseVerifyYourselfText.isDisplayed();
        } catch (Exception e) {
            System.out.println("Verify captcha page not displayed");
            return false;
        }
    }

    public void verifyYouAreHuman(){
        BrowserDriver.switchToFrame(captchaIframe);
        this.captchaCheckBox.click();
    }


}
