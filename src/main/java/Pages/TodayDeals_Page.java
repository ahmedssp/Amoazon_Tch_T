package Pages;

import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static Handler.synchronization_methods.WaitForvisibilityOfElementLocated;

public class TodayDeals_Page {
    private WebDriver d;
    private By Quanty_filed = By.id("quantity");
    private By AddToCartButton = By.id("add-to-cart-button");
    private By cart_count_container_filed = By.id("nav-cart-count-container");
    private By attachSiNoCoverage_filed = By.id("attachSiNoCoverage");

    public TodayDeals_Page(WebDriver d) {
        this.d = d;
    }

    @Setter
    private String productTitle1 = null;
    private String price1 = "";
    private Boolean x = true;

    public String getProductTitle1() {
        return productTitle1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public Boolean getX() {
        return x;
    }

    public void setX(Boolean x) {
        this.x = x;
    }

    public double getTotalprice1() {
        return totalprice1;
    }

    public void setTotalprice1(double totalprice1) {
        this.totalprice1 = totalprice1;
    }

    private double totalprice1;
    private By second_categoryes_filed = By.xpath("//div[@id=\"anonCarousel3\"]//li");
    private By Firstproduct_filed = By.xpath("(//div[@data-test-index])[2]/div/a");
    private By Second_item_filed = By.xpath("(//ul[@class=\"a-unordered-list a-nostyle a-horizontal a-spacing-none\"]/li)[2]");
    private By productTitle1_Field = By.id("productTitle");
    private By Bouton_announcer_field = By.id("warranty_no_button-announce");

    public void click_second_categoryes(int category_number) {
        WaitForvisibilityOfElementLocated(d, second_categoryes_filed);
        d.findElements(second_categoryes_filed).get(category_number).click();
    }

    public void click_Firstproduct() {
        WaitForvisibilityOfElementLocated(d, Firstproduct_filed);
        try {
// Initialize WebDriverWait with a timeout of 10 seconds
            WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

// Wait until the Firstproduct_filed element is clickable
            WebElement firstProductField = wait.until(ExpectedConditions.elementToBeClickable(Firstproduct_filed));
            d.findElement(Firstproduct_filed).click();
            System.out.println("Element is clickable");
        } catch (TimeoutException e) {
            System.out.println("Element isn't clickable");
        }
    }

    public void Second_item_Click_and_select_size_if_found() {


        try {
            WaitForvisibilityOfElementLocated(d, Second_item_filed);
            d.findElement(Second_item_filed).click();
        } catch (Exception e) {
            System.out.println("this product have one item ");
            try {
                WaitForvisibilityOfElementLocated(d, By.id("native_dropdown_selected_size_name"));
                System.out.println("should enter size");

                WebElement dropdownElement = d.findElement(By.id("native_dropdown_selected_size_name"));
                Select dropdown = new Select(dropdownElement);
//            dropdown.selectByVisibleText("L");
                dropdown.selectByIndex(2);
                WaitForvisibilityOfElementLocated(d, Quanty_filed);
                System.out.println("selectByIndex don");

            } catch (Exception e2) {
                System.out.println("no size needed to select");
            }
        }

    }

    public String productTitle1() {
        WaitForvisibilityOfElementLocated(d, By.id("productTitle"));
        return d.findElement(By.id("productTitle")).getText().toLowerCase();
    }

    public Double Totalprice1() {
        WaitForvisibilityOfElementLocated(d, By.id("productTitle"));
//        return  Double.parseDouble(d.findElement(By.cssSelector("span[class=\"a-price-whole\"]")).getText().toLowerCase().replace("egp", "").replace(" ", "").replace(",", ""));
        return Double.parseDouble(d.findElement(By.xpath("(//span[@class=\"a-price-whole\"])[1]")).getText().toLowerCase().replace("egp", "").replace(" ", "").replace(",", "").replace("-", ""));

    }

    public void add_QTY2(String qyt) {
        try {
            d.findElement(By.id("native_dropdown_selected_size_name")).isDisplayed();
            Select select = new Select(d.findElement(By.id("native_dropdown_selected_size_name")));
            select.deselectByIndex(2);
        } catch (Exception e) {
            System.out.println("no size needed to select");
        }
        try {
            setProductTitle1(d.findElement(productTitle1_Field).getText().toLowerCase());
            WaitForvisibilityOfElementLocated(d, By.cssSelector("span[class=\"a-price-whole\"]"));
            setPrice1(d.findElement(By.cssSelector("span[class=\"a-price-whole\"]")).getText().toLowerCase());
            WaitForvisibilityOfElementLocated(d, Quanty_filed);
            Select select = new Select(d.findElement(Quanty_filed));
            select.selectByValue(qyt);
            setX(true);
            System.out.println("this Product Have more Than One items ");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("this product have one Qty ");
            setX(false);
        }
    }

    public void add_QTY(String qyt) {

        try {
            WaitForvisibilityOfElementLocated(d, Quanty_filed);
            Select select = new Select(d.findElement(Quanty_filed));
            select.selectByValue(qyt);
            setX(true);
            System.out.println("this Product Have more Than One items ");
            Thread.sleep(1000);
        } catch (Exception e) {
            try {
                WaitForvisibilityOfElementLocated(d, Quanty_filed);
            } catch (Exception E3) {
                WaitForvisibilityOfElementLocated(d, By.xpath("//span[@id=\"dealBadgeSupportingText\"]"));
                System.out.println("we have Prime Day Deal");
            }
            System.out.println("this product have one Qty ");
            setX(false);
        }
    }

    public void prress_add_cart_button() {
        try {
            WaitForvisibilityOfElementLocated(d, AddToCartButton);
            JavascriptExecutor js = (JavascriptExecutor) d;
            js.executeScript("arguments[0].click();", d.findElement(AddToCartButton));
        } catch (Exception e) {
            WaitForvisibilityOfElementLocated(d, By.xpath("//span[@id=\"dealBadgeSupportingText\"]"));

        }

        try {
            WaitForvisibilityOfElementLocated(d, attachSiNoCoverage_filed);
            d.findElement(attachSiNoCoverage_filed).click();
        } catch (Exception e) {
            System.out.println("No offers");
        }
    }

    public Cart_Page GotoCarte_page() {
        try {
            WaitForvisibilityOfElementLocated(d, cart_count_container_filed);
            d.findElement(cart_count_container_filed).click();
            return new Cart_Page(d);
        } catch (Exception exx) {
            WaitForvisibilityOfElementLocated(d, By.xpath("//span[@id=\"dealBadgeSupportingText\"]"));
            return new Cart_Page(d);
        }

    }

    public boolean TodayDeals_PgObj() {
        if (d.findElement(By.xpath("//span[@id=\"dealBadgeSupportingText\"]")).getText().contains("Prime Day ")) {
            return true;
        } else return false;
    }


}
