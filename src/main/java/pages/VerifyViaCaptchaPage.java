package pages;

import utils.BrowserDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyViaCaptchaPage {

    Logger log = Logger.getLogger(VerifyViaCaptchaPage.class);

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
            log.info("Captcha page is not displayed");
            return false;
        }
    }

    public void verifyYouAreHuman(){
        BrowserDriver.switchToFrame(captchaIframe);
        this.captchaCheckBox.click();
        log.error("Selenium can click on captcha checkbox but can't select correct images");
    }


}
