package com.projtest.api.practice.serialization_deserialization.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PojoArrayObject {

    private String name;
    private String empId;
    private String emailId;
    private long[] contactNumber;

}
