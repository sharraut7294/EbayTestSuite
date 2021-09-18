package Utils;

import java.util.concurrent.TimeUnit;

public class BrowserControl {

    public static void startBrowser(String browserName){
        BrowserDriver.setWebDriver(browserName);
    }

    public static void accessUrl(String url){
        BrowserDriver.getWebDriver().get(url);
        BrowserDriver.getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void  maximizeBrowser(){
        BrowserDriver.getWebDriver().manage().window().maximize();
    }

    public static void  closeBrowser(){
        BrowserDriver.getWebDriver().close();
    }
    public static void  quitBrowser(){
        BrowserDriver.getWebDriver().quit();
    }

}
