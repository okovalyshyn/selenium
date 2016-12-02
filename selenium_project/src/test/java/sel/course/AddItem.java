package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

//Created by okovalyshyn on 11/30/2016.

public class AddItem extends SetDriver {
    private  String itemName = "Crested duck";

    @Test
    public void addItem(){
        LoginTest test = new LoginTest();
        test.login();

        //catalog
        wd.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        if (!wd.getTitle().equals("Catalog | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page title is " + wd.getTitle());
            Assert.fail();
        }
        System.out.println("Page title is: " + wd.getTitle());

        wd.findElement(By.linkText("Rubber Ducks")).click();
        WebElement itemsFirst = wd.findElement(By.cssSelector("table.dataTable tr.footer td"));
        String numFirst = itemsFirst.getAttribute("textContent");
        System.out.println("There are: " + numFirst);


        //General tab
        wd.findElement(By.cssSelector("td#content a[href$='doc=edit_product']")).click();
        if (!wd.getTitle().equals("Add New Product | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page title is: " + wd.getTitle());
            Assert.fail();
        }
        System.out.println("Page title is: " + wd.getTitle());

        WebElement statusRBtn = wd.findElement(By.cssSelector("div#tab-general input[value='1'][type='radio']"));
        statusRBtn.click();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement name = wd.findElement(By.cssSelector("div#tab-general input[name='name[en]'][type='text']"));
        name.sendKeys(itemName);

        WebElement code = wd.findElement(By.cssSelector("div#tab-general input[name='code'][type='text']"));
        code.sendKeys("cd0001");

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement main = wd.findElement(By.cssSelector("div#tab-general input[value='0'][type='checkbox']"));
        String mainStatus = main.getAttribute("checked");
        if (mainStatus=="true"){
            main.click();
        }

        WebElement rubberDuck = wd.findElement(By.cssSelector("div#tab-general input[value='1'][type='checkbox']"));
        String rubberDuckStatus = rubberDuck.getAttribute("checked");
        if (rubberDuckStatus=="null"){
            rubberDuck.click();
        }

        WebElement subcategory = wd.findElement(By.cssSelector("div#tab-general input[value='2'][type='checkbox']"));
        String subcategoryStatus = subcategory.getAttribute("checked");
        if (subcategoryStatus=="true"){
            subcategory.click();
        }

        wd.findElement(By.cssSelector("div#tab-general input[value='1-3'][type='checkbox']")).click();

        WebElement quantity = wd.findElement(By.cssSelector("div#tab-general input[name='quantity']"));
        quantity.clear();
        quantity.sendKeys("39");

        Select soldOut = new Select (wd.findElement(By.cssSelector("div#tab-general select[name='sold_out_status_id']")));
        soldOut.selectByValue("2");

       // WebElement uploadImage = wd.findElement(By.cssSelector("div#tab-general input[name='new_images[]']"));
       // uploadImage.click();

       // wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // uploadImage.sendKeys("C:\\xampp\\htdocs\\litecart\\images\\products\\test1-1.jpg");
       // wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // uploadImage.sendKeys(Keys.ENTER);


        WebElement validFrom = wd.findElement(By.cssSelector("div#tab-general input[name='date_valid_from'][type='date']"));
        validFrom.sendKeys("11302016");

        WebElement validTo = wd.findElement(By.cssSelector("div#tab-general input[name='date_valid_to'][type='date']"));
        validTo.sendKeys("12302016");

        //Information tab
        wd.findElement(By.cssSelector("div.tabs a[href='#tab-information']")).click();

        Select manufacturer = new Select(wd.findElement(By.cssSelector("div#tab-information select[name='manufacturer_id']")));
        manufacturer.selectByValue("1");

        WebElement keywords = wd.findElement(By.cssSelector("div#tab-information input[name='keywords'][type='text']"));
        keywords.sendKeys(itemName);

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
        headTitle.sendKeys(itemName);

        WebElement metaDescription = wd.findElement(By.cssSelector("div#tab-information input[name='meta_description[en]'][type='text']"));
        metaDescription.sendKeys(itemName);

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

        System.out.println("Pressed Save button");

        //save
        wd.findElement(By.cssSelector("span.button-set button[type='submit'][value='Save']")).click();
    //    wait.until(titleIs("Catalog | My Store"));
        if (!wd.getTitle().equals("Catalog | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page URL is " + wd.getCurrentUrl());
            Assert.fail();
        }
        System.out.println("Current Page Title is: "+wd.getTitle());

        wd.findElement(By.linkText("Rubber Ducks"));
        WebElement itemsSecond = wd.findElement(By.cssSelector("table.dataTable tr.footer td"));
        String numSecond = itemsSecond.getAttribute("textContent");
        System.out.println("There are: " + numSecond);

        isElementPresent(wd);
    }

    protected boolean isElementPresent(WebDriver wd){
        try{
            wd.findElement(By.linkText(itemName));
            System.out.println("Found created item on the Catalog page");
            return true;
        }
        catch(NoSuchElementException e){
            System.out.println("Can not found created item on the Catalog page");
            return false;
        }
    }
}
