package sel.course;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

//Created by okovalyshyn on 11/17/2016.
public class LoginTest extends SetDriver
{
    @Test
    public void login(){
        wd.navigate().to("http://localhost/litecart/admin/login.php");
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).sendKeys("admin");

        wd.findElement(By.name("login")).click();

        if (!wd.getCurrentUrl().equals("http://localhost/litecart/admin/")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page title is " + wd.getCurrentUrl());
            Assert.fail();
        }
    }
}