package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "user-name")
	WebElement txt_Login;
	@FindBy(id = "password")
	WebElement txt_Password;
	@FindBy(id = "login-button")
	WebElement btn_Login;
	@FindBy(xpath = "//div[@class='error-message-container error']")
	WebElement errorMessage;

	// Method to enter username in login page
	public void enterUsername(String username) throws Exception {
		txt_Login.clear();
		txt_Login.sendKeys(username);
	}

	// Method to enter password in login page
	public void enterPassword(String password) throws Exception {
		txt_Password.clear();
		txt_Password.sendKeys(password);
	}

	// Method to click on login button
	public void clickLogin() {
		btn_Login.click();
	}

	// Method to enter username, password and then login
	public void enterCredentialsAndLogin(String username, String password) throws Exception {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public String getErrorMessage(String errorMsg) {
		try {
			return (errorMessage.getText());
		} catch (Exception e) { // TODO: handle exception
			return (e.getMessage());
		}
	}

	public String getPageTitle() {
		try {
			return (driver.getTitle());
		} catch (Exception e) {
			// TODO: handle exception
			return (e.getMessage());
		}
	}

	public String getCurrentPageURL() {
		try {
			return (driver.getCurrentUrl());
		} catch (Exception e) { // TODO: handle exception
			return (e.getMessage());
		}
	}
}
