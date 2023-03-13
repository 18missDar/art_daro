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

public class Task25 {

    private WebDriver driver;

    @Test
    @Description("task 2.5 Google")
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
        WebElement jewellery = driver.findElement(By.xpath("//*[@id=\"left_container\"]/div/ul[2]/li[5]/a"));
        jewellery.click();

        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstJewellery = paintingImages.get(0).getAttribute("alt");
        WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"sa_container\"]/div[2]/div[1]"));
        String priceInitial = priceElement.getText();

        System.out.println(textOfFirstJewellery);
        System.out.println(priceInitial);

        WebElement buyButton = driver.findElement(By.xpath("//*[@id=\"CartButton1093263\"]"));
        buyButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cmodal-guts")));

        WebElement goToChart = driver.findElement(By.xpath("//*[@id=\"cmodal\"]/div/p/button[1]"));
        goToChart.click();

        WebElement firstProductFromChart = driver.findElement(By.xpath("//*[@id=\"cart1093263\"]/div[3]/div[1]/a"));
        System.out.println(firstProductFromChart.getText());
        WebElement firstProductFromChartPrice = driver.findElement(By.xpath("//*[@id=\"cart1093263\"]/div[3]/div[5]/div[2]"));
        System.out.println(firstProductFromChartPrice.getText());

        Assert.assertEquals(textOfFirstJewellery.split("\\. Л")[0], firstProductFromChart.getText());
        Assert.assertEquals(priceInitial, firstProductFromChartPrice.getText());

        driver.quit();
    }

    @Test
    @Description("task 2.5 FireFox")
    public void testFireFox(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/");
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement jewellery = driver.findElement(By.xpath("//*[@id=\"left_container\"]/div/ul[2]/li[5]/a"));
        jewellery.click();

        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstJewellery = paintingImages.get(0).getAttribute("alt");
        WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"sa_container\"]/div[2]/div[1]"));
        String priceInitial = priceElement.getText();

        System.out.println(textOfFirstJewellery);
        System.out.println(priceInitial);

        WebElement buyButton = driver.findElement(By.xpath("//*[@id=\"CartButton1093263\"]"));
        buyButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cmodal-guts")));

        WebElement goToChart = driver.findElement(By.xpath("//*[@id=\"cmodal\"]/div/p/button[1]"));
        goToChart.click();

        WebElement firstProductFromChart = driver.findElement(By.xpath("//*[@id=\"cart1093263\"]/div[3]/div[1]/a"));
        System.out.println(firstProductFromChart.getText());
        WebElement firstProductFromChartPrice = driver.findElement(By.xpath("//*[@id=\"cart1093263\"]/div[3]/div[5]/div[2]"));
        System.out.println(firstProductFromChartPrice.getText());

        Assert.assertEquals(textOfFirstJewellery.split("\\. Л")[0], firstProductFromChart.getText());
        Assert.assertEquals(priceInitial, firstProductFromChartPrice.getText());

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
