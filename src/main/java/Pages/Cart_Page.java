package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Cart_Page {
    private WebDriver d;

    public Cart_Page(WebDriver d) {
        this.d = d;
    }

    TodayDeals_Page objt = new TodayDeals_Page(d);

    private double totalprice2;
    private String price2;
    private String name2;
    private String getProductTitle2;

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }


    public double getTotalprice2() {
        return totalprice2;
    }

    public void setTotalprice2(double totalprice2) {
        this.totalprice2 = totalprice2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getGetProductTitle2() {
        return getProductTitle2;
    }

    public void setGetProductTitle2(String getProductTitle2) {
        this.getProductTitle2 = getProductTitle2;
    }

    private By Price2_field = By.cssSelector("div[class=\"sc-badge-price-to-pay\"]");
    private By ProductTitle2_field = By.cssSelector("span[class=\"a-truncate-cut\"]");

    public boolean Assertion_Qty() {

        if (!objt.getX()) {
            //if product have only one QTY
            return d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("1");
        } else {
            //if product have only more than one QTY
            return d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("2");
        }

    }

    public boolean Assertion_product_name(String productTitle1) {

        setGetProductTitle2(d.findElement(ProductTitle2_field).getText().toLowerCase());
        return productTitle1.contains(getProductTitle2.substring(0, 10));
    }

    public boolean Assertion_TotalPrice(Double Price1,int qut) {


        if (!objt.getX()) {
            System.out.println((Price1 * qut + "   ?  " + SetTotalPrice2()));
            return (Price1 * qut == setTOTTALprice2_G());
        } else {
            System.out.println((Price1  + "   ?  " + SetTotalPrice2()));
            return (Price1 == setTOTTALprice2_G());
        }
    }

    public double SetPrice2() {
        setPrice2(d.findElement(By.xpath("(//div[@class=\"sc-badge-price\"]/div)[1]")).getText().toLowerCase().replace("egp", "").replace(" ", "").replace(",", ""));
        return Double.parseDouble(getPrice2());
    }
    public double SetTotalPrice2() {
        setPrice2(d.findElement(By.id("sc-subtotal-amount-activecart")).getText().toLowerCase().replace("egp", "").replace(" ", "").replace(",", ""));
        return Double.parseDouble(getPrice2());
    }

    ;

    public double setTOTTALprice2_G() {
        setPrice2(d.findElement(By.id("sc-subtotal-amount-activecart")).getText().toLowerCase().replace("egp", "").replace(" ", "").replace(",", ""));
        return Double.parseDouble(getPrice2());
    }

}
