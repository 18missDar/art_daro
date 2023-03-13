import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.SearchResultsPage;

import java.io.ByteArrayInputStream;

public class Task22 {

    private WebDriver driver;

    @Test
    @Description("task 2.2 Google")
    public void testGoogle(){
        // Set the path to the Chrome driver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");
        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to the paintings by genre page
        driver.get("https://artnow.ru/");
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement embroidered_paintings = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul[2]/li[8]/a"));
        embroidered_paintings.click();
        // Find the checkbox for the desired genre and click it
        WebElement genreCheckbox = driver.findElement(By.id("genre257"));
        genreCheckbox.click();
        // Submit the search form and wait for the results page to load
        WebElement searchButton = driver.findElement(By.cssSelector(".arrowdiv"));
        searchButton.click();
        // check that the painting exists on the page
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.waitForSearchResults(driver);
        WebElement tramPathPainting = driver.findElement(By.cssSelector("#sa_container > div:nth-child(5) > a"));
        tramPathPainting.click();
        WebElement style = driver.findElement(By.cssSelector("#main_container > div:nth-child(3) > div.infocontainer > div:nth-child(9) > a"));
        Assert.assertEquals("Реализм", style.getText());
        // Quit the driver when the test is complete
        driver.quit();
    }

    @Test
    @Description("task 2.2 Firefox")
    public void testFirefox(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        this.driver = driver;
        driver.get("https://artnow.ru/");
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement embroidered_paintings = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul[2]/li[8]/a"));
        embroidered_paintings.click();
        WebElement genreCheckbox = driver.findElement(By.id("genre257"));
        genreCheckbox.click();
        WebElement searchButton = driver.findElement(By.cssSelector(".arrowdiv"));
        searchButton.click();
        WebElement tramPathPainting = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("html body div.wrapper div.table div#main_container.content_container div#sa_container div.post a img.bubu")));
        tramPathPainting.click();
        WebElement style = driver.findElement(By.cssSelector("div.txtline:nth-child(9) > a:nth-child(2)"));
        Assert.assertEquals("Реализм", style.getText());
        driver.quit();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Take a screenshot and attach it to the Allure report
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            addAttachment("Screenshot", "image/png", screenshotBytes, "png");
        }
    }

    private void addAttachment(String attachmentName, String attachmentType, byte[] attachmentContent, String attachmentExtension) {
        Allure.addAttachment(attachmentName, attachmentType, new ByteArrayInputStream(attachmentContent), attachmentExtension);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
