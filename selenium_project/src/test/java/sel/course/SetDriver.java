package sel.course;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//Created by okovalyshyn on 11/17/2016.
public class SetDriver {
    public WebDriver wd;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        wd = new ChromeDriver(caps);
    }

    @After
    public void quit(){
        wd.quit();
    }
}
