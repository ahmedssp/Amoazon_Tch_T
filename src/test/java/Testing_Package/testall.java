package Testing_Package;

import Base_Package.TestBase;
import Pages.Cart_Page;
import Pages.LoginPage_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Handler.synchronization_methods.WaitForvisibilityOfElementLocated;
import static Utility.testing_methods.getCurrentMethodName;

@Listeners(TestListener.class)
public class testall extends TestBase {
    /*
     * Scenario 1
     * (Verify that user cannot log in with valid but not registered email)
     * */
    @Test(priority = 0)
    public void login_valid_not_registered_email() throws InterruptedException {
        logger = extent.createTest(getCurrentMethodName());
        //0-change language
        Home_PgObj.switch_language_to_english();
        logger.info("change page language");
        SoftAssert Assert = new SoftAssert();
        //1-make hover on hello tab
        Home_PgObj.Hover_Hello();
        logger.info("Hover_Hello");
        //2- press on sign in tab
        LoginPage_Page loginPagePage_obj = Home_PgObj.Click_sign_in();
        logger.info("Click_sign_in");
        //3-inter validEmail_not register
        loginPagePage_obj.send_UnRegMail("ahmedabdelsalame20@gmail.com");
        logger.info("send_UnRegMail(\"ahmedabdelsalame20@gmail.com\")");
        //4-press continue to log in
        loginPagePage_obj.ClickContinue();
        logger.info("ClickContinue");
        //5- assertion for can`t logIn
        Assert.assertTrue(loginPagePage_obj.Assertion_login());
        logger.info("Assertion login");
        Assert.assertAll();
    }
    /*
     * Scenario 2
     * (Verify that Items are added to cart correctly)
     * */

    @Test(priority = 1) // Scenario 2
    public void added_Items() throws InterruptedException {
        logger = extent.createTest(getCurrentMethodName());
        //0-switch page language to english
        Home_PgObj.switch_language_to_english();
        logger.info("change page language");
        //1-click on all tap
        Home_PgObj.Click_AllTap();
        logger.info("click on all tap");
        //2-from list select Today's Deals
        Home_PgObj.Click_Todays_Deals();
        logger.info("from list select Today's Deals");
        //3- Select category number
        TodayDeals_PgObj.click_second_categoryes(2);
        logger.info("click second category");
        //4-click on First product
        TodayDeals_PgObj.click_Firstproduct();
        logger.info("from list select Today's Deals");
        //5-Click on 2nd item in this product
        TodayDeals_PgObj.Second_item_Click_and_select_size_if_found();
        logger.info("Click on 2nd item in this product");
        //product info
        String productTitle1 = TodayDeals_PgObj.productTitle1();
        Double Totalprice1 = TodayDeals_PgObj.Totalprice1();
        boolean cond = TodayDeals_PgObj.TodayDeals_PgObj();
        System.out.println(productTitle1 + "? " + Totalprice1);
        //6-add qty=2
        TodayDeals_PgObj.add_QTY("2");
        logger.info("add qty=2");
//        //7- prress add add-to-cart-button
        TodayDeals_PgObj.prress_add_cart_button();
        logger.info("press add cart button");
//        //9-go to Chart page
        Cart_Page cartPage_Obj = TodayDeals_PgObj.GotoCarte_page();

        logger.info("go to Chart page");
        //10- assert for
        SoftAssert Assert = new SoftAssert();
        if (cond) {
            logger.info("we can not add to carte as its Prime Day Deal!");
        } else {
            //1>assert qty
            Assert.assertTrue(cartPage_Obj.Assertion_Qty());
            logger.info("Assertion_Qty");
            //2> name assertion
            Assert.assertTrue(cartPage_Obj.Assertion_product_name(productTitle1));
            logger.info("Assertion_product_name");
            //3>price assertion
            Assert.assertEquals(cartPage_Obj.SetPrice2(), Totalprice1);
            logger.info("price assertion");
            // 4>assert total price for qyt=2
            Assert.assertTrue(cartPage_Obj.Assertion_TotalPrice(Totalprice1, 2));
            logger.info("assert total price for qyt=2");
        }
        Assert.assertAll();
    }

    /*
     * Scenario 3
     * (Verify that you cannot see “Your Orders” and “Your Addresses”
       pages if you are not logged in. But you can see “Your Lists” intro screen)
       */
    @Test(priority = 2)
    public void if_you_are_not_logged_in_cannot_see_user_details() throws InterruptedException {
        logger = extent.createTest(getCurrentMethodName());
        //0-switch page language to english
        Home_PgObj.switch_language_to_english();
        logger.info("switch_language_to_english");
        //instate soft assertion
        SoftAssert ass = new SoftAssert();
        //loop for 3 assertion for each element we check condition
        for (int i = 0; i < 3; i++) {
            Home_PgObj.Hover_Hello();
            logger.info(i + ": Hover_Hello");
            WaitForvisibilityOfElementLocated(d, Home_PgObj.li().get(i));
            String element = d.findElement(Home_PgObj.li().get(i)).getText();
            d.findElement(Home_PgObj.li().get(i)).click();
            logger.info(i + ": " + element + " clicked");
            if (i == 2) {
                ass.assertTrue(Home_PgObj.Assert_seeLists_intro_screen());

                d.navigate().back();
            } else {
                Assert.assertTrue(Home_PgObj.Assertion_WE_in_SignIN_page());
                d.navigate().back();
            }
            logger.info(i + ": " + "Assertion don on:" + element);
            ass.assertAll();
        }
    }


}
