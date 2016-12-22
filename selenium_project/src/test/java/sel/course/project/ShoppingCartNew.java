package sel.course.project;

import org.junit.Test;
import sel.course.project.pageobjects.Application;


//Created by okovalyshyn on 12/19/2016.
public class ShoppingCartNew  {
    String firstItemTitle = "Blue Duck";
    String secondItemTitle = "Red Duck";
    String thirdItemTitle = "Purple Duck";
    int previousNumber;
    int rowsSizePrev;


    @Test
    public void shoppingCartPO(){
        Application app = new Application();

        app.goToPage("http://localhost/litecart/en/");

        previousNumber = app.mainPage.getPreviousCartNumber();

        app.mainPage.selectItem(firstItemTitle);
        app.itemPage.buyItem();
        previousNumber++;
        app.itemPage.checkCartNumber(previousNumber);

        app.goToPage("http://localhost/litecart/en/");

        app.mainPage.selectItem(secondItemTitle);
        app.itemPage.buyItem();
        previousNumber++;
        app.itemPage.checkCartNumber(previousNumber);

        app.goToPage("http://localhost/litecart/en/");

        app.mainPage.selectItem(thirdItemTitle);
        app.itemPage.buyItem();
        previousNumber++;
        app.itemPage.checkCartNumber(previousNumber);

        app.cartPage.openCart();
        rowsSizePrev = app.cartPage.checkOrderTable();

        app.cartPage.removeItem(rowsSizePrev);
        app.cartPage.waitForTableToBeUpdated(rowsSizePrev);
        rowsSizePrev = app.cartPage.checkOrderTable();

        app.cartPage.removeItem(rowsSizePrev);
        app.cartPage.waitForTableToBeUpdated(rowsSizePrev);
        rowsSizePrev = app.cartPage.checkOrderTable();

        app.cartPage.removeItem(rowsSizePrev);
        app.cartPage.waitForTableToBeUpdated(rowsSizePrev);
        app.cartPage.checkAllItemsDeleted();

        app.quit();
    }
}
