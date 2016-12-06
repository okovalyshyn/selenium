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

        String mainWindow = wd.getWindowHandle();
        System.out.println(mainWindow);
        //Set<String> oldWindows = wd.getWindowHandles();
        //System.out.println("oldWindows");
        WebElement openLink = wd.findElement(By.xpath(".//*[@id='content']/form/table[1]/tbody/tr[2]/td/a/i"));
        ((JavascriptExecutor) wd).executeScript("window.open(\"http://www.google.com/\");");




    }
}
