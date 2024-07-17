package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static Handler.synchronization_methods.*;

import java.time.Duration;
import java.util.ArrayList;

public class LoginPage_Page {
    private WebDriver d;
    private By continue_filed = By.id("continue");
    private By NewToAmazonFiled = By.xpath("//*[contains(text() ,\"new to Amazon\")]");
    private By WaseAbroblemFiled = By.xpath("//*[contains(text() ,\"There was a problem\")]");
    private By CreateAccount = By.xpath("//*[contains(.,\"Create Account\")]//h1");

    public LoginPage_Page(WebDriver d) {
        this.d = d;
    }

    private By firestnameField = By.id("nf-field-17");
    private By ap_email = By.id("ap_email");

    private By ap_email2 = By.id("ap_email_login");

    public void send_UnRegMail(String UnRegMail) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) d;
            js.executeScript("arguments[0].value = arguments[1];", d.findElement(ap_email), UnRegMail);

        } catch (Exception E) {
            WaitForvisibilityOfElementLocated(d, ap_email2);
            d.findElement(ap_email2).sendKeys(UnRegMail);
        }
    }

    public void ClickContinue() {
        WaitForvisibilityOfElementLocated(d, continue_filed);
        d.findElement(continue_filed).click();

    }


    public boolean Assertion_login() {

        try {
            WaitForvisibilityOfElementLocated(d, NewToAmazonFiled);
            if (d.findElement(NewToAmazonFiled).getText().toLowerCase().contains("new to amazon")) {
                return true;
            }
        } catch (Exception e) {
            try {
                WaitForvisibilityOfElementLocated(d, WaseAbroblemFiled);
                if (d.findElement(WaseAbroblemFiled).getText().toLowerCase().contains("problem")) {
                    return true;
                }
            } catch (Exception e3) {
            }
            WaitForvisibilityOfElementLocated(d, CreateAccount);
            if (d.findElement(CreateAccount).getText().toLowerCase().contains("create account")) {
                return true;
            }
        }
        return false;
    }

}
