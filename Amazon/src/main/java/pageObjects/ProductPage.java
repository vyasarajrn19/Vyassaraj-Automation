package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appiumTest.AmazonTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage {

	public static WebDriverWait wait;
	public Logger log;

	public ProductPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
		log = Logger.getLogger(ProductPage.class);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='0']")
	public AndroidElement RandomResult;

	@AndroidFindBy(xpath = "//*[@resource-id='title_feature_div']")
	public AndroidElement Name;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_gps_auto_detect")
	public AndroidElement UseMyCurrentLocarion;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public AndroidElement AllowPermission;

	@AndroidFindBy(xpath = "//*[@resource-id='newPitchPriceWrapper_feature_div']/android.view.View/android.widget.TextView[2]")
	public AndroidElement Price;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/glow_subnav_label")
	public AndroidElement Inches;

	@AndroidFindBy(xpath = "//*[@resource-id='title_feature_div']/android.view.View")
	public AndroidElement ProdcutName;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@index='0'])[3]")
	public AndroidElement Prodcut;

	@AndroidFindBy(xpath = "//android.view.View[@text='FREE Delivery']")
	public AndroidElement ProdcutDetails;

	@AndroidFindBy(xpath = "//android.view.View[@text='From the manufacturer']")
	public AndroidElement FromTheManufacturer;

	@AndroidFindBy(xpath = "//*[@resource-id='add-to-cart-button']")
	public AndroidElement AddToCart;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/atc-success")
	public AndroidElement AddedToCart;

	public boolean productpageIsDisplayed() {
		log.info("Selected the product and navigated to detail page of the product");
		wait.until(ExpectedConditions.visibilityOf(Inches));
		log.info("Verify Product page is displayed");
		return Inches.isDisplayed();

	}

	public boolean productpriceIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(Price));
		return Price.isDisplayed();

	}

	public String getProductprice() {
		wait.until(ExpectedConditions.visibilityOf(Price));
		log.info("Choose the product price");
		String ProductPrice = Price.getText();
		log.info("The Name of the Product Price is : " + ProductPrice);
		return ProductPrice;
	}

	public String getProductName() {
		wait.until(ExpectedConditions.visibilityOf(ProdcutName));
		log.info("Verify product details are displayed");
		String Prodcutname = ProdcutName.getText();
		log.info("The Name of the Product is : " + Prodcutname);
		return Prodcutname;
	}
}
