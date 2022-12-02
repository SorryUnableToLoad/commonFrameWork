package com.proj.test.base;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.utils.DatabaseUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.proj.listeners.TestListenerImpl.class)
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
