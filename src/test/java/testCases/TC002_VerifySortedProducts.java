package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.InventoryPage;
import pageObject.LoginPage;
import testBase.BaseClass;

public class TC002_VerifySortedProducts extends BaseClass {
	@Test
	public void sortProductsbyZtoA() throws Exception {
		try {
			logger.info("** TC002_VerifySortedProducts Started **");

			// Step 1: Login Page
			LoginPage loginPage = new LoginPage(driver);
			loginPage.enterCredentialsAndLogin(p.getProperty("username"), p.getProperty("password"));
			logger.info("Credentials entered");

			// Step 2: Inventory Page
			InventoryPage inventoryPage = new InventoryPage(driver);
			// check login is successful and next page is visible
			boolean status = inventoryPage.checkInventoryPageIsDisplayed();
			try {
				Assert.assertTrue(status);
			} catch (Exception e) {
				Assert.fail();
			}

			inventoryPage.sortByNameZA(); // Sort products by Name Z-A
			logger.info("Sorting done");
			// Validate that the sorting is correct
			Assert.assertTrue(inventoryPage.isSortedByNameZA(), "Items are not sorted correctly by Name Z-A");

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("** TC002_VerifyLoginFunctionality Finished **");
	}
}
