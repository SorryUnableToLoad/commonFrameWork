package javaPrectice.functional_interface;

import org.testng.annotations.Test;

public class TestRunner {
    public static void main(String[] args) {
       /*//by Conventional Way
        IPrintable obj = new LogFileImpl();
        obj.print();
        //by Anonymous Inner Class
        IPrintable obj1 = new IPrintable() {
            @Override
            public void print() {
                System.out.println("From Anonymous Inner Class");
            }
        };
        obj1.print();
        //by Functional Programming/interface
        //1.parameter of the abstract method and your lambda expression
        //2.return type of the abstract method and your lambda expression
        //3.only one abstract method or unimplemented method
        IPrintable obj2 = () -> System.out.println("I Am A Lambda Expression");
        obj2.print();*/
    }
    //----------------------------------------------------------------------------------------//

    /*@Test
    public void intParam() {
        IPrintable obj = (int a) -> System.out.println(a);
        IPrintable obj1 = a -> System.out.println(a);
        obj.print(6);
        obj1.print(8);
    }*/
    //----------------------------------------------------------------------------------------//

    /* @Test
     public void stringParam(){
         IPrintable obj=(String e)-> System.out.println(e);
         IPrintable obj1=(e)-> System.out.println(e);
         obj.print("java 8");
         obj1.print("java");
     }*/
    //----------------------------------------------------------------------------------------//

    /*@Test
    public void stringParam() {
        IPrintable obj = (a, b) -> System.out.println(a + " " + b);
        obj.print("appium", "selenium");
    }*/
    //----------------------------------------------------------------------------------------//

    /* @Test
     public void returnString() {
         IPrintable obj = () -> {
             return "appium";
         };
         System.out.println(obj.print());

         IPrintable obj1 = () -> "selenium";
         System.out.println(obj1.print());
     }*/
    /*@Test
    public void returnStringWithParam() {
        IPrintable obj = (String a) -> {
            return a.toUpperCase();
        };
        System.out.println(obj.print("appium"));

        IPrintable obj1 = (String a) -> a.toUpperCase();
        System.out.println(obj1.print("selenium"));

        IPrintable obj2 = a -> a.toUpperCase();
        System.out.println(obj2.print("suraj"));
    }*/
    @Test
    public void returnBoolean() {
        IPrintable obj = (String a) -> {
            return a.length() > 3;
        };
        System.out.println(obj.print("appium"));

        IPrintable obj1 = (String a) -> a.length() > 10;
        System.out.println(obj1.print("selenium"));

        IPrintable obj2 = a -> a.length() > 5;
        System.out.println(obj2.print("suraj"));

    }
}
