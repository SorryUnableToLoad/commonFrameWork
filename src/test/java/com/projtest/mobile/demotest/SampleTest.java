package com.projtest.mobile.demotest;

import com.projtest.base.WebSetUp;
import org.testng.annotations.Test;

public class SampleTest extends WebSetUp {
    @Test
    public void test(){
        /*for execute mobile script appium server up
        and running and emulator should runnuing*/
        System.out.println("Mobile LOCAL");
        System.out.println("it won't execute without appium server up and running and emulator should runnuing");
    }
}
