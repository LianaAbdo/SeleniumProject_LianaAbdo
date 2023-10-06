
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    private By resultItems = By.xpath(".//div[@class = 'carousel-item active']/img[contains(@title, 'Apple Cinema')]");
    private By addToWishlist = By.xpath(".//button[@title = 'Add to Wish List']");
    private By closePopupButton = By.xpath(".//button[@aria-label = 'Close']");
    private By thirdSamsung = By.xpath("//*[@id=\"entry_212469\"]/div/div[3]/div/div[2]/h4/a");



    public void clickFirstItem(){
        driver.findElements(resultItems).get(0).click();

    }
    public WebElement getFirstItem() {
        return driver.findElements(resultItems).get(0);
    }

    public void addItemToWishlist() {
        driver.findElements(addToWishlist).get(0).click();

    }
    public WebElement getAddToWishlistButton() {
        return driver.findElements(addToWishlist).get(0);
    }

    public void clickClosePopupButton() {
        driver.findElement(closePopupButton).click();
    }
    public void clickThirdItemSamsung() {
        driver.findElement(thirdSamsung).click();

    }
}
