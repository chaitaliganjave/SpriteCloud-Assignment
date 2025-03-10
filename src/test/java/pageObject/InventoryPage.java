package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage{
	
	public InventoryPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath = "//button[text()='Open Menu']") WebElement btn_OpenMenu;
	@FindBy(xpath = "//a[text()='Logout']") WebElement lnk_Logout;

	
	
	
	
	
	
	
	public void clickOpenMenu() {
		btn_OpenMenu.click(); }
	
	public void clickLogout() {
		lnk_Logout.click(); }

}
