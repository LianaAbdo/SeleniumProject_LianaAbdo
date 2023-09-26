import Util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static Util.TestUtil.generateRandomEmail;





public class RegisterAccountTests {
    private WebDriver driver;
    RegisterAccountPage registerAccountPage;
    private String loginPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";



    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + loginPageURL);
        driver.get(loginPageURL);
        registerAccountPage= new RegisterAccountPage(driver);


    }
    @Test
    public void registerNewAccountMandatoryFieldsTest() throws Exception {


        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        AccountCreatedPage  accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText  = "Your Account Has Been Created!";
        Assert.assertEquals(actualText,expectedText,"Actual text is not the expected one.");


    }
    @Test
    public void registerWithoutPolicyPrivacyTest(){

        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
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
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getFirstNameErrorMessage();
        String expectedText = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");


    }
    @Test
    public void registerAccountWithoutLastNameTest() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getLastNameErrorMessage();
        String expectedText = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");

    }
    @Test
    public void registerAccountWithoutEmailAddress() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getNoEmailError();
        String expectedText = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");

    }
    @Test
    public void registerAccountWithoutPhoneNumber() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getNoTelephoneError();
        String expectedText = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "The warning message is not the expected one");

    }
    @Test
    public void registerAccountWithTooShortPassword() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("123");
        registerAccountPage.setPasswordConfirm("123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getTooShortPasswordError();
        String expectedText  = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(actualText,expectedText,"Actual text is not the expected one.");

    }
    @Test
    public void nonMatchingPasswordConfirmation() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getPasswordConfirmationError();
        String expectedText  = "Password confirmation does not match password!";
        Assert.assertEquals(actualText,expectedText,"Actual text is not the expected one.");

    }
    @Test
    public void registerAccountWithAlreadyRegisteredEmail() {
        registerAccountPage.insertFirstName("Mrs");
        registerAccountPage.insertLastNme("Lollipop");
        registerAccountPage.insertEmail("mrslollie@mail.com");
        registerAccountPage.insertPhoneNumber("0123456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText= registerAccountPage.getWarningMessage();
        String expectedText  = "Warning: E-Mail Address is already registered!";
        Assert.assertEquals(actualText,expectedText,"Actual text is not the expected one.");

    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
