package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CartPage;
import pageObject.InventoryPage;
import pageObject.LoginPage;
import pageObject.CheckoutCompletePage;
import pageObject.CheckoutOverviewPage;
import pageObject.CheckoutYourInformationPage;

import testBase.BaseClass;

public class TC001_VerifyCheckoutFlow extends BaseClass {

	@Test
	public void verifyCompleteCheckoutFlow() throws Exception {
		try {
			logger.info("** TC001_VerifyCheckoutFlow Started **");

			// Step 1: Login Page
			LoginPage loginPage = new LoginPage(driver);
			loginPage.enterCredentialsAndLogin(p.getProperty("username"), p.getProperty("password"));
			logger.info("Credentials entered");

			// Step 2: Inventory Page
			InventoryPage inventoryPage = new InventoryPage(driver);
			// Check if login is successful and inventory page is visible
			boolean status = inventoryPage.checkInventoryPageIsDisplayed();
			try {
				Assert.assertTrue(status);
				inventoryPage.addProductsToCart(2); // To add two products to cart

				logger.info("Products added in cart");
			} catch (Exception e) {
				Assert.fail();
			}
			inventoryPage.clickOnCart();

			logger.info("Clicked on cart");

			// Step 3: Cart Page
			CartPage cartPage = new CartPage(driver);
			cartPage.clickOnCheckout();

			logger.info("Clicked on checkout");

			// Step 4: Checkout Your Information Page
			CheckoutYourInformationPage informationPage = new CheckoutYourInformationPage(driver);
			informationPage.setFirstName(randomString());
			informationPage.setLastName(randomString());
			informationPage.setPostalcode(randomAlphaNumeric());

			logger.info("Information entered on Checkout Your Information Page");

			informationPage.clickOnContinue();

			logger.info("Clicked on Continue");

			// Step 5: Checkout Overview Page
			CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);

			double expectedTotalPrice = cartPage.calculateTotalPrice();
			String taxRate = p.getProperty("taxRate"); // Get tax rate from config.properties file
			expectedTotalPrice = overviewPage.calculateTotalPriceAfterTax(expectedTotalPrice,Double.parseDouble(taxRate)); // Expected total price
			
			double actualTotalPrice = overviewPage.finalPrice(); // Get final price from overview page
			Assert.assertEquals(actualTotalPrice, expectedTotalPrice, "Total price is not as expected");
			overviewPage.clickOnFinish();

			logger.info("Clicked on Finish");

			// Step 6: Checkout Complete Page
			CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
			String actualmsg = completePage.thankYouMessage();
			Assert.assertEquals(actualmsg, "Thank you for your order!");

			String checkoutMsg = completePage.checkoutCompleteMessage();
			Assert.assertEquals(checkoutMsg, "Checkout: Complete!");
			logger.info("Checkout Complete Page");
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("** TC001_VerifyCheckoutFlow Finished **");
	}
}