package com.proj.practice.pojolib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PojoArrayObjectLib {

    private String name;
    private String empId;
    private String emailId;
    private long[] contactnumber;

}
