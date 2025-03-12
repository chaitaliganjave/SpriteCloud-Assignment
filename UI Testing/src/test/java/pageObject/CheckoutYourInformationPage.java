package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutYourInformationPage extends BasePage {

	public CheckoutYourInformationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "first-name")
	WebElement firstName;
	@FindBy(xpath = "//input[@id='last-name']")
	WebElement lastName;
	@FindBy(xpath = "//input[@data-test='postalCode']")
	WebElement postalCode;
	@FindBy(id = "continue")
	WebElement continueBtn;

	//Method to set first name
	public void setFirstName(String ftname) {
		firstName.sendKeys(ftname);
	}

	//Method to set last name
	public void setLastName(String ltname) {
		lastName.sendKeys(ltname);
	}

	//Method to set postal code
	public void setPostalcode(String postalNo) {
		postalCode.sendKeys(postalNo);
	}

	//Method to click on continue button
	public void clickOnContinue() {
		continueBtn.click();
	}
}