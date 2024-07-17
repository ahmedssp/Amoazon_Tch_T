package Pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import static Handler.synchronization_methods.*;

import java.util.ArrayList;

public class Home_Page {
    private WebDriver d;
    private By switch_lang_en_filed = By.xpath("//div [@id=\"nav-flyout-icp\"] //a[@href=\"#switch-lang=en_AE\"]");
    private By hover_lang_filed = By.id("icp-nav-flyout");

    public Home_Page(WebDriver d) {
        this.d = d;
    }

    protected ExtentReports extent;
    protected ExtentSparkReporter spark;
    ExtentTest logger;


    private By Hello_filed = By.xpath("//span[text()=\"Account & Lists\"]//span[@class=\"nav-icon nav-arrow\"]");
    private By SignIn_button_filed = By.xpath("//span[@class=\"nav-action-inner\"]");
    private By AllTap_filed = By.xpath("//a[@id=\"nav-hamburger-menu\" ]//span[text()=\"All\"]");
    private By Todays_Deals_filed = By.xpath("//li//*[text()=\"Today's Deals\"]");

    public void switch_language_to_english() {

        //1-make hover of language tap
        try {
            Actions ac = new Actions(d);
            WaitForvisibilityOfElementLocated(d, hover_lang_filed);
            ac.moveToElement(d.findElement(hover_lang_filed)).build().perform();
        } catch (Exception e) {
            Actions ac = new Actions(d);
            d.navigate().refresh();
            WaitForvisibilityOfElementLocated(d, hover_lang_filed);
            ac.moveToElement(d.findElement(hover_lang_filed)).build().perform();
        }
        //2-click on en
        WaitForvisibilityOfElementLocated(d, switch_lang_en_filed);
        d.findElement(switch_lang_en_filed).click();

    }

    public void Hover_Hello() {

        Actions ac = new Actions(d);
        WaitForvisibilityOfElementLocated(d, Hello_filed);
        ac.moveToElement(d.findElement(Hello_filed)).build().perform();
    }

    public LoginPage_Page Click_sign_in() {
        WaitForvisibilityOfElementLocated(d, SignIn_button_filed);
        d.findElement(SignIn_button_filed).click();
        return new LoginPage_Page(d);
    }

    public void Click_AllTap() throws InterruptedException {
        WaitForvisibilityOfElementLocated(d, AllTap_filed);
        d.findElement(AllTap_filed).click();
    }

    public void Click_Todays_Deals() throws InterruptedException {
        Thread.sleep(2000);
        WaitForvisibilityOfElementLocated(d, Todays_Deals_filed);
        d.findElement(Todays_Deals_filed).click();

    }
    public boolean AssertYourOrders() throws InterruptedException {
        switch_language_to_english();
        d.findElement(By.id("nav_prefetch_yourorders")).click();
        System.out.println("we click on order");
//        WaitForvisibilityOfElementLocated(d, By.cssSelector("h1[class=\"a-spacing-small\"]"));
//        System.out.println(d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText()+">>>><<<<");
        return d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("sign in");

    }
    public boolean Assert_seeLists_intro_screen() {
        WaitForvisibilityOfElementLocated(d, By.cssSelector("span[class=\"al-intro-banner-header\"]"));
        return d.findElement(By.cssSelector("span[class=\"al-intro-banner-header\"]")).getText().toLowerCase().contains("lists");
    }
    public boolean Assertion_WE_in_SignIN_page()  {
        WaitForvisibilityOfElementLocated(d, By.xpath("//*[contains(.,\"Sign in\")]//h1"));
        return d.findElement(By.xpath("//*[contains(.,\"Sign in\")]//h1")).getText().toLowerCase().contains("sign in");

    }
    public ArrayList<By> li() {
        ArrayList<By> LI = new ArrayList<By>();
        LI.add(By.id("nav_prefetch_yourorders"));
        LI.add(By.id("nav_prefetch_youraddresses"));
        LI.add(By.xpath("//span[text()=\"Your Lists\"]"));
        return LI;
    }

}
