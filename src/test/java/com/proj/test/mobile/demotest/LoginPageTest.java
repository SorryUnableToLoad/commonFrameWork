package com.proj.test.mobile.demotest;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.test.base.MobileSetUp;
import org.testng.annotations.Test;

public class LoginPageTest extends MobileSetUp {
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void homePageValidation() throws InterruptedException {
       /* Thread.sleep(5000);
        DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Login/Signup']"));
        Thread.sleep(5000);
        String text = DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Login/Signup']")).getAttribute("content-desc");
        Thread.sleep(5000);
        System.out.println(text);
        Thread.sleep(5000);
        String text1 = DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Please enter your mobile number']")).getAttribute("content-desc");
        Thread.sleep(5000);
        System.out.println(text1);*/

        System.out.println("done");

    }
}
