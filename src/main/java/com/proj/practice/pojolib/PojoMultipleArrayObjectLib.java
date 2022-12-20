package com.proj.practice.pojolib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PojoMultipleArrayObjectLib {

       private Object[] pojoarrayobject;

       private int buildingNo;
       private String addressline_1;
       private String city;
       private String state;
       private String country;
       private int pincode;

}
