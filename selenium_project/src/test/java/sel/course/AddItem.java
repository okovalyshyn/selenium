package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

//Created by okovalyshyn on 11/30/2016.

public class AddItem extends SetDriver {

    @Test
    public void addItem(){
        LoginTest test = new LoginTest();
        test.login();

        wd.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        if (!wd.getTitle().equals("Catalog | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page title is " + wd.getCurrentUrl());
            Assert.fail();
        }

        //General tab
        wd.findElement(By.cssSelector("td#content a[href$='doc=edit_product']")).click();
        if (!wd.getTitle().equals("Add New Product | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page title is " + wd.getCurrentUrl());
            Assert.fail();
        }

        WebElement statusRBtn = wd.findElement(By.cssSelector("div#tab-general input[value='1'][type='radio']"));
        statusRBtn.click();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement name = wd.findElement(By.cssSelector("div#tab-general input[name='name[en]'][type='text']"));
        name.sendKeys("Crested duck");

        WebElement code = wd.findElement(By.cssSelector("div#tab-general input[name='code'][type='text']"));
        code.sendKeys("cd0001");

        wd.findElement(By.cssSelector("div#tab-general input[value='1'][type='checkbox']")).click();

        wd.findElement(By.cssSelector("div#tab-general input[value='1-3'][type='checkbox']")).click();

        WebElement quantity = wd.findElement(By.cssSelector("div#tab-general input[name='quantity']"));
        quantity.clear();
        quantity.sendKeys("39");

        Select soldOut = new Select (wd.findElement(By.cssSelector("div#tab-general select[name='sold_out_status_id']")));
        soldOut.selectByValue("2");

        WebElement validFrom = wd.findElement(By.cssSelector("div#tab-general input[name='date_valid_from'][type='date']"));
        validFrom.sendKeys("11302016");

        WebElement validTo = wd.findElement(By.cssSelector("div#tab-general input[name='date_valid_from'][type='date']"));
        validTo.sendKeys("12302016");

        //Information tab
        wd.findElement(By.cssSelector("div.tabs a[href='#tab-information']")).click();

        Select manufacturer = new Select(wd.findElement(By.cssSelector("div#tab-information select[name='manufacturer_id']")));
        manufacturer.selectByValue("1");

        WebElement keywords = wd.findElement(By.cssSelector("div#tab-information input[name='keywords'][type='text']"));
        keywords.sendKeys("Crested duck");

        WebElement shortDescription = wd.findElement(By.cssSelector("div#tab-information input[name='short_description[en]'][type='text']"));
        shortDescription.sendKeys("Crested duck (duck breed)");

        WebElement italic = wd.findElement(By.cssSelector("div#tab-information button[title='Italic (Ctrl + I)']"));
        italic.click();

        WebElement description = wd.findElement(By.cssSelector("div#tab-information div.trumbowyg-editor"));
        description.click();
        description.sendKeys("The Crested is a breed of domestic duck. It was probably brought to Europe from the East Indies by Dutch ships.");
        description.sendKeys(Keys.ENTER);
        italic.click();
        description.sendKeys("It has its appearance because it is heterozygous for a genetic mutation causing a deformity of the skull. A bantam version of the breed, the Crested Miniature, was bred by John Hall and Roy Sutcliffe in the United Kingdom in the late twentieth century; it was recognized in 1997.");

        WebElement headTitle = wd.findElement(By.cssSelector("div#tab-information input[name='head_title[en]'][type='text']"));
        headTitle.sendKeys("Crested duck");

        WebElement metaDescription = wd.findElement(By.cssSelector("div#tab-information input[name='meta_description[en]'][type='text']"));
        metaDescription.sendKeys("Crested duck");

        //Prices tab
        wd.findElement(By.cssSelector("div.tabs a[href='#tab-prices']")).click();

        WebElement price = wd.findElement(By.cssSelector("div#tab-prices input[name='purchase_price'][type='number']"));
        price.clear();
        price.sendKeys("47");

        Select currency = new Select (wd.findElement(By.cssSelector("div#tab-prices select[name='purchase_price_currency_code']")));
        currency.selectByValue("USD");

        WebElement price2 = wd.findElement(By.cssSelector("div#tab-prices input[data-type='currency']"));
        price2.clear();
        price2.sendKeys("47");

        System.out.println("Looking for Save button: ");

        //save
        WebElement save = wd.findElement(By.cssSelector("span.button-set button[type='submit'][value='Save']"));
        //WebElement save = wd.findElement(By.xpath(".//*[@id='content']/form/p/span/button[1]"));

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebElement save = wd.findElement(By.name("save"));
        String at = save.getAttribute("outerHTML");
        System.out.println("at: "+at);

        //Actions actions = new Actions(wd);
        //actions.moveToElement(save);
        //actions.click();
        //actions.build().perform();

        System.out.println("Clicking Save button");


        save.click();


        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("form[name='catalog_form']")).click();

        System.out.println(wd.getCurrentUrl());

        if (!wd.getCurrentUrl().equals("http://localhost/litecart/admin/?app=catalog&doc=catalog")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page URL is " + wd.getCurrentUrl());
            Assert.fail();
        }









        // actions.sendKeys(countryValue);
        //actions.sendKeys(Keys.ENTER);
        //actions.build().perform();








    }
}
