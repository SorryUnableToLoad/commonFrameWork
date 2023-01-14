package sdet.externalfilereading.excelfile.zerocell;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ZerocellTestDataTest {


    @Test(dataProvider = "getData")
    public void zerocell(ZeroCellTestData data) {
        System.out.println(data.getFname());
        System.out.println(data.getIsboolean());
        System.out.println(data.getLname());
        System.out.println(data.getMobileno());
    }

    @DataProvider
    public Object[] getData() {
        List<ZeroCellTestData> data = ZeroCellReader.getTestdates();
        return data.toArray();
    }
}
