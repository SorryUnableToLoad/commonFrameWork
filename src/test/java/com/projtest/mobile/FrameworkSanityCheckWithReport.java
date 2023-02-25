package com.projtest.mobile;

import com.proj.common.annotations.FrameworkAnnotations;
import com.proj.common.base.MobileSetUp;
import com.proj.common.listeners.TestListenerImpl;
import com.proj.utils.UI_Utils.ActionHelper;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestListenerImpl.class)
public class FrameworkSanityCheckWithReport extends MobileSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoMobilePassTest() throws InterruptedException {
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
        System.out.println("test1 passed");
    }

    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoMobileFailTest() throws InterruptedException {
        System.out.println("test 2nd executed but failed");
        SoftAssert sa = new SoftAssert();
        sa.fail("fail");
        sa.assertAll();
    }

    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoMobileSkipTest() {
        throw new SkipException("test3 skipped");
    }
}
