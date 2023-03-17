import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.qameta.allure.Description;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class UpdatedTask21 {

    private WebDriver driver;

    @Given("I am on the ArtNow homepage")
    public void iAmOnTheArtNowHomepage() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/");
    }

    @When("I navigate to the Embroidered Paintings page")
    public void iNavigateToTheEmbroideredPaintingsPage() {
        WebElement detailButton = driver.findElement(By.cssSelector("#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i"));
        detailButton.click();
        WebElement embroidered_paintings = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/ul[2]/li[8]/a"));
        embroidered_paintings.click();
    }

    @When("I select the {string} genre")
    public void iSelectTheGenre(String genre) {
        WebElement genreCheckbox = driver.findElement(By.id("genre257"));
        genreCheckbox.click();
    }

    @When("I search for paintings")
    public void iSearchForPaintings() {
        WebElement searchButton = driver.findElement(By.cssSelector(".arrowdiv"));
        searchButton.click();
    }
}
