package com.projtest.api.practice.samplepetstore;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DataProviderTest {
    //Object[][] is not always the return type
    // number of tests will be one if we use for loop

    @Test(dataProvider = "getData1")
    public void sampleDataProviderTest(Map<String, String> data) {
        System.out.println(data.get("username"));
        System.out.println(data.get("password"));
        System.out.println(data.get("email"));
        System.out.println("username = " + data.get("username"));
        System.out.println("password = " + data.get("password"));
        System.out.println("email = " + data.get("email"));
    }

    @DataProvider
    public Object[][] getData1() {
        Object[][] data = new Object[3][1];
        Map<String, String> map1 = new HashMap<>();
        map1.put("username", "sdf sf");
        map1.put("password", "sdfsffghf");
        map1.put("email", "sdfsffdhdf");

        Map<String, String> map2 = new HashMap<>();
        map2.put("username", "sfGuard");
        map2.put("password", "sdfhgdfsffghf");
        map2.put("email", "sdfsffdfgdhdf");

        Map<String, String> map3 = new HashMap<>();
        map3.put("username", "Sdf");
        map3.put("password", "sdfsffghf");
        map3.put("email", "sdfsffdfgdhdf");

        data[0][0] = map1;
        data[1][0] = map2;
        data[2][0] = map3;
        return data;
    }

    @Test(dataProvider = "getData")
    public void sampleDataProviderTest(String un, String pw, String em, String add, String code) {
        System.out.println(un);
        System.out.println(pw);
        System.out.println(em);
        System.out.println(add);
        System.out.println(code);
    }

    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method
        return new Object[][]{  //3*2
                {"abed", "doughs", "email0", "address", "responseCode"},
                {"eff", "dsfgdfgsg", "email6", "address", "responseCode"},
                {"nickle", "dfgdfgdg", "email9", "address", "responseCode"}
        };

        // map --> username-abed, password-sfdjsdf
        // map2
        //map3
        //list --> map1,map2,map3
    }
}
