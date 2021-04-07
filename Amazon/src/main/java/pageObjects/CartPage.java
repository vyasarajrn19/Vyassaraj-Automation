package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazonBase.Uitilities;
import appiumTest.AmazonTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {

	public static WebDriverWait wait;
	public Logger log;
	public Uitilities uitilities;

	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
		log = Logger.getLogger(CartPage.class);
		uitilities = new Uitilities(driver);
	}

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart_image")
	public AndroidElement CartMenu;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Proceed to checkout')]")
	public AndroidElement ProceedToBuy;

	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'Subtotal')]")
	public AndroidElement SubTotal;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_clear")
	public AndroidElement ClearSearch;

	@AndroidFindBy(xpath = "//android.view.View[@text='Add a mobile phone number']")
	public AndroidElement AddMobileNumber;

	@AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
	public static AndroidElement MobileNumberField;

	@AndroidFindBy(xpath = "//*[@text='Continue']")
	public AndroidElement ContinueMoble;

	@FindBy(id = "android.view.View[@text='Enter a shipping address']")
	public AndroidElement EnterShippingAddress;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@index='0'])[3]")
	public AndroidElement Prodcut;

	public void clickonCartIcon() {
		log.info("Added the Product to Cart by Scrolling to Add Cart Section");
		uitilities.waitForElemetExistAndClick(CartMenu);
		log.info("Navigate to the Cart menu");
	}

	public boolean cartpageIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(ProceedToBuy));
		log.info("Verify cart page is displayed");
		return ProceedToBuy.isDisplayed();
	}

	public String getBrandName() {
		wait.until(ExpectedConditions.visibilityOf(Prodcut));
		log.info("Verify the product in cart page");
		String Prodcutname = Prodcut.getText();
		log.info("The Name of the Prodcut is : " + Prodcutname);
		return Prodcutname;
	}

}
