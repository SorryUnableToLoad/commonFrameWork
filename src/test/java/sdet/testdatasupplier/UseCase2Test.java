package sdet.testdatasupplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.CsvReader;
import io.github.sskorol.data.JsonReader;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import static io.github.sskorol.data.TestDataReader.use;

public class UseCase2Test {
    @DataProvider
    public Object[] getData() throws IOException {
        List<TestData> users = new ObjectMapper()
                .readValue(new File(System.getProperty("user.dir") + "/src/test/resources/testdata.json"), new TypeReference<>() {
                });
        return users.toArray();
    }

    @Test(dataProvider = "getData")
    public void testDemo(TestData td) {
        System.out.println(td);
    }

    @DataSupplier
    public StreamEx<TestData> getDataFromJson() {
        return use(JsonReader.class)
                .withTarget(TestData.class)
                .withSource("testdata.json")
                .read();
    }

    @Test(dataProvider = "getDataFromJson")
    public void readjson(TestData td) {
        System.out.println(td);
    }

    @DataSupplier
    public StreamEx<TestData1> getDataFromCsv() {
        return use(CsvReader.class)
                .withTarget(TestData1.class)
                .withSource("testdata.csv")
                .read();
    }

    @Test(dataProvider = "getDataFromCsv")
    public void readCsv(TestData1 testData) {
        System.out.println(testData);
    }

    @DataSupplier
    public StreamEx<TestData> getDataFromExcel() {
        return use(XlsxReader.class)
                .withTarget(TestData.class)
                .withSource("testdata.xlsx")
                .read();
    }

    @Test(dataProvider = "getDataFromExcel")
    public void readXlsx(TestData e) {
        System.out.println(e);
    }
    @DataSupplier
    public StreamEx<TestData> getDataFromExcel1(Method mtd) {
        return use(XlsxReader.class)
                .withTarget(TestData.class)
                .withSource("testdata.xlsx")
                .read().filter(e->e.getFname().equalsIgnoreCase(mtd.getName()));
    }

    @Test(dataProvider = "getDataFromExcel1")
    public void suraj2(TestData e) {
        System.out.println(e);
    }

}
