import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import pages.SearchResultsPage;

public class Task21 {

    @Test
    @Description("task 2.1 Google")
    public void testGenreSearch(){
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
        String expectedElement = "Трамвайный путь. Гвоздецкая Татьяна";
        Boolean result = searchResultsPage.isPaintingInSearchResults(expectedElement);
        if (result) {
            System.out.println("The painting exists on the page");
        } else {
            System.out.println("The painting does not exist on the page");
        }
        // Quit the driver when the test is complete
        driver.quit();
    }


    @Test
    @Description("task 2.1 Firefox")
    public void testGenreSearch2(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://artnow.ru/vyshitye-kartiny-kupit.html");
        WebElement genreCheckbox = driver.findElement(By.id("genre257"));
        genreCheckbox.click();
        WebElement searchButton = driver.findElement(By.cssSelector(".arrowdiv"));
        searchButton.click();
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        String expectedElement = "Трамвайный путь. Гвоздецкая Татьяна";
        Boolean result = searchResultsPage.isPaintingInSearchResults(expectedElement);
        if (result) {
            System.out.println("The painting exists on the page FireFox");
        } else {
            System.out.println("The painting does not exist on the page Firefox");
        }
        driver.quit();
    }


}
