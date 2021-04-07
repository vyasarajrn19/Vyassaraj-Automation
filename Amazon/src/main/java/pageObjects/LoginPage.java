package pageObjects;

import org.apache.log4j.Logger;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazonBase.TestData;
import amazonBase.Uitilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	public static WebDriverWait wait;
	public Logger log;
	public Uitilities uitilities;

	public LoginPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 60);
		log = Logger.getLogger(LoginPage.class);
		uitilities = new Uitilities(driver);
	}

	@AndroidFindBy(id = "gw-sign-in-button")
	public AndroidElement SignIn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Sign in']")
	public AndroidElement HelloSignIn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
	public AndroidElement MobileNumberOrEmail;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
	public AndroidElement ContinueBtn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Amazon password']")
	public AndroidElement Password;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Sign-In']")
	public AndroidElement Login;

	public void ClickSignIn() {
		uitilities.waitForElemetExistAndClick(HelloSignIn);
		log.info("Clicked on sign in to login page");

	}

	public void EnterEmailaddress() {
		wait.until(ExpectedConditions.visibilityOf(MobileNumberOrEmail));
		MobileNumberOrEmail.sendKeys(TestData.email);
		log.info("Enter valid email id");

	}

	public void ClickonContinue() {
		uitilities.waitForElemetExistAndClick(ContinueBtn);
		log.info("click on continue");

	}

	public void EnterPassword() {
		wait.until(ExpectedConditions.visibilityOf(Password));
		Password.sendKeys(TestData.password);
		log.info("Enter password");

	}

	public void Login() {
		uitilities.waitForElemetExistAndClick(Login);
		log.info("Login to application");

	}

}
