import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Util.TestUtil.generateRandomEmail;

public class WishListFlowTests extends BaseTest{

    private RegisterAccountPage registerAccountPage;
    private WishListPage wishListPage;
    private SearchResultsPage searchResultsPage;
    private Actions action;


    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";


    @BeforeClass
    public void setUpPreconditions() {
        registerAccountPage = new RegisterAccountPage(driver);
        wishListPage = new WishListPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        action = new Actions(driver);
        System.out.println("Creating new account to be logged in...");
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
    }

    @Test

    public void addItemToWishList() throws Exception {
        driver.manage().window().fullscreen();
        String expectedResult = "No results!";
        wishListPage.clickWishlist();
        String actualResult = wishListPage.getNoResultsElementText();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishListPage.enterTextSearch("Apple Cinema 30\"");
        wishListPage.clickSearchButton();
        //Wait for items to load
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem();
        action.moveToElement(item).build().perform();
        //Wait for hoover element to be displayed
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddToWishlistButton();
        action.moveToElement(button).click().build().perform();
        //Wait for popup to be displayed
        Thread.sleep(1000);
        searchResultsPage.clickClosePopupButton();
        searchResultsPage.clickWishlist();
        int noOfItems = wishListPage.getWishListItems().size();
        Assert.assertTrue(noOfItems == 1, "Wishlist is empty");
        wishListPage.clickRemoveItemFromWishlistButton();
        actualResult = wishListPage.getNoResultsElementText();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
    }
    @Test
    public void searchNoProductResult() {
        //want to search an unexistent product and get the text message that shows
        wishListPage.enterTextSearch("fridge");
        wishListPage.clickSearchButton();
        String expectedTxt= "There is no product that matches the search criteria.";
        Assert.assertEquals(wishListPage.getNotFindingItemMsg(),expectedTxt, "There is no match");
    }
    @Test
    public void addSamsungToWishlist() {
        //searching Samsung in the search bar and adding the 3rd element found to Wishlist
        wishListPage.enterTextSearch("Samsung");
        wishListPage.clickSearchButton();
        searchResultsPage.clickThirdItemSamsung();
        searchResultsPage.addItemToWishlist();


    }
    @Test
    public void removeSamsungFromWishlist () {
        wishListPage.clickWishlist();
        wishListPage.clickRemoveItemFromWishlistButton();
        String expectedResult = "No results!";
        String actualResult = wishListPage.getNoResultsElementText();
        Assert.assertEquals(actualResult, expectedResult, "Wrong text");
    }


    public void createAccount() {
        System.out.println("Creating new account to be used in tests...");
        driver.get(registerUrl);
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
    }
}
