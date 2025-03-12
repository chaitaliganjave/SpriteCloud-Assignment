package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "checkout")
	WebElement checkoutBtn;
	@FindBy(xpath = "//div[@class='inventory_item_price']")
	List<WebElement> productPrice;

	// Method to calculate total price
	public double calculateTotalPrice() {
		double total = 0.0;
		for (WebElement priceElement : productPrice) {
			String priceText = priceElement.getText().replace("$", "");
			total = total + Double.parseDouble(priceText);
		}
		return total;
	}

	// Method to click Checkout button
	public void clickOnCheckout() {
		checkoutBtn.click();
	}
}