package amazonBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Uitilities extends BaseClass{
	
	
	AndroidDriver<AndroidElement>  driver;

	public Uitilities(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
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
	    	 MobileElement el = (MobileElement) ((FindsByAndroidUIAutomator) driver)
					    .findElementByAndroidUIAutomator("new UiScrollable("
					        + "new UiSelector().scrollable(true)).scrollIntoView("                      
					        + "new UiSelector().textContains(\""+text+"\"));");
			  el.click();
	}
	
	/**
	 * 
	 * @param driver
	 * @info : Swipe the Page in Horizontal Direction
	 */
	public static void swipeHorizontal(AppiumDriver<MobileElement> driver) {
		
	    System.out.println("Call swipe left...");
		Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.height * .15);
	    int startPoint = (int) (size.width * .10);
	    int endPoint = (int) (size.width * .50);
	    
	    System.out.println("Swipe: "+anchor+", "+startPoint+", "+endPoint+" and Size: "+size);
	    new TouchAction(driver).press(PointOption.point(950, 700)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).moveTo(PointOption.point(100, 700)).release().perform();
	    System.out.println("Element Swiped...");
	}
	
    /**
     * 
     * @param element
     * @param driver
     * @throws InterruptedException
     */
	public void clickAction(WebElement element, AppiumDriver<MobileElement> driver) throws InterruptedException {
		Thread.sleep(2000);
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
		System.out.println("Total No of Context Found = " + availableContexts.size());
		System.out.println("Available Contexts: " + availableContexts);
		for (String context : availableContexts) {
			System.out.println("context: " + context);
			if (context.contains("WEBVIEW")) {
				System.out.println("Switching to Context: " + context);
				driver.context(context);
				System.out.println("Switched to Context: " + context);
				break;
			} else {
				System.out.println("Expected context not found");
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
		System.out.println("Context switched to " + "NATIVE_APP");
	}
	
	public static String captureScreenshot(AndroidDriver<AndroidElement> driver) {

		//String path=System.getProperty("user.dir")+"/Screenshots/FreeCRM"+System.currentTimeMillis()+".png";
		String path=System.getProperty("user.dir")+"/Screenshots/Amazon_"+getCurrentDateTime()+".png";
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return path;
	}
	
	public static String getCurrentDateTime() {
		
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
		
	}
	
	public void waitTime(int millis) throws InterruptedException {
		Thread.sleep(millis);
		
	}
}
