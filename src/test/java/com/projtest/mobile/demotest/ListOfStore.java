package com.projtest.mobile.demotest;

import com.proj.driver.DriverManager;
import com.proj.base.MobileSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ListOfStore extends MobileSetUp {
    @Test
    public void listOfStore() throws InterruptedException {
        Thread.sleep(3000);
        DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc=\"Hello!\"]/following-sibling::android.view.View[1]")).click();
        Thread.sleep(3000);
        List<WebElement> listOfStore = DriverManager.getDriver().findElements(By.xpath("//android.view.View[@content-desc=\"Select Shop\"]/following-sibling::android.view.View[2]/android.view.View/android.view.View"));
        Thread.sleep(3000);
        int count = listOfStore.size();
        System.out.println(count);
        Thread.sleep(3000);
        for (int i=0;i<count;i++){
            String an = listOfStore.get(i).getTagName();
            String av= listOfStore.get(i).getAttribute("content-desc");
            System.out.println(an);
            System.out.println("----------------------------------------");
            System.out.println("store name "+i+" -> "+av);
            System.out.println("****************************************");

        }
        Thread.sleep(3000);
    }
}
