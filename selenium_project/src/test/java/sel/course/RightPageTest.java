package sel.course;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


//Created by okovalyshyn on 11/29/2016.
public class RightPageTest extends SetDriver {

    @Test
    public void rightRage(){
        wd.navigate().to("http://localhost/litecart/en/");
        System.out.println("Checking the next page: " + wd.getCurrentUrl());

        WebElement firstItem = wd.findElement(By.xpath(".//*[@id='box-campaigns']//ul/li[1]"));

        WebElement itemNameMain = firstItem.findElement(By.cssSelector("div.name"));
        String itemName = itemNameMain.getAttribute("textContent");

        WebElement itemOldPriceMain = firstItem.findElement(By.cssSelector("s"));
        String itemOldPrice = itemOldPriceMain.getAttribute("textContent");
        String itemOldPriceClass = itemOldPriceMain.getAttribute("class");


        WebElement itemNewPriceMain = firstItem.findElement(By.cssSelector("strong"));
        String itemNewPrice = itemNewPriceMain.getAttribute("textContent");
        String itemNewPriceClass = itemNewPriceMain.getAttribute("class");

        firstItem.click();
        System.out.println("Checking the next page: " + wd.getCurrentUrl());

        WebElement itemNameOpenedWindow = wd.findElement(By.cssSelector("div#box-product h1"));
        String itemNameOpened = itemNameOpenedWindow.getAttribute("textContent");

        WebElement contentOpenedW = wd.findElement(By.cssSelector("div#box-product"));

        WebElement itemOldPriceOpenedW = contentOpenedW.findElement(By.cssSelector("s"));
        String itemOldPriceOpened = itemOldPriceOpenedW.getAttribute("textContent");
        String itemOldPriceClassOpened = itemOldPriceOpenedW.getAttribute("class");

        WebElement itemNewPriceOpenedW = contentOpenedW.findElement(By.cssSelector("strong"));
        String itemNewPriceOpened = itemNewPriceOpenedW.getAttribute("textContent");
        String itemNewPriceClassOpened = itemNewPriceOpenedW.getAttribute("class");


        if (!(itemName.equals(itemNameOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " + assertError.getMessage());
            System.out.println("Name on the main page ("+ itemName +") is different than on the opened window ("+itemNameOpened+")" );
            Assert.fail();
        }
        System.out.println("Item's Name on the main page ("+ itemName +") is the same as on the opened window ("+itemNameOpened+")" );

        if (!(itemOldPrice.equals(itemOldPriceOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " + assertError.getMessage());
            System.out.println("Item's Old Price on the main page ("+ itemOldPrice +") is different than on the opened window ("+itemOldPriceOpened+")" );
            Assert.fail();
        }
        System.out.println("Item's Old Price on the main page ("+ itemOldPrice +") is the same as on the opened window ("+itemOldPriceOpened+")" );

        if (!(itemOldPriceClass.equals(itemOldPriceClassOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " + assertError.getMessage());
            System.out.println("Old Price's Class on the main page ("+ itemOldPriceClass +") is different than on the opened window ("+itemOldPriceClassOpened+")" );
            Assert.fail();
        }
        System.out.println("Old Price's Class on the main page ("+ itemOldPriceClass +") is the same as on the opened window ("+itemOldPriceClassOpened+")" );

        if (!(itemNewPrice.equals(itemNewPriceOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " + assertError.getMessage());
            System.out.println("Item's New Price on the main page ("+ itemNewPrice +") is different than on the opened window ("+itemNewPriceOpened+")" );
            Assert.fail();
        }
        System.out.println("Item's New Price on the main page ("+ itemNewPrice +") is the same as on the opened window ("+itemNewPriceOpened+")" );

        if (!(itemNewPriceClass.equals(itemNewPriceClassOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " + assertError.getMessage());
            System.out.println("New Price's Class on the main page ("+ itemNewPriceClass +") is different than on the opened window ("+itemNewPriceClassOpened+")" );
            Assert.fail();
        }
        System.out.println("New Price's Class on the main page ("+ itemNewPriceClass +") is the same as on the opened window ("+itemNewPriceClassOpened+")" );
    }
}
