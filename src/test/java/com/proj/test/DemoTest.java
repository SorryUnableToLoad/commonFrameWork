package com.proj.test;

import com.proj.config.ConfigFactory;
import org.testng.annotations.Test;

public class DemoTest {
    @Test
    public void demoTest(){

        System.out.println(ConfigFactory.getConfig().browser());
    }

}
