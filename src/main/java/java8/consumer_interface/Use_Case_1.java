package java8.consumer_interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Use_Case_1 {
    //Abstract method name : accept
    //Default methods in interface : andThen
    //Use Case 1 : forEach method
    //Use Case 2 : selectDropDown
    //Bi Type : BiConsumer
    //Using in test Automation framework to log details to extent report and consoles

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Surajkumar", "Naganuri", "SDET");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("++++++++++++++++++++");
        for (String temp : list) {
            System.out.println(temp);
        }
        System.out.println("++++++++++++++++++++");
        Consumer<String> consumer = (s) -> System.out.println(s);
        Consumer<String> consumer1= (s) -> System.out.println(s.toUpperCase());

        list.forEach(consumer);
        System.out.println("++++++++++++++++++++");
        list.forEach(s -> System.out.println(s.startsWith("N")));
        System.out.println("++++++++++++++++++++");
        list.forEach(s -> System.out.println(s.toUpperCase()));
        System.out.println("++++++++++++++++++++");
        list.forEach(consumer.andThen(consumer1));
    }
}
