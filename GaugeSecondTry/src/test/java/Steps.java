import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Steps extends BaseTest {

    String IDHolder = "com.ozdilek.ozdilekteyim:id/imageView";

    @Step("Wait for <wait> seconds.")
    public void waitForSecond(int wait) throws InterruptedException{
        Thread.sleep(1000 * wait);
        System.out.println("Waited for " + wait + " seconds.");
        logger.info("Waited for " + wait + " seconds.");
    }

    @Step("Click the element using id : <selectoriID>")
    public void clickByID(String selectorID){
        appiumDriver.findElement(By.id(selectorID)).click();
        System.out.println("Clicked to " + selectorID);
        logger.info("Clicked to the element with given id.");
    }

    @Step("Send <text> using id to <id>")
    public void sendText(String text, String id){
        MobileElement element = (MobileElement) appiumDriver.findElement(By.id(id));
        element.sendKeys(text);
        System.out.println("Sent '" + text + "' to " + id);
        logger.info("Sent " + text + " to given textbox.");
    }

    @Step("Click using private <xPath>")
    public void clickUsingXpath(String path){
        appiumDriver.findElement(By.xpath(path)).click();
        System.out.println("Clicked to " + path);
        logger.info("Clicked to the element with given id.");
    }

    @Step("Control <selectorID>'s existance")
    public void queryElementExistance(String selectorID){
        Assert.assertTrue(appiumDriver.findElement(By.id(selectorID)).isDisplayed());
        System.out.println("Existance check OK");
        logger.info("Checked for element's existance.");
    }

    @Step("Swipe to bottom")
    public void bottomTopswipe() {
        Dimension size = appiumDriver.manage().window().getSize();
        System.out.println(size);
        int starty = (int) (size.height * 0.50);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        System.out.println("Start swipe operation");
        System.out.println("startx : " + startx + " starty : " + starty + " endy " + endy);

        new TouchAction((PerformsTouchActions) appiumDriver)
                .press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(startx, endy - 3000))
                .release().perform();

        logger.info("Scrolled down.");
    }

    @Step("Select a random product between <randomRange> elements")
    public void randomProductAddition(int randomRange){
        Random rand = new Random();
        int randProductIndex = (rand.nextInt(randomRange));

        System.out.println("Trying to Click randomly");

        List<MobileElement> list = appiumDriver.findElements(By.id(IDHolder));
        list.get(randProductIndex).click();

        System.out.println("Clicked index " + randProductIndex);

        logger.info("Clicked random element.");
    }
}

/*
Özdilekteyim Favori Ürün Senaryosu (Without Concepts)
-----------------------------------------------------
* Wait for "5" seconds.
* Control "com.ozdilek.ozdilekteyim:id/tv_startShoppingStore"'s existance
* Click the element using id : "com.ozdilek.ozdilekteyim:id/tv_startShoppingStore"
* Wait for "2" seconds.
* Control "com.ozdilek.ozdilekteyim:id/nav_categories"'s existance
* Click the element using id : "com.ozdilek.ozdilekteyim:id/nav_categories"
* Wait for "2" seconds.
* Control "com.ozdilek.ozdilekteyim:id/edtSearch"'s existance
* Click using private "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]"
* Wait for "2" seconds.
* Click using private "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]/android.widget.TextView"
* Wait for "3" seconds.
* Control "com.ozdilek.ozdilekteyim:id/imageView"'s existance
* Swipe to bottom
* Swipe to bottom
* Wait for "2" seconds.
* Select a random product between "6" elements
* Wait for "2" seconds.
* Control "com.ozdilek.ozdilekteyim:id/imgFav"'s existance
* Wait for "2" seconds.
* Click the element using id : "com.ozdilek.ozdilekteyim:id/imgFav"
* Wait for "2" seconds.
* Control "com.ozdilek.ozdilekteyim:id/btnLogin"'s existance
* Send "kuty" using id to "com.ozdilek.ozdilekteyim:id/etEposta"
* Send "password" using id to "com.ozdilek.ozdilekteyim:id/etPassword"
* Wait for "2" seconds.
* Click the element using id : "com.ozdilek.ozdilekteyim:id/ivBack"
* Wait for "2" seconds.
* Click the element using id : "com.ozdilek.ozdilekteyim:id/ivBack"
* Wait for "2" seconds.
* Select a random product between "4" elements
* Wait for "2" seconds.
* Click the element using id : "com.ozdilek.ozdilekteyim:id/relLayAddCartBtn"
* Wait for "5" seconds.
*/