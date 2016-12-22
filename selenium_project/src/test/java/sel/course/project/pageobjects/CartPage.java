package sel.course.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Olga on 12/20/2016.
 */
public class CartPage {
    private WebDriver wd;
    private WebDriverWait wait;

    public CartPage(WebDriver w, WebDriverWait wt){
        wd = w;
        wait = wt;
        PageFactory.initElements(wd, this);
    }

    @FindBy(css = "div#cart-wrapper a.link")
    private WebElement cartLink;

    @FindBy(css = "button[name='remove_cart_item']")
    private WebElement buttonRemove;

    @FindBy(css = "div#checkout-cart-wrapper p em")
    private WebElement noItems;

    public void openCart(){
        cartLink.click();
        wait.until(titleIs("Checkout | My Store"));
    }

    public int checkOrderTable(){
        List<WebElement> rows = wd.findElements(By.cssSelector("table.dataTable tr"));
        int rowsSizePrev = rows.size();
        System.out.println("Order Summary Table contains:");
        for (int i = 1; i < rowsSizePrev-4; i++) {
            String itemName = rows.get(i).findElements(By.cssSelector("td")).get(1).getText();
            System.out.println("\t"+itemName);
        }
        return rowsSizePrev;
    }

    public void removeItem(int rowsSizePrev){
        buttonRemove.click();
        System.out.println("Removed one item");

        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));
    }

    public void waitForTableToBeUpdated(int rowsSizePrev){
        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));
    }

    public void checkAllItemsDeleted(){
        wait.until(visibilityOfElementLocated(By.cssSelector("div#checkout-cart-wrapper p em")));
        String noItemsText = noItems.getText();
        System.out.println(noItemsText);
    }
}
