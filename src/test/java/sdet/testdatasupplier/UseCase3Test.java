package sdet.testdatasupplier;

import io.github.sskorol.core.DataSupplier;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class UseCase3Test {
    @Test(dataProvider = "getData")
    public void testDemo(String[] data) {
        System.out.println(data[0]);
        System.out.println(data[1]);
    }

    @DataProvider
    public Object[] getData() {
        return Stream.of("suraj", "testing", "automation")
                .map(e -> new String[]{e, e + "test"})
                .toArray();
    }

    @Test(dataProvider = "getDataAsFlatMap")
    public void testDemo1(String data, String data1) {
        System.out.println(data);
        System.out.println(data1);
    }

    @DataSupplier(flatMap = true)
    public Stream<String[]> getDataAsFlatMap() {
        return Stream.of("suraj", "testing", "automation")
                .map(e -> new String[]{e, e + "test"});
    }
}
