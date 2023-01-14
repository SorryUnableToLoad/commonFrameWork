package sdet.testdatasupplier;

import io.github.sskorol.core.DataSupplier;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class UseCase1Test {

    //firstDimensions-->no. of times est will run
    //secondDimension-->no. of parameters passed to the test
    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[3][1];
        data[0][0] = 1;
        data[1][0] = 2;
        data[2][0] = 3;
        return data;
    }

    @Test(dataProvider = "getData")
    public void testDemo(int a) {
        System.out.println(a);
    }

    @DataSupplier
    public Stream<Integer> getData1() {
        return Stream.of(4, 5, 6);
    }

    @Test(dataProvider = "getData1")
    public void testDemo1(int a) {
        System.out.println(a);
    }

    @DataSupplier(indices = {0, 2})
    public Stream<Integer> getData2() {
        return Stream.of(7, 8, 9);
    }

    @Test(dataProvider = "getData2")
    public void testDemo2(int a) {
        System.out.println(a);
    }

    @DataSupplier(transpose = true)//1*3
    public Stream<Character> getData3() {
        return Stream.of('a', 'b', 'c');//1*3
    }

    @Test(dataProvider = "getData3")
    public void testDemo3(char a, char b, char c) {
        System.out.println(a + " " + b + " " + c);
    }

}
