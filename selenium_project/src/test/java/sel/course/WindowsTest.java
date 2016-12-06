package sel.course;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//Created by okovalyshyn on 12/6/2016.
public class WindowsTest extends SetDriver {
    @Test
    public void windows(){

        LoginTest test = new LoginTest();
        test.login();

        wd.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wd.findElement(By.xpath("//table[contains(@class, 'dataTable')]//tr[2]/td[7]/a")).click();

        String windowBefore = wd.getWindowHandle();
        System.out.println(windowBefore);

        WebElement link = wd.findElement(By.xpath(".//*[@id='content']/form/table[1]/tbody/tr[2]/td/a"));
        String linkUrl = link.getAttribute("href");
        System.out.println(linkUrl);
        //((JavascriptExecutor) wd).executeScript("window.open(\"http://www.google.com/\");");
        ((JavascriptExecutor) wd).executeScript("window.open(\"" + linkUrl + "\");");

        for(String winHandle : wd.getWindowHandles()){
            wd.switchTo().window(winHandle);
            String windowNew = wd.getWindowHandle();
            System.out.println(windowNew);
        }

        wd.close();

// Switch back to original browser (first window)
        wd.switchTo().window(windowBefore);



    }
}
