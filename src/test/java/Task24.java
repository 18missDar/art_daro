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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FilterResultPage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Task24 {

    private WebDriver driver;

    private String browser = "firefox";

    @BeforeMethod
    public void setup() {
        if (browser.equalsIgnoreCase("chrome")) {
            // Set the path to the Chrome driver executable
            System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");
            // Create a new instance of the Chrome driver
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
    }

    @Test
    @Description("Ввести в поисковую строку «Жираф», проверить, что название первой\n" +
            "картины содержит слово «Жираф»")
    public void test(){
        driver.get("https://artnow.ru/");

        FilterResultPage filterResultPage = new FilterResultPage(driver);
        filterResultPage.enterGiraffe();

        // Wait for the search results page to load
        WebElement searchResultsHeader = driver.findElement(By.cssSelector("#main_container > h1"));
        Assert.assertTrue(searchResultsHeader.getText().contains("Подбор картин и работ"));

        String firstPaintingName = filterResultPage.getFirstPictureName();
        Assert.assertTrue(firstPaintingName.contains("Жираф"));

        driver.quit();
    }


    @Test
    @Description("Ввести в поисковую строку «Жираф», проверить, что название первой\n" +
            "картины содержит слово «Жираф»")
    public void testFirefox(){
        driver.get("https://artnow.ru/");

        FilterResultPage filterResultPage = new FilterResultPage(driver);
        filterResultPage.enterGiraffe();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html body div.wrapper div.table div#main_container.content_container h1.h2.lft")));

        String firstPaintingName = filterResultPage.getFirstPictureName();
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
