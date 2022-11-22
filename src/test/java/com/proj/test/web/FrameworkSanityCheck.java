package com.proj.test.web;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.annotations.WebTest;
import com.proj.test.base.WebSetUp;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//integrating with extent report

@Listeners(com.proj.listeners.TestListenerImpl.class)
//@Listeners(com.proj.utils.etc.ListenerImplements.class)


public class FrameworkSanityCheck extends WebSetUp {
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void test() throws InterruptedException {
        System.out.println("test started");
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
