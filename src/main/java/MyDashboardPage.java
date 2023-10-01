import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyDashboardPage extends BasePage{
    public MyDashboardPage(WebDriver driver) {
        super.driver = driver;
    }
    private By firstSectionHeader = By.xpath(".//div[@id = 'content']/div[1]/h2");
    private By editAccountElement = By.xpath("//*[@id=\"column-right\"]/div/a[2]");
    private By changePasswordElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/password')]");
    private By modifyAddressElement = By.xpath("//*[@id=\"column-right\"]/div/a[4]");
    private By myWishlistElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/wishlist')]");
    private By newsletterElement = By.xpath("//*[@id=\"content\"]/div[1]/div/div/div[5]/a");
    private By updatedMessage = By.xpath("//*[@id=\"account-account\"]/div[1]");
    private By addressBookTxt = By.xpath("//*[@id=\"content\"]/p");
    private By yesSubscription = By.xpath("//*[@id=\"content\"]/form/fieldset/div/div/div[1]/label");
    private By noSubscription = By.xpath("//*[@id=\"content\"]/form/fieldset/div/div/div[2]/label");





    public String getFirstSectionHeaderText() {
        return driver.findElement(firstSectionHeader).getText();
    }

    public String getEditAccountElementText() {
        return driver.findElement(editAccountElement).getText();
    }

    public String getChangePasswordElementText() {
        return driver.findElement(changePasswordElement).getText();
    }

    public String getModifyAddressElementText() {
        return driver.findElement(modifyAddressElement).getText();
    }

    public String getMyWishlistElementText() {
        return driver.findElement(myWishlistElement).getText();
    }

    public String getNewsletterElementText() {
        return driver.findElement(newsletterElement).getText();
    }
    public void clickEditAccountElement() {
        driver.findElement(editAccountElement).click();

    }
    public String getUpdatedMessage() {
        return driver.findElement(updatedMessage).getText();
    }
    public void clickAddressBook() {
        driver.findElement(modifyAddressElement).click();
    }
    public String getAdressBookTxt() {
       return driver.findElement(addressBookTxt).getText();
    }
    public void clickNewsletterElement() {
        driver.findElement(newsletterElement).click();
    }
    public void clickYesSubscription() {
        driver.findElement(yesSubscription).click();
    }
    public void clickNoSubscription() {
        driver.findElement(noSubscription).click();
    }


}
