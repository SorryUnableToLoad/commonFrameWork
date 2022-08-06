package com.proj.test;

import com.proj.config.ConfigFactory;
import com.proj.config.FrameworkConfig;
import org.aeonbits.owner.ConfigCache;
import org.testng.annotations.Test;

public class DemoTest {
    @Test
    public void demoTest(){

        System.out.println(ConfigFactory.getConfig().browser());
    }

}
