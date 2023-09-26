import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver driver;
    LoginPage loginPage;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginExistingAccount() {


        loginPage.insertEmailAddress("mamaomidaghiceste@gmail.com");
        loginPage.setPasswordInput("Password123!");
        loginPage.clickLogin();

        String actualText = loginPage.getMyAccountText();
        String expectedText = "Account";
        Assert.assertEquals(actualText, expectedText, "This is not what we expected!");
        driver.quit();


    }

    @Test
    //creating a login test with the correct e-mail address but incorrect password
    public void incorrectLoginTry() {

        loginPage.insertEmailAddress("mamaomidaghiceste@gmail.com");
        loginPage.setPasswordInput("Password123");
        loginPage.clickLogin();

        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");


    }

    @AfterTest
    public void tearDown() {

        driver.quit();
    }

}

