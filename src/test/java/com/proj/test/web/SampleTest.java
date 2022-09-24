package com.proj.test.web;

import com.proj.base.WebBaseClass;
import com.proj.driver.DriverManager;
import org.testng.annotations.Test;

public class SampleTest extends WebBaseClass {
    @Test
    public void test(){
        System.out.println("WEB LOCAL");
        String title = DriverManager.getCurrentDriver().getTitle();
        System.out.println(title);
    }
}
