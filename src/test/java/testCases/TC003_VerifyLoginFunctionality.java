package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.InventoryPage;
import pageObject.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_VerifyLoginFunctionality extends BaseClass {

	@Test(dataProvider = "Logindata", dataProviderClass = DataProviders.class,groups = {"DataDriven","Master"} )
	public void verify_LoginCredentials(String type, String username, String password, String result) throws Exception {
		logger.info("** TC003_VerifyLoginFunctionality Started **");
		try {
			LoginPage loginPage = new LoginPage(driver);
			InventoryPage inventoryPage = new InventoryPage(driver);
			loginPage.enterCredentialsAndLogin(username, password);

			logger.info("Credentials entered");

			if (type.equalsIgnoreCase("Valid")) {
				String actualUrl = loginPage.getCurrentPageURL();
				logger.info("Validate page url");
				Assert.assertEquals(actualUrl, result);
				logger.info("Page url validation successful");
				logger.info("Login successful");
				inventoryPage.clickOpenMenu();
				logger.info("clicked on Open Menu");
				inventoryPage.clickLogout();
				logger.info("Logout successful");
			} else if (type.equalsIgnoreCase("invalid")) {
				String actualResult = loginPage.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult, result);
				logger.info("Error message validation successful");
			} else if (type.equalsIgnoreCase("InvalidUsername")) {
				String actualResult = loginPage.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult, result);
				logger.info("Error message validation successful");
			} else if (type.equalsIgnoreCase("InvalidPassword")) {
				String actualResult = loginPage.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult, result);
				logger.info("Error message validation successful");
			} else if (type.equalsIgnoreCase("BlankUsername")) {
				String actualResult = loginPage.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult, result);
				logger.info("Error message validation successful");
			} else if (type.equalsIgnoreCase("BlankPassword")) {
				String actualResult = loginPage.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult, result);
				logger.info("Error message validation successful");
			} else {
				logger.info("Type not matched");
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("** TC001_VerifyLoginFunctionality Finished **");
	}
}
