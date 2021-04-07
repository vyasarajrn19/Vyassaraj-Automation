package amazonBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.CartPage;

public class Uitilities extends BaseClass {

	AndroidDriver<AndroidElement> driver;
	public static WebDriverWait wait;
	public static Logger log;

	public Uitilities(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		log = Logger.getLogger(CartPage.class);
	}

	/**
	 * 
	 * @param text
	 * @param driver
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public void scrollToText(String text, AppiumDriver<AndroidElement> driver)
			throws MalformedURLException, InterruptedException {
		MobileElement el = (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains(\"" + text + "\"));");
		el.click();
	}

	/**
	 * 
	 * @param driver
	 * @info : Swipe the Page in Horizontal Direction
	 */
	public static void swipeHorizontal(AppiumDriver<MobileElement> driver) {

		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.height * .15);
		int startPoint = (int) (size.width * .10);
		int endPoint = (int) (size.width * .50);
		log.info("Swipe: " + anchor + ", " + startPoint + ", " + endPoint + " and Size: " + size);
		new TouchAction(driver).press(PointOption.point(950, 700))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).moveTo(PointOption.point(100, 700))
				.release().perform();
		log.info("Element Swiped...");

	}

	/**
	 * 
	 * @param element
	 * @param driver
	 * @throws InterruptedException
	 */
	public void clickAction(WebElement element, AppiumDriver<MobileElement> driver) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click();
		builder.perform();
	}

	/**
	 * Switch to WebView context
	 * 
	 * @param driver
	 */
	public void switchToWebView(AppiumDriver<MobileElement> driver) {

		Set<String> availableContexts = driver.getContextHandles();
		log.info("Total No of Context Found = " + availableContexts.size());
		log.info("Available Contexts: " + availableContexts);
		for (String context : availableContexts) {
			log.info("context: " + context);
			if (context.contains("WEBVIEW")) {
				log.info("Switching to Context: " + context);
				driver.context(context);
				log.info("Switched to Context: " + context);
				break;
			} else {
				log.info("Expected context not found");
			}
		}
	}

	/**
	 * Switch to Native context
	 * 
	 * @param driver
	 */
	public void switchToNativeApp(AppiumDriver<MobileElement> driver) {
		driver.context("NATIVE_APP");
		log.info("Context switched to " + "NATIVE_APP");
	}

	public static String captureScreenshot(AndroidDriver<AndroidElement> driver) {

		String path = System.getProperty("user.dir") + "/Screenshots/Amazon_" + getCurrentDateTime() + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			log.info(e.getMessage());
		}

		return path;
	}

	public static String getCurrentDateTime() {

		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);

	}
	
	public  void waitForElemetExistAndClick(AndroidElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	


}
