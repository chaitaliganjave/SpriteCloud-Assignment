package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	//Method to click Checkout button
	public void clickOnCheckout() {
		checkoutBtn.click();
	}
}