package sdet.testdatasupplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    @Column(name = "fname", index = 0)
    private String fname;
    @Column(name = "lname", index = 1)
    private String lname;
    @Column(name = "mobileno", index = 2)
    private long mobileno;
    @Column(name = "isboolean", index = 3,converterClass = BooleanConverter.class)
    private boolean isboolean;
}
