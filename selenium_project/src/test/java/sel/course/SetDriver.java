package sel.course;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

//Created by okovalyshyn on 11/17/2016.
public class SetDriver {
    public static WebDriver wd;
    public WebDriverWait wait;

    @Before
    public void start(){
        if (wd==null){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            wd = new ChromeDriver(caps);
            wait = new WebDriverWait(wd, 30);
        }

        //firefox
        //if (wd==null){
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, true);
        //wd = new FirefoxDriver(caps);
        //wd.manage().window().maximize();
        //wait = new WebDriverWait(wd, 30);
        //}

        //ie
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        //caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        //caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        //wd = new InternetExplorerDriver(caps);
        //wd.manage().window().maximize();

        //System.out.println(((HasCapabilities) wd).getCapabilities());

        //wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wait = new WebDriverWait(wd, 10);
    }

    //@After
    //public void quit(){
   //     wd.quit();
   // }
}
