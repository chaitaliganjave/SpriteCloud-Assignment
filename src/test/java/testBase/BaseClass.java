package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger; // For Log4j
	public Properties p; //to access commonly used data from config.properties file

	@Parameters({ "os", "browser" })
	@BeforeClass
	public void setUp(String os, String br) throws IOException {

		logger = LogManager.getLogger(this.getClass()); // For Log4j

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}
		
		FileReader file = new FileReader("./src//test//resources/config.properties");
		 p = new Properties();
		 p.load(file);

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(p.getProperty("url")); //reading url from properties file
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	/*
	 * public String randomString() { String generatedString = new
	 * RandomStringUtils().randomAlphabetic(7); return generatedString;
	 * 
	 * }
	 * 
	 * public String randomNumber() { String generatedNumber = new
	 * RandomStringUtils().randomAlphanumeric(10); return generatedNumber;
	 * 
	 * }
	 * 
	 * public String randomAlphaNumeric() { String generatedNumber = new
	 * RandomStringUtils().randomAlphanumeric(3); String generatedString = new
	 * RandomStringUtils().randomAlphabetic(5); return (generatedNumber + "@" +
	 * generatedString);
	 * 
	 * }
	 */
}
