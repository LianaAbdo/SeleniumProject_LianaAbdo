import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class RegisterAccountTests {
    private WebDriver driver;
    RegisterAccountPage registerAccountPage;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        registerAccountPage = new RegisterAccountPage(driver);

    }
    @Test
    public void registerNewAccountMandatoryFieldsTest() throws Exception {


        registerAccountPage.insertFirstName("mama");
        registerAccountPage.insertLastNme("omida");
        //email needs to be changed every new run
        Random random = new Random();
        String email = "mamaomidaghiceste" + random.nextLong() + "gmail.com";
        System.out.println("Used email is: " + email);
        registerAccountPage.insertEmail("mamaomidaghiceste" + random.nextLong() + "gmail.com");
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        AccountCreatedPage  accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText  = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText,expectedText,"Actual text is not the expected one.");


    }
    @Test
    public void registerWithoutPolicyPrivacyTest(){

        registerAccountPage.insertFirstName("mama");
        registerAccountPage.insertLastNme("omida");
        Random random = new Random();
        String email = "mamaomidaghiceste" + random.nextLong() + "gmail.com";
        System.out.println("Used email is: " + email);
        registerAccountPage.insertEmail("mamaomidaghiceste" + random.nextLong() + "gmail.com");
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getWarningMessage();
        String expectedText = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");

    }
    @Test
    public void registerAccountWithoutFirstNameTest() {
        registerAccountPage.insertLastNme("omida");
        Random random = new Random();
        String email = "mamaomidaghiceste" + random.nextLong() + "gmail.com";
        System.out.println("Used email is: " + email);
        registerAccountPage.insertEmail("mamaomidaghiceste" + random.nextLong() + "gmail.com");
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getFirstNameErrorMessage();
        String expectedText = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
