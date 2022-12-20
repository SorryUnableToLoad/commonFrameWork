package com.proj.practice.pojolib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PojoLib {

    private String name;
    private String empId;
    private String emailId;
    private long contactNumber;

    public PojoLib(){}

}
