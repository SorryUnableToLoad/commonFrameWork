package com.projtest.mobile.demotest;

import com.proj.base.MobileSetUp;
import com.proj.utils.ActionHelper;
import org.testng.annotations.Test;

public class SampleMobile extends MobileSetUp {
    @Test
    public void sampleMobileTest() {
        /*for execute mobile script appium server up
        and running and emulator should runnuing*/
        System.out.println("Mobile LOCAL");
        System.out.println("it won't execute without appium server up and running and emulator should runnuing");
    }

    @Test
    public void scrollTest() throws InterruptedException {

        System.out.println("scrollTest started");
        Thread.sleep(3000);
       /* ActionHelper.scrollForMobile();
        System.out.println("swiped");
        Thread.sleep(3000);*/

        Thread.sleep(3000);
        ActionHelper.performScrollBottomToTop();
        System.out.println("swiped From Bottom To Top");
        Thread.sleep(3000);

        ActionHelper.performScrollTopToBottom();
        System.out.println("swiped From Top To Bottom");
        Thread.sleep(3000);

        ActionHelper.performScrollLeftToRight();
        System.out.println("swiped From Left To Right");
        Thread.sleep(3000);

        ActionHelper.performScrollRightToLeft();
        System.out.println("swiped From Right To Left");
        Thread.sleep(3000);
    }
}
