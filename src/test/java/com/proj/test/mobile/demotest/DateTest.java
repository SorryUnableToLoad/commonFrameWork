package com.proj.test.mobile.demotest;

import com.proj.utils.etc.JavaUtility;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    @Test
    public void customdateformate() {
        Date d = new Date();
        System.out.println(d);
    }

    @Test
    public void sample() {
        String timestamp = new SimpleDateFormat(("yyyy MMM dd hh_mm_ss")).format(new Date());
        System.out.println(timestamp);
        String timestamp1 = new SimpleDateFormat(("dd MMM, yyyy")).format(new Date());
        System.out.println(timestamp1);
        //25 Jan, 2023
    }

    @Test
    public void splitdate() {
        String timestamp = new SimpleDateFormat(("MMM, yyyy")).format(new Date());
        System.out.println(timestamp);
        System.out.println((Integer.parseInt(new SimpleDateFormat(("dd")).format(new Date()).split(" ")[0]) + 1) + " " + timestamp);
        String dddd = (Integer.parseInt(new SimpleDateFormat(("dd")).format(new Date()).split(" ")[0]) + 1) + " " + timestamp;
    }

    public String oderDate() {
        return new SimpleDateFormat(("dd MMM, yyyy")).format(new Date());
    }

    public String deliveryDate() {
        String timestamp = new SimpleDateFormat(("MMM, yyyy")).format(new Date());
        return (Integer.parseInt(new SimpleDateFormat(("dd")).format(new Date()).split(" ")[0]) + 1) + " " + timestamp;
    }


    public String ele(String delivery_date, String total_order_count, String order_id, String status, String order_date, String no_of_items, String total_qty, String total_amount) {
        return ("Delivery Date: " + delivery_date + "\n" + "Total order " + total_order_count + "\n" +
                "Order ID: " + order_id + "\n" +
                "" + status + "\n" +
                "Order Date\n" +
                "" + order_date + "\n" +
                "No. of Items\n" +
                "" + no_of_items + " item\n" +
                "Total Quantity\n" +
                "" + total_qty + ".0 kgs\n" +
                "Total Amount\n" +
                " ₹ " + total_amount + ".00']");
    }

    @Test
    public void run() {
        System.out.println(ele(new JavaUtility().deliveryDate(), "1", "123456", "delivered", new JavaUtility().oderDate(), "5", "10", "2000"));
    }
}
