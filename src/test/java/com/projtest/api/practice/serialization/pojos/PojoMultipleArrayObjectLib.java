package com.projtest.api.practice.serialization.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PojoMultipleArrayObjectLib {

    private Object[] pojoarrayobject;
    private int buildingNo;
    private String addressLine_1;
    private String city;
    private String state;
    private String country;
    private int pinCode;

}
