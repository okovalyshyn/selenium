package sel.course.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sel.course.SetDriver;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//Created by okovalyshyn on 12/19/2016.
public class ShoppingCartNew extends SetDriver {
    String firstItemTitle = "Blue Duck";
    String secondItemTitle = "Red Duck";
    String thirdItemTitle = "Purple Duck";
    WebElement cartItems;
    String cartItem;
    int previousNumber;
    String expectedNumber;
    List<WebElement> rows;
    int rowsSizePrev;
    WebElement buttonRemove;
    WebElement noItems;



    @Test
    public void shoppingCartPO(){
        wd.navigate().to("http://localhost/litecart/en/");
        checkPreviousCartNumber();

        //first item
        selectItem(firstItemTitle);
        checkCartNumber();

        //second item
        wd.navigate().to("http://localhost/litecart/en/");
        selectItem(secondItemTitle);
        checkCartNumber();

        //third item
        wd.navigate().to("http://localhost/litecart/en/");
        selectItem(thirdItemTitle);
        checkCartNumber();

        // Actions with Shopping Cart
        openCart();
        checkOrderTable();

        removeItem();
        waitForTableToBeUpdated();
        checkOrderTable();

        removeItem();
        waitForTableToBeUpdated();
        checkOrderTable();

        removeItem();
        waitForTableToBeUpdated();
        checkAllItemsDeleted();
    }


    private void selectItem(String itemTitle){
        wd.findElement(By.cssSelector("div#box-most-popular a.link[title='"+itemTitle+"']")).click();
        wait.until(titleIs(itemTitle+" | Rubber Ducks | My Store"));
        wd.findElement(By.cssSelector("div.buy_now button[name='add_cart_product']")).click();
        previousNumber++;
    }

    private void checkPreviousCartNumber(){
        cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        String cartItem = cartItems.getText();
        int previousNumber = Integer.parseInt(String.valueOf(cartItem));
        System.out.println("Shopping cart contains "+previousNumber+" items");
    }

    private void checkCartNumber(){
        expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));
        cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        cartItem = cartItems.getText();
        System.out.println("There are "+cartItem+" items in Shopping cart");
    }

    private void openCart(){
        wd.findElement(By.cssSelector("div#cart-wrapper a.link")).click();
        wait.until(titleIs("Checkout | My Store"));
    }

    private void checkOrderTable(){
        rows = wd.findElements(By.cssSelector("table.dataTable tr"));
        rowsSizePrev = rows.size();
        System.out.println("Order Summary Table contains:");
        for (int i = 1; i < rowsSizePrev-4; i++) {
            String itemName = rows.get(i).findElements(By.cssSelector("td")).get(1).getText();
            System.out.println("\t"+itemName);
        }
    }

    private void removeItem(){
        buttonRemove = wd.findElement(By.cssSelector("button[name='remove_cart_item']"));
        buttonRemove.click();
        System.out.println("Removed one item");

        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));
    }

    private void waitForTableToBeUpdated(){
        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));
    }

    private void checkAllItemsDeleted(){
        wait.until(visibilityOfElementLocated(By.cssSelector("div#checkout-cart-wrapper p em")));
        WebElement noItems = wd.findElement(By.cssSelector("div#checkout-cart-wrapper p em"));
        String noItemsText = noItems.getText();
        System.out.println(noItemsText);
    }
}
