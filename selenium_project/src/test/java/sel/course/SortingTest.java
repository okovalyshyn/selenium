package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

//Created by okovalyshyn on 11/29/2016.
public class SortingTest extends SetDriver{

    @Test
    public void sort() {
        LoginTest test = new LoginTest();
        test.login();

        wd.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Page URL " + wd.getCurrentUrl());

        List<WebElement> rows = wd.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int j = 0; j < rows.size(); j++) {

            List<WebElement> countries = wd.findElements(By.cssSelector("table.dataTable td a:not([title='Edit'])"));
            //List <WebElement> countries = wd.findElements(By.xpath(".//table[@class='dataTable']/tbody/tr["+j+"]/td[6]"));
            System.out.println(countries.get(j).getText());

            for (int i = 1; i < countries.size(); i++) {
                if (!(countries.get(i).getText().compareToIgnoreCase(countries.get(i - 1).getText()) > 0)) {
                    AssertionError assertError = new AssertionError();
                    System.out.println("FAILED: " + assertError.getMessage());
                    System.out.println("Countries are NOT displayed in alphabetical order");
                    Assert.fail();
                }
            }


            //for (WebElement tr : rows) {
            // List<WebElement> columns = tr.findElements(By.tagName("td"));
            // System.out.println("text " + columns.get(4).getText());

            // for (WebElement cell : columns) {
            //   columns.get(3).getText();

            //System.out.println("text " + cell.getText());
            // System.out.println("text 4 " + columns.get(4).getText());
            //}
            //}
        }

    }
}
