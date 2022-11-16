package com.proj.test.base;

import com.proj.utils.DatabaseUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class APISetUp {
    @BeforeSuite(enabled = false)
    public void connectDB(){
        DatabaseUtils.connectToDB();
    }
    @AfterSuite(enabled = false)
    public void disconnectDB(){
        DatabaseUtils.disConnectToDB();
    }
}
