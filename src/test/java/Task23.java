import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Task23 {
    @Test
    @Description("task 2.3 Google")
    public void testGoogle(){
        // Set the path to the Chrome driver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");
        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to the paintings by genre page
        driver.get("https://artnow.ru/");
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement batik = driver.findElement(By.xpath("//*[@id=\"left_container\"]/div/ul[2]/li[3]/a"));
        batik.click();

        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstPicture = paintingImages.get(0).getAttribute("alt");
        WebElement izbranoe = driver.findElement(By.xpath("//*[@id=\"heart1099595\"]"));
        izbranoe.click();

        WebElement izbranoePage = driver.findElement(By.xpath("/html/body/div[1]/span[6]/img"));
        izbranoePage.click();
        List<WebElement> paintingImagesIzbrannoe = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstIzbrannoe = paintingImagesIzbrannoe.get(0).getAttribute("alt");

        Assert.assertEquals(textOfFirstPicture.split("\\.")[0], textOfFirstIzbrannoe);
        driver.quit();
    }

    @Test
    @Description("task 2.3 Firefox")
    public void testFirefox(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/");
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement batik = driver.findElement(By.xpath("//*[@id=\"left_container\"]/div/ul[2]/li[3]/a"));
        batik.click();

        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstPicture = paintingImages.get(0).getAttribute("alt");
        WebElement izbranoe = driver.findElement(By.xpath("//*[@id=\"heart1099595\"]"));
        izbranoe.click();

        WebElement izbranoePage = driver.findElement(By.xpath("/html/body/div[1]/span[6]/img"));
        izbranoePage.click();
        List<WebElement> paintingImagesIzbrannoe = driver.findElements(By.cssSelector(".bubu"));
        String textOfFirstIzbrannoe = paintingImagesIzbrannoe.get(0).getAttribute("alt");

        Assert.assertEquals(textOfFirstPicture.split("\\.")[0], textOfFirstIzbrannoe);
        driver.quit();
    }

}
