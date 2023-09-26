import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
//creating a constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }
    private By emailAddressInput = By.id("input-email") ;
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/input");
    private By myAccountText = By.xpath("//*[@id=\"account-account\"]/nav/ol/li[2]");
    private By forgotPassword = By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/div[2]/a");
    private By forgotPasswordMessage = By.xpath("//*[@id=\"content\"]/h1");



    public void insertEmailAddress(String emailAddress) {
        driver.findElement(emailAddressInput).sendKeys(emailAddress);

    }public String getMyAccountText() {
        return driver.findElement(myAccountText).getText();
    }


    public void setPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }
    public String getForgotPasswordText() {
        return driver.findElement(forgotPasswordMessage).getText();
    }

}
