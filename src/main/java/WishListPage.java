import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends BasePage{
    public WishListPage(WebDriver driver) {
        this.driver = driver;

    }
    private By noResultsElement= By.xpath(".//div[@id='content']/p");
    private By searchButton= By.xpath(".//button[@class='type-text']");

    public String getNoResultsElementText(){
        return driver.findElement(noResultsElement).getText();
    }
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}
