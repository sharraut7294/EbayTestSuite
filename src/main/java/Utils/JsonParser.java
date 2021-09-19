package Utils;

import POJO.CardDetails;
import POJO.ErrorMessages;
import POJO.ShippingDetails;
import POJO.SiteAndBrowserDetails;
import Pages.VerifyViaCaptchaPage;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser {

    Logger log = Logger.getLogger(JsonParser.class);

    public ShippingDetails readShippingDetailsFromJSON() throws IOException {
        JSONParser jsonParser = new JSONParser();
        ShippingDetails ship = new ShippingDetails();
        try {
            FileReader reader = new FileReader("src/main/resources/TestData/ShippingDetails.json");
            Object obj = jsonParser.parse(reader);
            JSONObject shippingDetails = (JSONObject) obj;

            ship.firstName= (String)shippingDetails.get("firstName");
            ship.lastName = (String)shippingDetails.get("lastName");
            ship.streetAddress = (String)shippingDetails.get("streetAddress");
            ship.streetAddress2 = (String)shippingDetails.get("streetAddress2");
            ship.city = (String)shippingDetails.get("city");
            ship.state = (String)shippingDetails.get("state");
            ship.zipCode = (String)shippingDetails.get("zipCode");
            ship.email = (String)shippingDetails.get("email");
            ship.phoneNumber = (String)shippingDetails.get("phoneNumber");

            log.info("Parsed all the fields for shipping details object");

        } catch (FileNotFoundException | org.json.simple.parser.ParseException e) {
            log.error("Unable to parse ShippingDetails.json");
            e.printStackTrace();
        }
        return ship;
    }
    public CardDetails readCardDetailsFromJSON() throws IOException {
        JSONParser jsonParser = new JSONParser();
        CardDetails card = new CardDetails();
        try {
            FileReader reader = new FileReader("src/main/resources/TestData/CardDetails.json");
            Object obj = jsonParser.parse(reader);
            JSONObject cardDetails = (JSONObject) obj;

            card.cardNumber= (String)cardDetails.get("cardNumber");
            card.expirationDate = (String)cardDetails.get("expirationDate");
            card.securityCode = (String)cardDetails.get("securityCode");

            log.info("Parsed all the fields for card details object");

        } catch (FileNotFoundException | org.json.simple.parser.ParseException e) {
            log.error("Unable to parse CardDetails.json");
            e.printStackTrace();
        }
        return card;
    }

    public ErrorMessages readErrorMessagesFromJSON() throws IOException {
        JSONParser jsonParser = new JSONParser();
        ErrorMessages errorMessages = new ErrorMessages();
        try {
            FileReader reader = new FileReader("src/main/resources/TestData/ErrorMessages.json");
            Object obj = jsonParser.parse(reader);
            JSONObject error = (JSONObject) obj;

            errorMessages.addShippingDetailsWarning = (String)error.get("addShippingDetailsWarning");
            errorMessages.enterShippingAddressError = (String)error.get("enterShippingAddressError");
            errorMessages.enterPaymentDetailsAgainError = (String)error.get("enterPaymentDetailsAgainError");
            errorMessages.cardNumberError = (String)error.get("cardNumberError");

            log.info("Parsed all the fields for Error Messages object");

        } catch (FileNotFoundException | org.json.simple.parser.ParseException e) {
            log.error("Unable to parse ErrorMessages.json");
            e.printStackTrace();
        }
        return errorMessages;
    }

    public SiteAndBrowserDetails readSiteBrowserDetailsFromJSON() throws IOException {
        JSONParser jsonParser = new JSONParser();
        SiteAndBrowserDetails siteAndBrowserDetails = new SiteAndBrowserDetails();
        try {
            FileReader reader = new FileReader("src/main/resources/TestData/SiteAndBrowserDetails.json");
            Object obj = jsonParser.parse(reader);
            JSONObject site = (JSONObject) obj;

            siteAndBrowserDetails.browserName = (String)site.get("browserName");
            siteAndBrowserDetails.baseUrl = (String)site.get("baseUrl");
            siteAndBrowserDetails.searchProductName = (String)site.get("searchProductName") ;

            log.info("Parsed all the fields for siteAndBrowserDetails object");
        } catch (FileNotFoundException | org.json.simple.parser.ParseException e) {
            log.error("Unable to parse SiteAndBrowserDetails.json");
            e.printStackTrace();
        }
        return siteAndBrowserDetails;
    }
}
