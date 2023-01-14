package com.projtest.mobile.demotest;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.base.MobileSetUp;
import com.proj.listeners.TestListenerImpl;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestListenerImpl.class)
public class FrameworkSanityCheckWithReport extends MobileSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoMobilePassTest() {
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
