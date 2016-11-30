package sel.course;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//Created by okovalyshyn on 11/22/2016.
public class RegistrationTest extends SetDriver {

    //potentially to be moved to the config file
    String nameValue="Olga";
    String lastnValue="Kovalyshyn";
    String addressValue="Bandery ave";
    String postcodeValue="04427";
    String cityValue="Kyiv";
    String countryValue="Ukraine";
    String emailValue="olya.rolya@gmail.com";
    String phoneValue="+380972671234";
    String passwordValue="12345678";

    @Test
    public void test() {

        wd.navigate().to("http://localhost/litecart/en/");

        //open Registration form. Entered values
        WebElement link = wd.findElement(By.cssSelector("div#box-account-login a[href*=create_account]"));
        link.click();

        if (!(wd.getCurrentUrl().equals("http://localhost/litecart/en/create_account"))){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page URL is" + wd.getCurrentUrl());
            Assert.fail();
        }

        WebElement firstName = wd.findElement(By.name("firstname"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys(nameValue);

        WebElement lastName = wd.findElement(By.name("lastname"));
        lastName.click();
        lastName.clear();
        lastName.sendKeys(lastnValue);

        WebElement address1 = wd.findElement(By.name("address1"));
        address1.click();
        address1.clear();
        address1.sendKeys(addressValue);

        WebElement postcode = wd.findElement(By.name("postcode"));
        postcode.click();
        postcode.clear();
        postcode.sendKeys(postcodeValue);

        WebElement city = wd.findElement(By.name("city"));
        city.click();
        city.clear();
        city.sendKeys(cityValue);

        WebElement country = wd.findElement(By.cssSelector("span.selection"));

        Actions actions = new Actions(wd);
        actions.moveToElement(country);
        actions.click();
        actions.sendKeys(countryValue);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();

        Random randNumber = new Random();
        int uniqueNumber = randNumber.nextInt(100) + 1;

        WebElement email = wd.findElement(By.name("email"));
        email.click();
        email.clear();
        email.sendKeys(uniqueNumber+emailValue);
        System.out.println("The next email was used:  " + uniqueNumber+emailValue);

        WebElement phone = wd.findElement(By.name("phone"));
        phone.click();
        phone.clear();
        phone.sendKeys(phoneValue);

        WebElement password = wd.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordValue);

        WebElement cpassword = wd.findElement(By.name("confirmed_password"));
        cpassword.click();
        cpassword.clear();
        cpassword.sendKeys(passwordValue);

        WebElement submit = wd.findElement(By.cssSelector("button[type=submit]"));
        submit.click();

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String accountCreatedText = wd.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();

        if (!accountCreatedText.equals("Your customer account has been created.")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Do not see message: 'Your customer account has been created'");
            Assert.fail();
        }
        System.out.println("Account created. I see message:  " + accountCreatedText);

        //Log out
        logout();

        //Log in
        login(uniqueNumber);

        //log out
        logout();
    }

    private void logout(){
        WebElement logout = wd.findElement(By.cssSelector("div#box-account a[href*=logout]"));
        logout.click();

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String successLogoutText = wd.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();

        if (!successLogoutText.equals("You are now logged out.")){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Do not see message:  'You are now logged out'");
            Assert.fail();
        }
        System.out.println("You logged out. I see message:  " + successLogoutText);
    }

    private void login(int  uniqueNumber){
        WebElement emailLogin = wd.findElement(By.cssSelector("div#box-account-login input[name=email]"));
        emailLogin.click();
        emailLogin.clear();
        emailLogin.sendKeys(uniqueNumber+emailValue);

        WebElement passwordLogin = wd.findElement(By.cssSelector("div#box-account-login input[name=password]"));
        passwordLogin.click();
        passwordLogin.clear();
        passwordLogin.sendKeys(passwordValue);

        WebElement loginBtn = wd.findElement(By.cssSelector("div#box-account-login button[name=login]"));
        loginBtn.click();

        String successLText = wd.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();
        String expectedMessage = "You are now logged in as "+nameValue + " "+lastnValue+".";
        if (!successLText.equals(expectedMessage)){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Do not see message: 'You are now logged in as "+nameValue + " "+lastnValue+"'.");
            Assert.fail();
        }
        System.out.println("You logged in. I see message:  " + successLText);
    }
}
