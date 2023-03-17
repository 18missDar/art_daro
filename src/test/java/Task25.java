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
import pages.CartPage;
import pages.SearchResultsPage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Task25 {

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
    @Description("Перейти в “Ювелирное искусство”, добавить первое изделие в\n" +
            "корзину, проверить, что выбранный товар находится в корзине, стоимость\n" +
            "товара не изменилась")
    public void test(){
        driver.get("https://artnow.ru/");
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.navigateToJewellery();

        String textOfFirstJewellery = searchResultsPage.getTextOfFirstPicture();
        String priceInitial = searchResultsPage.getFirstPrice();

        System.out.println(textOfFirstJewellery);
        System.out.println(priceInitial);

        searchResultsPage.clickFirstBuyButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cmodal-guts")));

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToChart();
        String actualTitle = cartPage.firstTitleProductCart();
        String actualPrice = cartPage.firstProductCartPrice();

        Assert.assertTrue(actualTitle.contains(textOfFirstJewellery));
        Assert.assertEquals(priceInitial, actualPrice);

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
