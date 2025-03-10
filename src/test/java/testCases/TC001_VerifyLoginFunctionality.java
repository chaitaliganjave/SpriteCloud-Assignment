package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.InventoryPage;
import pageObject.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC001_VerifyLoginFunctionality extends BaseClass {

	@Test(dataProvider = "Logindata", dataProviderClass = DataProviders.class)
	public void verify_LoginCredentials(String type, String username, String password, String result) {
		logger.info("** TC001_VerifyLoginFunctionality started **");
		try {
			LoginPage lp=new LoginPage(driver);
			InventoryPage ip=new InventoryPage(driver);
			/*
			 * logger.info("Entering Username"); lp.enterUsername(username);
			 * 
			 * logger.info("Entering Password"); lp.enterPassword(password);
			 * 
			 * logger.info("click login"); lp.clickLogin();
			 */		
			

			/*
			 * String actualUrl = lp.getCurrentPageURL(); logger.info("Validate page url");
			 * Assert.assertEquals(actualUrl,p.getProperty("productpageURL"));
			 * logger.info("Validate successful");
			 */
			
			
			lp.enterCredentials(username, password);
			logger.info("Credentials are entered");
			
			
			if(type.equalsIgnoreCase("Valid")) {
				String actualUrl = lp.getCurrentPageURL();
				logger.info("Validate page url");
				logger.info("Login successfull");
				ip.clickOpenMenu();
				logger.info("clicked on Open Menu");
				ip.clickLogout();
				logger.info("Successfully Logout");
				
				Assert.assertEquals(actualUrl,result);
				logger.info("Validate successful");
				
			}
			
			else if(type.equalsIgnoreCase("invalid")) {
				String actualResult = lp.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult,result);	
			}
			else if(type.equalsIgnoreCase("InvalidUsername")) {
				String actualResult = lp.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult,result);	
			}
			else if(type.equalsIgnoreCase("InvalidPassword")) {
				String actualResult = lp.getErrorMessage(result);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult,result);	
			}
			else if(type.equalsIgnoreCase("BlankUsername")) {
				String actualResult = lp.getErrorMessage(result);
				logger.info("actualResult" + actualResult);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult,result);	
			}
			else if(type.equalsIgnoreCase("BlankPassword")) {
				String actualResult = lp.getErrorMessage(result);
				logger.info("actualResult" + actualResult);
				logger.info("Validate error message");
				Assert.assertEquals(actualResult,result);	
			}

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("** TC001_VerifyLoginFunctionality Finished **");
	}
}
