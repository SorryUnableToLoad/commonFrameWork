package com.proj.utils.etc;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains generic methods with respect to java
 *
 * @author surajkumarnaganuri
 */
public class JavaUtility {
    /**
     * This method will generate random number and return the number to caller
     *
     * @return
     */
    public int getRandomNumber() {
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(1000);
        return num;
    }

    /**
     * This method is generate current date and return the current-date to caller
     *
     * @return
     */
    public String getCurrentDate() {
        Date date = new Date();
        String currenrDate = date.toString();
        return currenrDate;
    }

    /**
     * This method is generate date in customised and return to the caller
     *
     * @return
     */
    public String getDate() {
        Date date = new Date();
        String systemdate = date.toString();
        String[] ds = systemdate.split(" ");
        String weekday = ds[0]; //mon,sun,tue,wed
        String mon = ds[1];
        String dd = ds[2];
        String time = ds[3]; //hh:mm:ss
        String yyyy = ds[5];

        String today = yyyy + " " + mon + " " + dd;

        return today;

    }

    public String oderDate() {
        return new SimpleDateFormat(("dd MMM, yyyy")).format(new Date());
    }

    public String deliveryDate() {
        String timestamp = new SimpleDateFormat(("MMM, yyyy")).format(new Date());
        return (Integer.parseInt(new SimpleDateFormat(("dd")).format(new Date()).split(" ")[0]) + 1) + " " + timestamp;
    }


    @Test
    public void run() {
        System.out.println(deliveryDate());
        System.out.println(oderDate());
    }
}
