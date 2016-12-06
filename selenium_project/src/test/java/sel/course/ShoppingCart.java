package sel.course;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//Created by okovalyshyn on 12/5/2016.
public class ShoppingCart extends SetDriver{
    String firstItemTitle = "Blue Duck";
    String secondItemTitle = "Purple Duck";
    String thirdItemTitle = "Green Duck";

    @Test
    public void shoppingCart(){
        wd.navigate().to("http://localhost/litecart/en/");

        WebElement cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        String cartItem = cartItems.getText();
        int previousNumber = Integer.parseInt(String.valueOf(cartItem));
        System.out.println("Start. Shopping cart contains "+previousNumber+" items");

        //first item
        wd.findElement(By.cssSelector("div#box-most-popular a.link[title='"+firstItemTitle+"']")).click();
        wait.until(titleIs(firstItemTitle+" | Rubber Ducks | My Store"));

        wd.findElement(By.cssSelector("div.buy_now button[name='add_cart_product']")).click();
        previousNumber++;
        String expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));

        cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        cartItem = cartItems.getText();
        System.out.println("There are "+cartItem+" items in Shopping cart");


        //second item
        wd.navigate().to("http://localhost/litecart/en/");
        wd.findElement(By.cssSelector("div#box-most-popular a.link[title='"+secondItemTitle+"']")).click();
        wait.until(titleIs(secondItemTitle+" | Rubber Ducks | My Store"));

        wd.findElement(By.cssSelector("div.buy_now button[name='add_cart_product']")).click();
        previousNumber++;
        expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));

        cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        cartItem = cartItems.getText();
        System.out.println("There are "+cartItem+" items in Shopping cart");

        //third item
        wd.navigate().to("http://localhost/litecart/en/");
        wd.findElement(By.cssSelector("div#box-most-popular a.link[title='"+thirdItemTitle+"']")).click();
        wait.until(titleIs(thirdItemTitle+" | Subcategory | Rubber Ducks | My Store"));

        wd.findElement(By.cssSelector("div.buy_now button[name='add_cart_product']")).click();
        previousNumber++;
        expectedNumber = String.valueOf(previousNumber);
        wait.until(textToBePresentInElementLocated(By.cssSelector("div#cart a.content span.quantity"), expectedNumber));

        cartItems = wd.findElement(By.cssSelector("div#cart a.content span.quantity"));
        cartItem = cartItems.getText();
        System.out.println("There are "+cartItem+" items in Shopping cart");

        // Actions with Shopping Cart
        wd.findElement(By.cssSelector("div#cart-wrapper a.link")).click();
        wait.until(titleIs("Checkout | My Store"));

        List<WebElement> rows = wd.findElements(By.cssSelector("table.dataTable tr"));
        int rowsSizePrev = rows.size();
        System.out.println("Order Summary Table contains:");
        for (int i = 1; i < rowsSizePrev-4; i++) {//last 4th rows don't contain purchase items
            String itemName = rows.get(i).findElements(By.cssSelector("td")).get(1).getText();
            System.out.println("\t"+itemName);
        }

        WebElement buttonRemove = wd.findElement(By.cssSelector("button[name='remove_cart_item']"));
        buttonRemove.click();
        System.out.println("Removed one item");
        //wait.until(stalenessOf(buttonRemove));

        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));

        rows = wd.findElements(By.cssSelector("table.dataTable tr"));
        rowsSizePrev = rows.size();
        System.out.println("Order Summary Table contains:");
        for (int i = 1; i < rowsSizePrev-4; i++) {
            String itemName = rows.get(i).findElements(By.cssSelector("td")).get(1).getText();
            System.out.println("\t"+itemName);
        }

        buttonRemove = wd.findElement(By.cssSelector("button[name='remove_cart_item']"));
        buttonRemove.click();
        System.out.println("Removed one item");

        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));

        rows = wd.findElements(By.cssSelector("table.dataTable tr"));
        rowsSizePrev = rows.size();
        System.out.println("Order Summary Table contains:");
        for (int i = 1; i < rowsSizePrev-4; i++) {
            String itemName = rows.get(i).findElements(By.cssSelector("td")).get(1).getText();
            System.out.println("\t"+itemName);
        }

        buttonRemove = wd.findElement(By.cssSelector("button[name='remove_cart_item']"));
        buttonRemove.click();
        System.out.println("Removed one item");

        wait.until(numberOfElementsToBeLessThan(By.cssSelector("table.dataTable tr"), rowsSizePrev));
        wait.until(visibilityOfElementLocated(By.cssSelector("div#checkout-cart-wrapper p em")));
        WebElement noItems = wd.findElement(By.cssSelector("div#checkout-cart-wrapper p em"));
        String noItemsText = noItems.getText();
        System.out.println(noItemsText);
    }
}
