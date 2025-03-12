package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
	public ExtentReports extent; // populate common info of the report
	public ExtentTest test; // creating test case entries in the report and update status of test methods
	public ExtentSparkReporter sparkReporter; // UI of report

	String reportName;

	// Initialize ExtentReports
	public void onStart(ITestContext textContext) {
		if (extent == null) {
			// Set the report file path
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Create date and time
																								// Format
			reportName = "Test-Report-" + timeStamp + ".html";
			String reportPath = System.getProperty("user.dir") + "\\reports\\" + reportName;

			// Initialize ExtentSparkReporter
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			// Configure the report
			sparkReporter.config().setDocumentTitle("Automation Test Report");
			sparkReporter.config().setReportName("SauceDemo Test Report");
			sparkReporter.config().setTheme(Theme.STANDARD);

			// Initialize ExtentReports
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Add system information
			extent.setSystemInfo("Application", "Swag Labs");

			String os = textContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating system", os);

			String browser = textContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);

			extent.setSystemInfo("User", System.getProperty("user.name"));
		}
		// return extent;
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.PASS, result.getName() + " got successfully executed ");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) { // TODO handle exception
			e.printStackTrace(); // it will display any warning msg
		}
		System.out.println("Test is failed...");
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " Testcase got skipped ");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {
		extent.flush();

		// to open reports automatically
		String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
		File extentReport = new File(pathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); // this will open the report on browser automatically
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}