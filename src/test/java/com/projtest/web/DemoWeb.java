package com.projtest.web;

import com.proj.common.annotations.FrameworkAnnotations;
import com.proj.common.base.WebSetUp;
import com.proj.common.driver.DriverManager;
import org.testng.annotations.Test;

public class DemoWeb extends WebSetUp {
    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void demoWebTest() {
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
