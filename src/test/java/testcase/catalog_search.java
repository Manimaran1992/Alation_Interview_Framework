package testcase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageobject.amazon_pageobject;

public class catalog_search {
	//Testcase Webdriver Instance
	public static WebDriver driver = null;
	
	//Test Data for Testcase
	String URL = "https://www.amazon.com/";
	String Category = "Books";
	String SearchKeyword = "data catalog";
	int SearchResult_Index = 0;

	//Extent Report Initialization
	ExtentReports extent;
	ExtentTest logger;

	@BeforeSuite
	public void reportpreparation() {
		//Define extent report files and environment details
		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/Extent_Report/ExtentReport.html", true);
		// extent.addSystemInfo("Environment","Environment Name")
		extent.addSystemInfo("Host Name", "Avation")
				.addSystemInfo("Environment",
						"Amazon Search Automation Testing")
				.addSystemInfo("User Name", "Manimaran");
		extent.loadConfig(new File(System.getProperty("user.dir")
				+ "\\extent-config.xml"));
	}

	@BeforeMethod
	public void environment_setup() {
		//Initialize firefox driver
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + File.separator + "src"
						+ File.separator + "test" + File.separator
						+ "resources" + File.separator + "browsers"
						+ File.separator + "geckodriver.exe");
		driver = new FirefoxDriver();
		//Firefox browser window maximize
		driver.manage().window().maximize();
		//Adding 20 seconds implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(testName = "Amazon data catalog search and validate product details")
	public void Amazon_search() throws InterruptedException, IOException {
		//Starting test extent report logger
		logger = extent.startTest("Amazon Search","Amazon Search automation testing");
		amazon_pageobject amazon_page = new amazon_pageobject(driver);
		amazon_page.launchURL(URL);
		amazon_page.selectSearchCategory(Category);
		// amazon_page.searchwithKeyword(SearchKeyword);
		amazon_page.ClickonSearchresult(0);
		amazon_page.Saveauthors_Details();
		amazon_page.Save_Buyused_price();
		amazon_page.Save_Buynew_price();
		amazon_page.Save_bookedition();
		amazon_page.Save_Stock_status();
	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		//Log the failed testcase on extent report and take screenshot
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getThrowable());
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File(System.getProperty("user.dir") + File.separator
							+ "src" + File.separator + "test" + File.separator
							+ "resources" + File.separator + "screenshots"
							+ File.separator + "Pass_screenshotfile.png"));
			System.out.println("Screenshot taken");
			//Log the passed testcase on extent report and take screenshot
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS,
					"Test Case passed is " + result.getName());
			logger.log(LogStatus.PASS,
					"Test Case passed is " + result.getThrowable());
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File(System.getProperty("user.dir") + File.separator
							+ "src" + File.separator + "test" + File.separator
							+ "resources" + File.separator + "screenshots"
							+ File.separator + "Fail_screenshotfile.png"));
			System.out.println("Screenshot taken");
			//Log the skipped testcase on extent report and take screenshot
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP,
					"Test Case Skipped is " + result.getName());
		}
		//End of extent report log
		extent.endTest(logger);
		//Close all webdriver instances
		driver.quit();
	}

	@AfterSuite
	public void aftersuite() {
		//update test information to your report
		extent.flush();
		//Clear all extent report resources
		extent.close();
	}
}