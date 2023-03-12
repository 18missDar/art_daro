package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void waitForSearchResults(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        wait.until(ExpectedConditions.visibilityOfAllElements(paintingImages));
    }

    public boolean isPaintingInSearchResults(String paintingDetails) {
        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        for (WebElement img : paintingImages) {
            if (img.getAttribute("alt").equals(paintingDetails)) {
                return true;
            }
        }
        return false;
    }

}
