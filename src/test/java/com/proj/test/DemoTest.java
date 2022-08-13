package com.proj.test;

import com.proj.config.ConfigFactory;
import org.testng.annotations.Test;

public class DemoTest extends WebBaseClass{
    @Test
    public void demoTest(){
        System.out.println("hi welcome");
        System.out.println(ConfigFactory.getConfig().webBrowser());
        System.out.println(ConfigFactory.getConfig().browserStackURL());
        System.out.println(ConfigFactory.getConfig().seleniumGridUrl());
        System.out.println(ConfigFactory.getConfig().selenoidUrl());

    }
}
