package sdet.externalfilereading.excelfile.zerocell;

import com.creditdatamw.zerocell.Reader;
import sdet.externalfilereading.excelfile.zerocell.ZeroCellTestData;

import java.io.File;
import java.util.List;

public class ZeroCellRunner {
    public static void main(String[] args) {
        List<ZeroCellTestData> testdates = Reader.of(ZeroCellTestData.class)
                .from(new File("./src/test/resources/testdata.xlsx"))
                .sheet("sheet1")
                .skipHeaderRow(true).list();

        testdates.forEach(System.out::println);
    }
}
