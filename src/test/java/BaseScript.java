import POJO.SiteAndBrowserDetails;
import Utils.BrowserControl;
import Utils.BrowserDriver;
import Utils.JsonParser;
import org.testng.annotations.*;

import java.io.IOException;


public class BaseScript {

    @BeforeSuite
    public void launchBrowser() throws IOException {
        JsonParser parser = new JsonParser();
        SiteAndBrowserDetails siteAndBrowserDetails = parser.readSiteBrowserDetailsFromJSON();

        BrowserControl.startBrowser(siteAndBrowserDetails.browserName);
        BrowserControl.accessUrl(siteAndBrowserDetails.baseUrl);
        BrowserControl.maximizeBrowser();
    }

    @AfterSuite
    public void closeBrowser(){
        BrowserControl.closeBrowser();
    }
}
