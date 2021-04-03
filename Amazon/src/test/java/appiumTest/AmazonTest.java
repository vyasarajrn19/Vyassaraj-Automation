package appiumTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import amazonBase.BaseClass;
import amazonBase.TestData;
import amazonBase.Uitilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import pageObjects.HomePage;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.SearchResultPage;

public class AmazonTest extends BaseClass {

	public HomePage AmazonHomePage;
	public LoginPage LoginPage;
	public SearchResultPage SearchResultPage;
	public Uitilities Uitilities;
	public ProductPage ProductPage;
	public CartPage CartPage;
	public TestData data;
	public AndroidDriver<AndroidElement> driver;
	public Logger log;

	/**
	 * Launching the APP under test - First step of Test Cases Execution
	 * 
	 * @throws InterruptedException
	 *
	 */

	@BeforeMethod
	public void inilization() throws IOException, InterruptedException {
		driver = capabilities("AmazonApp");
		AmazonHomePage = new HomePage(driver);
		LoginPage = new LoginPage(driver);
		SearchResultPage = new SearchResultPage(driver);
		Uitilities = new Uitilities(driver);
		ProductPage = new ProductPage(driver);
		CartPage = new CartPage(driver);
		data = new TestData();
		log = Logger.getLogger(AmazonTest.class);
		logger = report.createTest("Amazon app Test");

	}

	@Test
	public void Amazon_app() throws IOException, InterruptedException {

		AmazonHomePage.ClickSkipSignIn();
		logger.pass("Click on skipsign");

		Assert.assertTrue(AmazonHomePage.amazonlogoIsDisplayed());
		logger.pass("Home page is displayed");

		LoginPage.ClickSignIn();
		logger.pass("Clicked on signin in login page");

		LoginPage.EnterEmailaddress();
		logger.pass("Enter valid email id");

		LoginPage.ClickonContinue();
		logger.pass("Click on continue");

		logger.pass("Enter password");
		LoginPage.EnterPassword();

		logger.pass("Login to application");
		LoginPage.Login();

		Assert.assertTrue(AmazonHomePage.amazonlogoIsDisplayed());
		logger.pass("Login is sucessfull");

		Assert.assertTrue(SearchResultPage.searchIsDisplayed());
		logger.pass("Verified search bar is displayed");

		SearchResultPage.searchTV();
		logger.pass("Enter the text 65 inc TV in search bar");

		SearchResultPage.SearchDropDown();
		logger.pass("Search for the 65 inch TV");

		Assert.assertTrue(SearchResultPage.SearchResultIsDisplayed());
		logger.pass("Search result is diaplyed");

		Uitilities.scrollToText(TestData.Brand, driver);
		logger.pass("Selected the product and navigated to detail page of the product");

		Assert.assertTrue(ProductPage.productpageIsDisplayed());
		logger.pass("Product page is displayed");

		String ProductName = ProductPage.getProductName();
		logger.info("The Name of the Prodcut is : " + ProductName);
		Assert.assertTrue(ProductName.contains(TestData.tv));
		logger.pass("Product details are displayed");

		Uitilities.scrollToText(TestData.Currency, driver);
		String ProductPrice = ProductPage.getProductprice();
		logger.info("The Name of the Product Price is : " + ProductPrice);
		Assert.assertTrue(ProductPage.productpriceIsDisplayed());
		logger.pass("Product price is displayed");

		Uitilities.scrollToText("Add to Cart", driver);
		logger.pass("Added the Product to Cart by Scrolling to Add Cart Section");

		CartPage.clickonCartIcon();
		logger.pass("Navigate to the Cart menu");

		CartPage.cartpageIsDisplayed();
		logger.pass("Cart page is displayed");

		String ProductCartPage = CartPage.getBrandName();
		Assert.assertTrue(ProductCartPage.contains(TestData.Brand));
		logger.pass("Verified the product in cartpage ");

	}

}