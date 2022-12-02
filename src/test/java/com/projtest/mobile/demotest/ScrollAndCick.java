package com.projtest.mobile.demotest;

import com.proj.driver.DriverManager;
import com.projtest.base.MobileSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.proj.utils.AppiumDriverUtils.scrollToSpecificElementAndClick;

public class ScrollAndCick extends MobileSetUp {

    @Test
    public void scrollAndClick() throws InterruptedException {
        Thread.sleep(3000);
        DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc=\"Hello!\"]/following-sibling::android.view.View[1]")).click();
        Thread.sleep(3000);
        List<WebElement> listOfStore = DriverManager.getDriver().findElements(By.xpath("//android.view.View[@content-desc=\"Select Shop\"]/following-sibling::android.view.View[2]/android.view.View/android.view.View"));
        Thread.sleep(3000);
        int count = listOfStore.size();
        System.out.println(count);

        By store = By.xpath("//android.view.View[@content-desc=\"Test Customer 1\n" +
                "#123/2, brigade rd, ashok nagar bangalore 560025, Bengaluru, Karnataka, India, 560025\"]");

        scrollToSpecificElementAndClick(store);
        Thread.sleep(5000);

    }
}
