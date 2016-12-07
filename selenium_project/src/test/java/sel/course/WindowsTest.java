package sel.course;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
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
        System.out.println("Main windows is '"+wd.getTitle()+ "'; "+windowBefore);

        List<WebElement> linksList = wd.findElements(By.cssSelector("td#content table a[target='_blank']"));
        for (int i = 0; i < linksList.size(); i++) {
            WebElement link = linksList.get(i);
            String linkUrl = link.getAttribute("href");

            ((JavascriptExecutor) wd).executeScript("window.open(\"" + linkUrl + "\");");
            waitForNumberOfWindowsToEqual(2);

            for(String winHandle : wd.getWindowHandles()){
                wd.switchTo().window(winHandle);
            }
            String windowNew = wd.getWindowHandle();
            System.out.println("\t New Window is '"+wd.getTitle() + "'. Page URL is '" + wd.getCurrentUrl()+"'. "+ windowNew);

            wd.close();
            wd.switchTo().window(windowBefore);
            System.out.println("Page title is "+wd.getTitle());
        }
    }

    public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
        new WebDriverWait(wd, 35) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver wd) {
                return (wd.getWindowHandles().size() == numberOfWindows);
            }
        });
    }
}
