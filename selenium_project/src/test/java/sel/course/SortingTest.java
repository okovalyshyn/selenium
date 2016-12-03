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
        if (!wd.getTitle().equals("Countries | My Store")){
            AssertionError assertError = new AssertionError("Can not open required page");
            System.out.println("FAILED: Page title is "+wd.getTitle()+"" +assertError.getMessage());
            Assert.fail();
        }
        System.out.println("Page Title is " + wd.getTitle());
        String previousCountryName = null;

        List<WebElement> rows = wd.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int j = 0; j < rows.size(); j++) {
            String countryName = rows.get(j).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a"))).getText();
            if (previousCountryName!=null && !(countryName.compareToIgnoreCase(previousCountryName) > 0)) {
                AssertionError assertError = new AssertionError("Countries are NOT displayed in an alphabetical order");
                System.out.println("FAILED: " + previousCountryName+ " "+ countryName+" "+assertError.getMessage());
                Assert.fail();
            }
            previousCountryName = countryName;
            System.out.println(countryName);

            String countryZone = rows.get(j).findElements(By.cssSelector("td")).get(5).getText();
            if (!countryZone.equals("0")){
                System.out.println("Found country "+countryName+" with "+countryZone+" zones");
                WebElement countryLink = rows.get(j).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a")));
                countryLink.click();

                String previousZone = null;
                List<WebElement> rowsZones = wd.findElements(By.cssSelector("table#table-zones tr"));
                for (int k = 1; k < rowsZones.size()-1; k++) {
                    String zone = rowsZones.get(k).findElements(By.cssSelector("td")).get(2).getText();

                    if (previousZone!=null && !(zone.compareToIgnoreCase(previousZone) > 0)) {
                        AssertionError assertError = new AssertionError("Zones are NOT displayed in an alphabetical order");
                        System.out.println("FAILED:" + previousZone+ " "+ zone+" "+assertError.getMessage());
                        Assert.fail();
                    }
                    previousZone = zone;
                }
                System.out.println(countryName +"'s zones are displayed in an alphabetical order");

                wd.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
                rows = wd.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }

        wd.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        if (!wd.getTitle().equals("Geo Zones | My Store")) {
            AssertionError assertError = new AssertionError("Can not open required page");
            System.out.println("FAILED: Page title is " + wd.getTitle() + "" + assertError.getMessage());
            Assert.fail();
        }
        System.out.println("Page title is " + wd.getTitle());

        List<WebElement> geoZonesList = wd.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int t = 0; t < geoZonesList.size(); t++) {
            WebElement geoZone = geoZonesList.get(t).findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("a")));
            String geoZoneName = geoZone.getText();
            geoZone.click();

            String geoZonesPrevious = null;
            List<WebElement> geoZonesCountryList = wd.findElements(By.cssSelector("table#table-zones tr"));
            for (int y = 1; y < geoZonesCountryList.size() - 1; y++) {
                String geoZones = geoZonesCountryList.get(y).findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("select option[selected='selected']"))).getText();

                if (geoZonesPrevious != null && !(geoZones.compareToIgnoreCase(geoZonesPrevious) > 0)) {
                AssertionError assertError = new AssertionError("Geo Zones are NOT displayed in an alphabetical order");
                System.out.println("FAILED:" + geoZonesPrevious + " " + geoZones + " " + assertError.getMessage());
                Assert.fail();
                }
                geoZonesPrevious = geoZones;
            }
            System.out.println(geoZoneName + "'s geo zones are displayed in an alphabetical order");
            wd.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            geoZonesList = wd.findElements(By.cssSelector("table.dataTable tr.row"));
        }
    }
}
