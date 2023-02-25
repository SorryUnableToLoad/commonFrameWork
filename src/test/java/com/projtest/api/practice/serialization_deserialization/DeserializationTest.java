package com.projtest.api.practice.serialization_deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projtest.api.practice.serialization_deserialization.pojos.PojoArrayObject;
import com.projtest.api.practice.serialization_deserialization.pojos.PojoMultipleArrayObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DeserializationTest {
    @Test
    public void deserialization() throws IOException {

        PojoMultipleArrayObject pojoMultipleArrayObject = new ObjectMapper()
                .readValue(new File("./src/test/resources/api-resources/jsonFiles/multipleArrayObject.json"), PojoMultipleArrayObject.class);
        System.out.println(pojoMultipleArrayObject.getBuildingNo());
        System.out.println(pojoMultipleArrayObject.getAddressLine_1());
        System.out.println(pojoMultipleArrayObject.getCity());
        System.out.println(pojoMultipleArrayObject.getState());
        System.out.println(pojoMultipleArrayObject.getCountry());
        System.out.println(pojoMultipleArrayObject.getPinCode());

        PojoArrayObject[] pojoArrayObject = pojoMultipleArrayObject.getPojoArrayObject();
        for ( PojoArrayObject pojoArrayObjectData: pojoArrayObject) {
            System.out.println(pojoArrayObjectData.getName());
            System.out.println(pojoArrayObjectData.getEmpId());
            System.out.println(pojoArrayObjectData.getEmailId());
            long[] contactNumber = pojoArrayObjectData.getContactNumber();
            for (long num:contactNumber) {
                System.out.println(num);
            }
            System.out.println();
        }


    }
}
