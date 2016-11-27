package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Olga Kovalyshyn on 11/27/2016.
 */
public class StickerTest extends SetDriver{

    @Test
    public void sticker(){
        wd.navigate().to("http://localhost/litecart/en/");

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> list = wd.findElements(By.cssSelector("div.content li.product.column.shadow.hover-light"));
        System.out.println("Found " + list.size() + " items on the main page");

        for(int i=0;i<list.size();i++){
            WebElement item = list.get(i);
            System.out.println("Checking "+ i +" item ...");

            if (!(item.findElements(By.cssSelector("div[class='sticker sale']")).size()>0
                ^ item.findElements(By.cssSelector("div[class='sticker new']")).size()>0))

            {
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED. Found an item without a sticker or with more than one sticker. Item number is " + i + " ." +assertError.getMessage());
                Assert.fail();
            }
        }
        System.out.println("Success: Found no items without a sticker or with more than one sticker");
    }
}