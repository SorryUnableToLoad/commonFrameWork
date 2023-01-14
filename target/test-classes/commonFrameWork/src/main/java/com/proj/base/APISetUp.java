package com.proj.base;

import com.proj.utils.apiutils.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

/**
 * This class contains basic configuration methods to execute every TestScripts
 *
 * @author surajkumarnaganuri
 */
@Listeners(com.proj.listeners.TestListenerImpl.class)
public class APISetUp {
    public static final Logger logger = LoggerFactory.getLogger(APISetUp.class);

    /**
     * This method consist of before test method configuration functionalities
     */
    @BeforeSuite(enabled = false)
    public void connectDB() {
        DatabaseUtils.connectToDB();
        logger.info("Successfully Connected to DB");
    }

    /**
     * This method consist of after test method configuration functionalities
     */
    @AfterSuite(enabled = false)
    public void disconnectDB() {
        DatabaseUtils.disConnectToDB();
        logger.info("Successfully Disconnected from DB");
    }
}
