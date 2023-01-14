package sdet.externalfilereading.excelfile.zerocell;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import io.github.sskorol.data.Sheet;

@Sheet(name = "Sheet1")
public class ZeroCellTestData {
    @Column(name = "fname", index = 0)
    private String fname;

    @Column(name = "lname", index = 1)
    private String lname;

    @Column(name = "mobileno", index = 2)
    private long mobileno;

    @Column(name = "isboolean", index = 3, converterClass = BooleanConverter.class)
    private boolean isboolean;

    @Override
    public String toString() {
        return "ZeroCellTestData{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", mobileno=" + mobileno +
                ", isboolean=" + isboolean +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public long getMobileno() {
        return mobileno;
    }

    public Boolean getIsboolean() {
        return isboolean;
    }
}
