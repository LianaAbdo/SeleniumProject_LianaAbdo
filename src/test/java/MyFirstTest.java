import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTest {
    @Test
   public void firstTest() {
        //write 'youtube' on search box and make sure it returns no result
        WebDriver driver = new ChromeDriver();
        driver.get("http://testfasttrackit.info/magento-test/");
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("Youtube");
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"search_mini_form\"]/div[1]/button"));
        searchButton.click();
        WebElement searchResultTextElement = driver.findElement(By.className("note-msg"));
        String actualText = searchResultTextElement.getText();
        String expectedText = "Your search returns no results.";

        Assert.assertEquals(actualText,expectedText,"Search result text is not the expected one");
        driver.quit();



   }
   @Test
   public void compareLogo() {
        //check that if u click on the logo, it returns the main page

        WebDriver driver = new ChromeDriver();

        driver.get("http://testfasttrackit.info/magento-test/");
        WebElement searchLogo = driver.findElement(By.xpath("//*[@id=\"header\"]/div/a/img[1]"));
        searchLogo.click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "http://testfasttrackit.info/magento-test/";

        Assert.assertEquals(actualUrl, expectedUrl,"Clicking on logo doesn't return main page");
        driver.quit();
   }
}
