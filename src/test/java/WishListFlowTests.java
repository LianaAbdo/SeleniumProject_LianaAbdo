import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Util.TestUtil.generateRandomEmail;

public class WishListFlowTests {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private WishListPage wishListPage;
    private SearchResultsPage searchResultsPage;


    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";


    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        registerAccountPage = new RegisterAccountPage(driver);
        wishListPage = new WishListPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        createAccount();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + loginPageURL);

    }

    @Test

    public void addItemToWishList() {
        wishListPage.clickWishlist();

        String actualResult = wishListPage.getNoResultsElementText();
        String expectedResult = "No results!";
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one!");
        wishListPage.enterTextSearch("Apple Cinema 30\"");
        wishListPage.clickSearchButton();
        searchResultsPage.clickFirstItem();

    }

    public void createAccount() {
        System.out.println("Creating new account to be used in tests...");
        registerAccountPage.insertFirstName("mama");
        registerAccountPage.insertLastNme("omida");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        System.out.println("Creating new account to be used in tests...Done");
    }
}
