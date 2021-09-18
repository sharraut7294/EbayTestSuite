import Pages.*;
import Utils.BrowserDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFailure {


        @Test
        public void verifyShippingDetailsFailure(){
            HomePage home = new HomePage();
            SearchResultsPage results = new SearchResultsPage();
            ProductInfoPage productInfo = new ProductInfoPage();
            ShoppingCartPage shoppingCart = new ShoppingCartPage();
            VerifyViaCaptchaPage captcha = new VerifyViaCaptchaPage();
            CheckoutPage checkout = new CheckoutPage();

            home.enterProductNameInSearchBar("iphone 12 pro max");
            home.clickOnSearchButton();

            results.selectFirstProduct();

            productInfo.clickOnAddToCartButton();

            shoppingCart.clickOnGoToCheckoutButton();
            shoppingCart.clickOnContinueAsGuestButton();

            if(captcha.isCaptchaPageDisplayed()){
                captcha.verifyYouAreHuman();
            }

            checkout.clickOnConfirmAndPayBtn();

            Assert.assertEquals(checkout.isAddShippingDetailsWarningDisplayed(), true);
            Assert.assertEquals(checkout.getAddShippingDetailsWarningText(), "Add your shipping details and click Done");
            Assert.assertEquals(checkout.isDisabledConfirmPayErrorMsgDisplayed(),true);
            Assert.assertEquals(checkout.getDisabledConfirmPayErrorMsg(), "Enter shipping address");

        }
}
