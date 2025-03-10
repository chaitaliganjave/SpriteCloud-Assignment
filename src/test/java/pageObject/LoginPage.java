package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators

	@FindBy(id = "user-name") WebElement txt_Login;
	@FindBy(id = "password") WebElement txt_Password;
	@FindBy(id = "login-button") WebElement btn_Login;
	@FindBy(xpath = "//div[@class='error-message-container error']") WebElement errorMessage;

	// Action Methods
	
	public void enterCredentials(String usname, String pass) {
		txt_Login.clear();
		txt_Login.sendKeys(usname);
		txt_Password.clear();
		txt_Password.sendKeys(pass);
		btn_Login.click();
		
		
	}

	/*
	 * public void enterUsername(String username) { txt_Login.clear();
	 * txt_Login.sendKeys(username); }
	 * 
	 * public void enterPassword(String password) { txt_Password.clear();
	 * txt_Password.sendKeys(password); }
	 * 
	 * public void clickLogin() { btn_Login.click(); }
	 * 
	 */	public String getErrorMessage(String errorMsg) { 
		try{
			return(errorMessage.getText());
		} 
		catch(Exception e) 
		{ // TODO: handle exception 
		return(e.getMessage()); } }
	
	 

	public String getPageTitle() {
		try{
			return(driver.getTitle());
		}
		catch (Exception e) {
			// TODO: handle exception
			return(e.getMessage());
		}
	}

	public String getCurrentPageURL() { 
		try{
			return(driver.getCurrentUrl());
		} 
		catch(Exception e) 
		{ // TODO: handle exception 
		return(e.getMessage()); } }
	}
	 
	
	/*
	 * public boolean isPageURLCorrect() { try{ return (driver.getCurrentUrl()); }
	 * catch(Exception e) { return false; }
	 * 
	 * }
	 */
	

