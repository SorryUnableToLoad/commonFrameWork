package com.projtest.web;

import com.proj.common.annotations.FrameworkAnnotations;
import com.proj.common.base.WebSetUp;
import com.proj.common.listeners.TestListenerImpl;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//integrating with extent report

@Listeners(TestListenerImpl.class)
public class FrameworkSanityCheckWithReport extends WebSetUp {

    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoWebTest() throws InterruptedException {
        System.out.println("test started");
    }


    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoWebPassTest() {
        System.out.println("test1 passed");
    }


    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoWebFailTest() throws InterruptedException {
        System.out.println("test 2nd executed but failed");
        SoftAssert sa = new SoftAssert();
        sa.fail("fail");
        sa.assertAll();
    }

    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoWebSkipTest() {
        throw new SkipException("test3 skipped");
    }
}
