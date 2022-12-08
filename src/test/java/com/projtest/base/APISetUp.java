package com.projtest.base;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.utils.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.proj.listeners.TestListenerImpl.class)
public class APISetUp {
    public static final Logger logger = LoggerFactory.getLogger(APISetUp.class);

    @BeforeSuite(enabled = false)
    public void connectDB(){
        DatabaseUtils.connectToDB();
        logger.info("Connected to DB");
    }
    @AfterSuite(enabled = false)
    public void disconnectDB(){
        DatabaseUtils.disConnectToDB();
        logger.info("Disconnected from DB");
    }
}
