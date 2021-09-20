import pojo.SiteAndBrowserDetails;
import utils.BrowserControl;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import utils.JsonParser;

import java.io.IOException;


public class BaseScript {

    Logger log = Logger.getLogger(BaseScript.class);

    @BeforeSuite
    public void launchBrowser() throws IOException {
        JsonParser parser = new JsonParser();
        SiteAndBrowserDetails siteAndBrowserDetails = parser.readSiteBrowserDetailsFromJSON();
        BrowserControl.startBrowser(siteAndBrowserDetails.browserName);
        log.info("Browser Started");
        BrowserControl.accessUrl(siteAndBrowserDetails.baseUrl);
        log.info("Application launched");
        BrowserControl.maximizeBrowser();
    }

    @AfterSuite
    public void closeBrowser(){
        BrowserControl.closeBrowser();
    }
}
