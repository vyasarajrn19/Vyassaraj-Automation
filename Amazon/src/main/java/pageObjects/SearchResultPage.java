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

public class SearchResultPage {

	public static WebDriverWait wait;
	public Logger log;
	public Uitilities uitilities;

	public SearchResultPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
		log = Logger.getLogger(SearchResultPage.class);
		uitilities = new Uitilities(driver);
	}

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	public AndroidElement HomeSearch;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text")
	public AndroidElement SearchDropDown;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Results')]")
	public AndroidElement ResultsCount;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='0']")
	public AndroidElement RandomResult;

	public boolean searchIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(HomeSearch));
		log.info("Verify search bar is displayed");
		return HomeSearch.isDisplayed();
	}

	public void searchTV() throws InterruptedException {
		uitilities.waitForElemetExistAndClick(HomeSearch);
		HomeSearch.sendKeys(TestData.Product);
		log.info("Enter the text 65 inch TV in search bar");
	}

	public void SearchDropDown() {
		uitilities.waitForElemetExistAndClick(SearchDropDown);
		log.info("Search for the 65 inch TV");
	}

	public boolean SearchResultIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(RandomResult));
		log.info("Verify the search result");
		return RandomResult.isDisplayed();

	}

}
