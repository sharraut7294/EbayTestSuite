package utils;

import pojo.CardDetails;
import pojo.ShippingDetails;
import pages.CheckoutPage;
import io.qameta.allure.Step;

public class Helper {

    @Step("User has added shipping details")
    public void addShippingDetails(ShippingDetails shippingDetails){
        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(shippingDetails.firstName);
        checkout.enterLastName(shippingDetails.lastName);
        checkout.enterStreetAddress(shippingDetails.streetAddress);
        checkout.enterStreetAddress2(shippingDetails.streetAddress2);
        checkout.enterCity(shippingDetails.city);
        checkout.selectState(shippingDetails.state);
        checkout.enterZipCode(shippingDetails.zipCode);
        checkout.enterEmailId(shippingDetails.email);
        checkout.confirmEmailId(shippingDetails.email);
        checkout.enterPhoneNumber(shippingDetails.phoneNumber);
        checkout.submitAddressDetails();
    }

    @Step("User has added card details")
    public void addCardDetails(CardDetails cardDetails){
        CheckoutPage checkout = new CheckoutPage();
        checkout.enterCardNumber(cardDetails.cardNumber);
        checkout.enterCardExpiryDate(cardDetails.expirationDate);
        checkout.enterSecurityCode(cardDetails.securityCode);
        checkout.submitCardDetails();
    }
}
