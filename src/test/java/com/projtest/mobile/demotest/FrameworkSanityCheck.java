package com.projtest.mobile.demotest;

import com.proj.annotations.FrameworkAnnotations;
import com.projtest.base.MobileSetUp;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.proj.utils.AppiumDriverUtils.*;
@Listeners(com.proj.listeners.TestListenerImpl.class)
//@Listeners(com.proj.utils.etc.ListenerImplements.class)
public class FrameworkSanityCheck extends MobileSetUp {
    @FrameworkAnnotations(author = "Suraj")
    @Test
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
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void test1() {
        System.out.println("test1 passed");
    }
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void test2() throws InterruptedException {
        System.out.println("test 2nd executed but failed");
        SoftAssert sa = new SoftAssert();
        sa.fail("fail");
        sa.assertAll();
    }
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void test3() {
        throw new SkipException("test3 skipped");
    }
}
