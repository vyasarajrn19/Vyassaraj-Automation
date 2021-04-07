package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazonBase.BaseClass;
import amazonBase.Uitilities;
import appiumTest.AmazonTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends BaseClass {

	public Logger log;
	public static WebDriverWait wait;
	public Uitilities uitilities;

	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
		log = Logger.getLogger(HomePage.class);
		uitilities = new Uitilities(driver);
	}

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
	public AndroidElement SkipSignIn;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_home_logo")
	public AndroidElement AmazonLogo;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_burger_icon")
	public AndroidElement HamburgerMenu;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Start shopping']")
	public AndroidElement startShopping;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_clear_text_button_accessibility")
	public AndroidElement ClearButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Deliver')]")
	public AndroidElement Devliver;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_update_current_pin_code")
	public AndroidElement useCurrentLocation;

	public void ClickSkipSignIn() {
		uitilities.waitForElemetExistAndClick(SkipSignIn);
		log.info("Click on skip sign button");

	}

	public boolean amazonlogoIsDisplayed() {
		log.info("Verified amazon Logo is displayed");
		wait.until(ExpectedConditions.visibilityOf(AmazonLogo));
		log.info("Home page is displayed");
		return AmazonLogo.isDisplayed();
	}

}
