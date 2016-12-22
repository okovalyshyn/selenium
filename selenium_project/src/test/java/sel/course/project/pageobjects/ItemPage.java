package sel.course.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

/**
 * Created by Olga on 12/20/2016.
 */
public class ItemPage {
    private WebDriver wd;
    private WebDriverWait wait;

    public ItemPage(WebDriver w, WebDriverWait wt){
        wd = w;
        wait = wt;
        PageFactory.initElements(wd, this);
    }

    @FindBy(css = "div.buy_now button[name='add_cart_product']")
    private WebElement buyNow;

    @FindBy(css = "div#cart a.content span.quantity")
    private WebElement cartItems;

    public void buyItem(){
        buyNow.click();
    }


    public void checkCartNumber(int previousNumber){
        String expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));
        String cartItem = cartItems.getText();
        System.out.println("There are " + cartItem + " items in Shopping cart");
    }
}
