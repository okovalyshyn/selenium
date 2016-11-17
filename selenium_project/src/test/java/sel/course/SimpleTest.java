package sel.course;

import org.junit.Test;
import org.openqa.selenium.By;

//Created by okovalyshyn on 11/17/2016.
public class SimpleTest extends SetDriver{
    @Test
    public void navigate() {
        wd.navigate().to("http://www.seleniumhq.org/");
        wd.findElement(By.id("q")).sendKeys("selenium");
        wd.findElement(By.id("submit")).click();

        System.out.println("Page title is " + wd.getTitle());
    }
}
