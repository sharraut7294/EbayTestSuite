import Pages.HomePage;
import org.testng.annotations.Test;

public class CheckoutFailure {

        @Test
        public void searchForProduct(){
            HomePage home = new HomePage();
            home.enterProductNameInSearchBar("iphone 12 pro max");
            home.clickOnSearchButton();
        }
}
