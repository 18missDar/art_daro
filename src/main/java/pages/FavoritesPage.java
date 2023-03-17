package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FavoritesPage extends BasePage {

    public FavoritesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/span[6]/img")
    private WebElement izbranoePage;

    @FindBy(css = ".bubu")
    private List<WebElement> paintingImagesIzbrannoe;

    @FindBy(xpath = "//*[@id=\"heart1099595\"]")
    private WebElement favouriteButton;

    public void clickIzbranoePage() {
        izbranoePage.click();
    }

    public void clickFavorite() {
        favouriteButton.click();
    }

    public String getTextOfFirstIzbrannoe() {
        return paintingImagesIzbrannoe.get(0).getAttribute("alt");
    }

}

