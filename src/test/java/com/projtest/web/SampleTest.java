package com.projtest.web;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.driver.DriverManager;
import com.proj.base.WebSetUp;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.proj.listeners.TestListenerImpl.class)
public class SampleTest extends WebSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void test(){
        System.out.println("WEB LOCAL");
        String title = DriverManager.getDriver().getTitle();
        System.out.println(title);
        logger.info("Test completed");
        logger.debug("Test completed");
        logger.trace("Test completed");
        logger.warn("Test completed");
        logger.error("Test completed");

    }
}
