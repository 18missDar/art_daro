package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//ul[@class='product_list']/li")
    private List<WebElement> searchResults;

    @FindBy(css = "#genre257")
    private WebElement genreCheckbox;

    @FindBy(css = ".arrowdiv")
    private WebElement searchButton;

    @FindBy(css = "#left_container > div > ul:nth-child(2) > li.menu-group.gids > div > i")
    private WebElement detailButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[2]/div/ul[2]/li[8]/a")
    private WebElement embroideredPaintingsLink;

    @FindBy(xpath = "//*[@id=\"left_container\"]/div/ul[2]/li[3]/a")
    private WebElement batikLink;


    @FindBy(xpath = "//*[@id=\"left_container\"]/div/ul[2]/li[5]/a")
    private WebElement jewelleryLink;


    @FindBy(css = ".bubu")
    private List<WebElement> paintingImages;

    @FindBy(css = ".price")
    private List<WebElement> prices;

    @FindBy(css = ".oclick")
    private List<WebElement> buyButtons;




    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToJewellery() {
        detailButton.click();
        jewelleryLink.click();
    }

    public void navigateToBatik() {
        detailButton.click();
        batikLink.click();
    }

    public void navigateToEmbroideredPaintings() {
        detailButton.click();
        embroideredPaintingsLink.click();
    }



    public void clickFirstBuyButton(){
        buyButtons.get(0).click();
    }

    public void clickGenreCheckbox() {
        genreCheckbox.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getFirstPrice(){
        return prices.get(0).getText();
    }

    public String getTextOfFirstPicture(){
        String textOfFirstPicture = paintingImages.get(0).getAttribute("alt");
        return textOfFirstPicture.split("\\.")[0];
    }

    public void waitForSearchResults(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        wait.until(ExpectedConditions.visibilityOfAllElements(paintingImages));
    }

    public boolean isPaintingInSearchResults(String paintingDetails) {
        List<WebElement> paintingImages = driver.findElements(By.cssSelector(".bubu"));
        for (WebElement img : paintingImages) {
            if (img.getAttribute("alt").equals(paintingDetails)) {
                return true;
            }
        }
        return false;
    }

}
