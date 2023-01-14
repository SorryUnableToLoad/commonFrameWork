package sdet.externalfilereading.excelfile.zerocell;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public final class ZeroCellReader {
    private static List<ZeroCellTestData> testdatas = null;

    static {
        testdatas = Reader.of(ZeroCellTestData.class)
                .from(new File("./src/test/resources/testdata.xlsx"))
                .sheet("sheet1")
                .skipHeaderRow(true).list();
    }

    private ZeroCellReader() {
    }

    public static List<ZeroCellTestData> getTestdates() {
        return testdatas;
    }
}
