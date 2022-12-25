package com.projtest.mobile.demotest;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.base.MobileSetUp;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.proj.utils.ActionHelper.*;
@Listeners(com.proj.listeners.TestListenerImpl.class)
public class FrameworkSanityCheckWithReport extends MobileSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void test() throws InterruptedException {

        System.out.println("test started");

        Thread.sleep(3000);
        performScrollLeftToRight();
        System.out.println("swiped From Left To Right");
        Thread.sleep(3000);
        performScrollRightToLeft();
        System.out.println("swiped From Right To Left");
        Thread.sleep(3000);
        performScrollBottomToTop();
        System.out.println("swiped From Bottom To Top");
        Thread.sleep(3000);
        performScrollTopToBottom();
        System.out.println("swiped From Top To Bottom");

    }
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void test1() {
        System.out.println("test1 passed");
    }
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void test2() throws InterruptedException {
        System.out.println("test 2nd executed but failed");
        SoftAssert sa = new SoftAssert();
        sa.fail("fail");
        sa.assertAll();
    }
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void test3() {
        throw new SkipException("test3 skipped");
    }
}
