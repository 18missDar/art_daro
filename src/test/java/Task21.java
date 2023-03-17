import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.SearchResultsPage;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

import static io.qameta.allure.Allure.addAttachment;


public class Task21 {

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
    @Description("Перейти в “Вышитые картины”, произвести поиск по жанру\n" +
            "«Городской пейзаж», проверить, что картина “Трамвайный путь”\n" +
            "присутствует в выдаче")
    public void test(){
        driver.get("https://artnow.ru/");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.navigateToEmbroideredPaintings();
        searchResultsPage.clickGenreCheckbox();
        searchResultsPage.clickSearchButton();
        //searchResultsPage.waitForSearchResults(driver);
        String expectedElement = "Трамвайный путь. Гвоздецкая Татьяна";
        Boolean result = searchResultsPage.isPaintingInSearchResults(expectedElement);
        Assert.assertEquals(true, result);
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
