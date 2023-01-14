package com.projtest.web;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.base.WebSetUp;
import com.proj.driver.DriverManager;
import com.proj.listeners.TestListenerImpl;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListenerImpl.class)
public class SampleWeb extends WebSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void sampleWebTest() {
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
