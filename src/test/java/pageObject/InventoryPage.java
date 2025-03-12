package pageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

	public InventoryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()='Open Menu']")
	WebElement btn_OpenMenu;
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement lnk_Logout;
	@FindBy(xpath = "//div[@class='app_logo']")
	WebElement appLogo;
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement shoppingCart;
	@FindBy(xpath = "//button[text()='Add to cart']")
	List<WebElement> addProducts;
	@FindBy(xpath = "//div[@class='inventory_item_price']")
	List<WebElement> productPrice;
	@FindBy(xpath = "//div[@class='inventory_item_name ']")
	List<WebElement> productName;
	@FindBy(xpath = "//select[@class='product_sort_container']")
	WebElement sortProductDropdown;

	// Method to add products to cart
	public void addProductsToCart(int numberOfProducts) {
		for (int index = 0; index < numberOfProducts; index++) {
			addProducts.get(index).click();
			
		}
	}

	// Method to calculate total price
	public double calculateTotalPrice() {
		double total = 0.0;
		for (WebElement priceElement : productPrice) {
			String priceText = priceElement.getText().replace("$", "");
			total += Double.parseDouble(priceText);
		}
		return total;
	}

	// Method to get all product names
	public List<String> getAllProductNames() {
		List<String> productList = new ArrayList<>();
		for (WebElement pName : productName) {
			productList.add(pName.getText());
		}
		return productList;
	}

	// Method to validate sorting by Name Z-A
	public boolean isSortedByNameZA() {
		List<String> actualProductNames = new ArrayList<>();
		actualProductNames = getAllProductNames();
		List<String> expectedProductNames = new ArrayList<>(actualProductNames);
		Collections.sort(expectedProductNames, Collections.reverseOrder());
		return actualProductNames.equals(expectedProductNames);
	}

	// Method to click on shopping cart
	public void clickOnCart() {
		shoppingCart.click();
	}

	// Method to sort products by Name Z-A
	public void sortByNameZA() {
		Select select = new Select(sortProductDropdown);
		select.selectByVisibleText("Name (Z to A)");
	}

	// Method to check if inventory page is displayed
	public boolean checkInventoryPageIsDisplayed() {
		try {
			return (appLogo.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOpenMenu() {
		btn_OpenMenu.click();
	}

	public void clickLogout() {
		lnk_Logout.click();
	}

}
