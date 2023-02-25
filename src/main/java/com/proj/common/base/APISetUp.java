package com.proj.common.base;

import com.proj.common.listeners.TestListenerImpl;
import com.proj.utils.apiutils.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

/**
 * This class contains basic configuration methods to execute every TestScripts
 *
 * @author surajkumarnaganuri
 */
//@Listeners(com.proj.common.listeners.TestListenerImpl.class)
public class APISetUp {
    public static final Logger logger = LoggerFactory.getLogger(APISetUp.class);
    /**
     * This method consist of before test method configuration functionalities
     */
    @BeforeSuite(enabled = true)
    public void connectDB() {
        //DatabaseUtils.connectToDB();
        logger.info("Successfully Connected to DB");
    }
    @BeforeClass
    public void bc(){
        System.out.println("=============================================================");
        logger.info("-------------- Before Class --------------");
    }
    @BeforeMethod
    public void bm(){
        System.out.println("=============================================================");
        logger.info("-------------- Before Method --------------");
    }
    @BeforeTest
    public void bt(){
        System.out.println("=============================================================");
        logger.info("-------------- Before Test  --------------");
    }

    @Test
    public void test1(){
        logger.info("-------------- Test 1  --------------");

        System.out.println("============================ TEST1 ============================");
    }
    @Test
    public void test2(){
        logger.info("-------------- Test 2 --------------");
        System.out.println("============================ TEST2 ============================");
    }
    @AfterTest
    public void at(){
        logger.info("-------------- After Test --------------");
        System.out.println("=============================================================");

    }
    @AfterMethod
    public void am(){
        logger.info("-------------- After Method --------------");
        System.out.println("=============================================================");

    }
    @AfterClass
    public void ac(){
        logger.info("-------------- After Class --------------");
        System.out.println("=============================================================");

    }

    /**
     * This method consist of after test method configuration functionalities
     */
    @AfterSuite(enabled = true)
    public void disconnectDB() {
        //DatabaseUtils.disConnectToDB();
        logger.info("Successfully Disconnected from DB");
    }

}
