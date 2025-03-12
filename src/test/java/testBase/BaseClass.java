package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // For Log4j
	public Properties p; // to access commonly used data from config.properties file

	@BeforeClass(groups = {"End to end","Sanity","DataDriven","Master"})
	@Parameters({ "os", "browser" })
	public void setUp(String os, String browser) throws IOException {

		logger = LogManager.getLogger(this.getClass()); // For Log4j

		switch (browser.toLowerCase()) {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(p.getProperty("url")); // reading url from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"End to end","Sanity","DataDriven","Master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().substring(0, 5).toUpperCase();
	}

	// Method to generate random 5 AlphaNumeric characters
	public String randomAlphaNumeric() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().substring(0, 5).toUpperCase();
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Create date and time
																							// Format
		TakesScreenshot ts = (TakesScreenshot) driver;

		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
