package sel.course.project.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Olga on 12/20/2016.
 */
public class CartPage {
    private WebDriver wd;
    private WebDriverWait wait;

    public CartPage(WebDriver w, WebDriverWait wt){
        wd = w;
        wait = wt;
    }
}
