package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='Thank you for your order!']")
	WebElement thankYouText;
	@FindBy(xpath = "//span[text()='Checkout: Complete!']")
	WebElement titleText;

	// Method to get text of thankYouText web element
	public String thankYouMessage() {
		return thankYouText.getText();
	}

	// Method to get text of titleText web element
	public String checkoutCompleteMessage() {
		return titleText.getText();
	}
}