package pages;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchField = By.cssSelector("a[href='//artnow.ru/']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    @Description("Open artnow.ru homepage")
    public HomePage open() {
        driver.get("https://artnow.ru/");
        return this;
    }

    @Description("Verify that the homepage is loaded")
    public void verifyHomePageIsLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchField));
        Assert.assertEquals(driver.getTitle(), "Купить картины современных художников и другие произведения от 1000р");
    }
}
