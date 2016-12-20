package sel.course.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    }

    public void buyItem(){
        wd.findElement(By.cssSelector("div.buy_now button[name='add_cart_product']")).click();
    }


    public void checkCartNumber(int previousNumber){
        String expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));
        WebElement cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        String cartItem = cartItems.getText();
        System.out.println("There are " + cartItem + " items in Shopping cart");
    }
}
