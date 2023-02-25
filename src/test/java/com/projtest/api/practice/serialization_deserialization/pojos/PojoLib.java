package com.projtest.api.practice.serialization_deserialization.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PojoLib {
    private String name;
    private String empId;
    private String emailId;
    private long contactNumber;
}
