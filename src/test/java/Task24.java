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

import java.io.ByteArrayInputStream;
import java.util.List;

public class Task24 {

    private WebDriver driver;

    @Test
    @Description("task 2.4 Google")
    public void testGoogle(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/");

        // Find the search box element and enter "Giraffe"
        WebElement searchBox = driver.findElement(By.name("qs"));
        searchBox.sendKeys("Жираф");
        searchBox.submit();

        // Wait for the search results page to load
        WebElement searchResultsHeader = driver.findElement(By.cssSelector("#main_container > h1"));
        Assert.assertTrue(searchResultsHeader.getText().contains("Подбор картин и работ"));

        // Find the first painting element and check if its name contains "Giraffe"
        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        String firstPaintingName = paintingImages.get(0).getAttribute("alt");
        Assert.assertTrue(firstPaintingName.contains("Жираф"));

        driver.quit();
    }


    @Test
    @Description("task 2.4 Firefox")
    public void testFirefox(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/");

        // Find the search box element and enter "Giraffe"
        WebElement searchBox = driver.findElement(By.name("qs"));
        searchBox.sendKeys("Жираф");
        searchBox.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html body div.wrapper div.table div#main_container.content_container h1.h2.lft")));
        // Find the first painting element and check if its name contains "Giraffe"
        WebElement firstPainting = driver.findElement(By.cssSelector("html body div.wrapper div.table div#main_container.content_container div#sa_container div.post a img.bubu"));
        String firstPaintingName = firstPainting.getAttribute("alt");
        Assert.assertTrue(firstPaintingName.contains("Жираф"));

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
