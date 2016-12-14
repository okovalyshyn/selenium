package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

//Created by okovalyshyn on 12/13/2016.
public class BrowserLogsTest extends SetDriver {
    @Test
    public void browserLogsTest(){

        LoginTest test = new LoginTest();
        test.login();

        wd. navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        String text = "Duck";
        List<WebElement> itemsList = wd.findElements(By.xpath(".//*[@id='content']/form/table//a[contains(text(),'" + text+"')]"));
        for (int i = 1; i < itemsList.size(); i++) {
            itemsList = wd.findElements(By.xpath(".//*[@id='content']/form/table//a[contains(text(),'" + text + "')]"));

            WebElement item = itemsList.get(i);
            item.click();
            System.out.println("Checking the next item: " + wd.getTitle());

            List<LogEntry> log = wd.manage().logs().get("browser").getAll();
            if (log.size() > 0) {
                AssertionError assertError = new AssertionError("Failed: Found message in Browser Console \n");
                System.out.println(assertError.getMessage() + "" + log);
                Assert.fail();
            }

            wd.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
}
