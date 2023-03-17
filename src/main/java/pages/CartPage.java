package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"cmodal\"]/div/p/button[1]")
    private WebElement goToChartLink;

    @FindBy(css = ".c_name")
    private List<WebElement> titles;

    @FindBy(css = ".price")
    private List<WebElement> prices;

    public void navigateToChart(){
        goToChartLink.click();
    }

    public String firstProductCartPrice(){
        return prices.get(0).getText();
    }

    public String firstTitleProductCart(){
        return titles.get(0).getText();
    }
}
