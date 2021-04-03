package amazonBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import appiumTest.AmazonTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	public static WebDriverWait wait;
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public ExtentReports report;
	public ExtentTest logger;
	String path;

	Logger log = Logger.getLogger(BaseClass.class);

	@BeforeSuite
	public void setupTestSuite() {

		path = System.getProperty("user.dir") + "/Reports/Amazon_" + Uitilities.getCurrentDateTime() + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		report = new ExtentReports();
		report.attachReporter(htmlReporter);

	}

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {

		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		log.info("killing all the nodes");
		service = startServer();
		log.info("Starting appium server");

	}

	/**
	 * Starting the Appium Server through Code using AppiumServiceBuilder
	 * 
	 */
	public static AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;

	}

	public static void startEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {

			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	/**
	 * @author Vyasaraj
	 * 
	 *         Desired Capabilities --> keys and values encoded in a JSON object,
	 *         It's a way of telling the Appium Server which kind of session we are
	 *         interested in. Appium clients Sent's the capabilities to server when
	 *         a new automation session is requested
	 * 
	 */

	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\amazonBase\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File(System.getProperty("user.dir") + "\\App\\");

		File app = new File(appDir, (String) prop.get(appName));

		DesiredCapabilities capabilities = new DesiredCapabilities();

		String device = prop.getProperty("Device");

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);

		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL(prop.getProperty("AppiumServerURL")), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	
	/**
	 * Stop's the Appium Server and Close the Connection
	 */
	@AfterTest
	public void stopsession() throws IOException, InterruptedException {

		// service.stop();
		// Runtime.getRuntime().exec("taskkill /F /IM node.exe");

	}

	@AfterMethod
	public void tearDownTest(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail("Test Failed " + result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(Uitilities.captureScreenshot(driver)).build());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		report.flush();
	}

}
