package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;

public class BrowserDriver {

    public static WebDriver driver;

    public static WebDriver getWebDriver() {return  driver;}

    public static  void setWebDriver(String browserName){

        switch (browserName.toLowerCase()){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
    }

    public WebElement getElement(String locType, String locValue) {
       WebElement element = null;

       switch (locType.toLowerCase()){
           case "id":
               element = driver.findElement(By.id(locValue));
               break;

           case "xpath":
               element = driver.findElement(By.xpath(locValue));
               break;
       }
       return  element;
    }

    public static void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);

    }

    public void switchToMainWindow(){
        driver.switchTo().defaultContent();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
}
