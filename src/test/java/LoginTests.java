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


        loginPage.insertEmailAddress("mrslollie@mail.com");
        loginPage.setPasswordInput("Password123!");
        loginPage.clickLogin();

        String actualText = loginPage.getMyAccountText();
        String expectedText = "Account";
        Assert.assertEquals(actualText, expectedText, "This is not what we expected!");
        driver.quit();


    }

    @Test
    //creating a login test with the correct e-mail address but incorrect password
    public void incorrectPasswordLoginTry() {

        loginPage.insertEmailAddress("mrslollie@mail.com");
        loginPage.setPasswordInput("Password123");
        loginPage.clickLogin();

        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");


    }
    @Test
    //test for logging with incorrect email address but correct password
    public void incorrectEmailAddressLogin() {
        loginPage.insertEmailAddress("lollipop@gmail.com");
        loginPage.setPasswordInput("Password123!");
        loginPage.clickLogin();
        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText,expectedText,"This alert text is not correct");

    }
    @Test
    public void emailNorPasswordCorrect() {
        loginPage.insertEmailAddress("123a");
        loginPage.setPasswordInput("123");
        loginPage.clickLogin();
        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText,expectedText,"This is not the right warning message");


    }
    @Test
    public void loginWithEmailButNoPassword() {
        loginPage.insertEmailAddress("mrslollie@mail.com");
        loginPage.clickLogin();
        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText,expectedText,"The actual message doesn't match the expected one");
    }
    @Test
    public void loginWithoutEmailnorPassword() {
        loginPage.clickLogin();
        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertEquals(actualText,expectedText,"This text is not the ne that should appear when you attempt numerous login failed tries");
    }
    @Test
    public void loginWithoutEmailOnlyPassword () {
        loginPage.setPasswordInput("Password123!");
        loginPage.clickLogin();
        String actualText = loginPage.getWarningMessage();
        String expectedText = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertEquals(actualText, expectedText, "This is not the expected text");
    }
    @Test
    public void forgotYourPasswordLogin() {
        loginPage.insertEmailAddress("mrslollie@mail.com");
        loginPage.clickForgotPassword();
        String actualText = loginPage.getForgotPasswordText();
        String expectedText = "Forgot Your Password?";
        Assert.assertEquals(actualText,expectedText,"This is not the forgotten password text message");

    }


    @AfterTest
    public void tearDown() {

        driver.quit();
    }


}

