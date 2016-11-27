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
public class AdminMenuTest extends SetDriver {

    @Test
    public void adminMenu() {
        LoginTest test = new LoginTest();
        test.login();

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> menuList = wd.findElements(By.cssSelector("ul#box-apps-menu li#app-"));

        for (int i = 0; i < menuList.size(); i++) {
            menuList = wd.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
            WebElement menuItem = menuList.get(i);

            menuItem.click();

            if (wd.getTitle()==""){
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED. Found page without title. Page URL is " + wd.getCurrentUrl()+ " ." +assertError.getMessage());
                Assert.fail();
            }
            System.out.println("Page title is " + wd.getTitle());

            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            if ((wd.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
                List<WebElement> subMenuList = wd.findElements(By.cssSelector("ul#box-apps-menu li li"));

                for (int j = 1; j < subMenuList.size(); j++) {
                    subMenuList = wd.findElements(By.cssSelector("ul#box-apps-menu li li"));
                    WebElement subMenuItem = subMenuList.get(j);

                    subMenuItem.click();

                    if (wd.getTitle()==""){
                        AssertionError assertError = new AssertionError();
                        System.out.println("FAILED. Found page without title. Page URL is " + wd.getCurrentUrl()+ " ." +assertError.getMessage());
                        Assert.fail();
                    }
                    System.out.println("Page title is " + wd.getTitle());

                    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }
        }
    }
}
