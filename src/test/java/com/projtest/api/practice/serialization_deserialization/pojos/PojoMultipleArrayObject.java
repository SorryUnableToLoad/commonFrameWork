package com.projtest.api.practice.serialization_deserialization.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PojoMultipleArrayObject {

    private PojoArrayObject[] pojoArrayObject;
    private int buildingNo;
    private String addressLine_1;
    private String city;
    private String state;
    private String country;
    private int pinCode;

}
