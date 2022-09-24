package com.proj.test.mobile;

import com.proj.base.MobileBaseClass;
import com.proj.driver.DriverManager;
import org.testng.annotations.Test;


public class AndroidTest extends MobileBaseClass {

    @Test
    public void testLoginAndroid() {

        System.out.println("running");
        DriverManager.getCurrentDriver();
        /*LoginPageDemo lp=new LoginPageDemo();
        lp.loginToApplication();*/

        /*Trial tr = new Trial();
        tr.login();*/
    }
}


