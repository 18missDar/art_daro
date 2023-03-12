import io.qameta.allure.Description;
import junit.runner.BaseTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest{

    @Test
    @Description("Verify that the homepage can be opened")
    public void testHomePage() {
        // Set the path to the Chrome driver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/google_browser/chromedriver.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.verifyHomePageIsLoaded();
    }

}