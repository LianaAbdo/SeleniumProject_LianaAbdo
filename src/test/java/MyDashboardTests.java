import Util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Util.TestUtil.generateRandomEmail;
import static Util.TestUtil.generateRandomPhoneNr;


public class MyDashboardTests extends BaseTest {
    private RegisterAccountPage registerAccountPage;
    private MyDashboardPage myDashboardPage;
    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUpPreconditions() {
        System.out.println("Creating new account to be logged in...");
        registerAccountPage= new RegisterAccountPage(driver);
        createAccount();
        System.out.println("Done creating new account");
    }
    @BeforeMethod
    public void beforeMethod() {
        myDashboardPage = new MyDashboardPage(driver);

    }
    @Test
    public void verifyDashboardFirstSection() {
        String expectedFirstSectionHeaderText = "My Account";
        String expectedEditAccountElementText = "Edit your account information";
        String expectedChangeYourPasswordElement = "Change your password";

        Assert.assertEquals(myDashboardPage.getFirstSectionHeaderText(),expectedFirstSectionHeaderText, "This is not the correct header");
        Assert.assertEquals(myDashboardPage.getEditAccountElementText(),expectedEditAccountElementText,"Edit account element is not the correct one");
        Assert.assertEquals(myDashboardPage.getChangePasswordElementText(), expectedChangeYourPasswordElement, "Change your password element isn't matching");

    }

    @Test
    public void verifyAdressBookWishlistSubscription() {
        String expectedModifyAddressBookText = "Modify your address book entries";
        String expectedModifyWishlistText = "Modify your wish list";
        String expectedNewsletterSubscription = "Subscribe/unsubscribe to newsletter";

        Assert.assertEquals(myDashboardPage.getModifyAddressElementText(),expectedModifyAddressBookText,"This is not the text we have expected");
        Assert.assertEquals(myDashboardPage.getMyWishlistElementText(),expectedModifyWishlistText,"This is the wrong text");
        Assert.assertEquals(myDashboardPage.getNewsletterElementText(),expectedNewsletterSubscription,"The Newsletter text isn't right");
    }
    @Test
    public void updateAccountPhoneNr() {
        myDashboardPage.clickEditAccountElement();
        registerAccountPage.insertPhoneNumber(generateRandomPhoneNr().toString());
        registerAccountPage.clickContinue();
        String actualMessage= myDashboardPage.getUpdatedMessage();
        String expectedMessage ="Success: Your account has been successfully updated.";
        Assert.assertEquals(actualMessage, expectedMessage, "This is not the correct message displayed");

    }
    @Test
    public void updateAccountEmail() {
        myDashboardPage.clickEditAccountElement();
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.clickContinue();
        String actualMessage= myDashboardPage.getUpdatedMessage();
        String expectedMessage ="Success: Your account has been successfully updated.";
        Assert.assertEquals(actualMessage, expectedMessage, "This is not the correct message displayed");

    }
    @Test
    public void addressBookNoResults() {
        myDashboardPage.clickAddressBook();
        String actualText = myDashboardPage.getAdressBookTxt();
        String expectedText = "No results!";
        Assert.assertEquals(actualText,expectedText,"There is no match!");
    }
    @Test
    public  void subscribeToNewsletter() {
        myDashboardPage.clickNewsletterElement();
        myDashboardPage.clickYesSubscription();
        registerAccountPage.clickContinue();
        String actualTxt = registerAccountPage.getWarningMessage();
        String expectedTxt = "Success: Your newsletter subscription has been successfully updated!";
        Assert.assertEquals(actualTxt,expectedTxt, "The text isn't matching.");

    }
    @Test
    public void UnsubscribeFromNewsletter() {
        myDashboardPage.clickNewsletterElement();
        myDashboardPage.clickNoSubscription();
        registerAccountPage.clickContinue();
        String actualTxt = registerAccountPage.getWarningMessage();
        String expectedTxt = "Success: Your newsletter subscription has been successfully updated!";
        Assert.assertEquals(actualTxt,expectedTxt, "The text isn't matching.");


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
