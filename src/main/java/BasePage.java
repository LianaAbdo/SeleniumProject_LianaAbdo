
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
   WebDriver driver ;
    private By warningMessage = By.xpath(".//div[@class = 'alert alert-danger alert-dismissible']");
    private By wishlistHeartElement= By.xpath("//a[@aria-label='Wishlist']");
    private By searchInput= By.name("search");



    public void clickWishlist(){
        driver.findElement(wishlistHeartElement).click();
    }
    public void enterTextSearch(String searchText){
        driver.findElement(searchInput).sendKeys(searchText);
    }
    public String getWarningMessage() {
        return driver.findElement(warningMessage).getText();
    }



}

