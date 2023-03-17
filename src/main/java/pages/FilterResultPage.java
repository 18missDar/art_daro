package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilterResultPage extends BasePage{
    public FilterResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "qs")
    private WebElement searchBox;

    @FindBy(css = ".bubu")
    private List<WebElement> paintingImages;


    public void enterGiraffe(){
        searchBox.sendKeys("Жираф");
        searchBox.submit();
    }

    public String getFirstPictureName(){
        return paintingImages.get(0).getAttribute("alt");
    }
}
