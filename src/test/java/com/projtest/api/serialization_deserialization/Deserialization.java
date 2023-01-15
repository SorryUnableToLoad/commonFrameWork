package com.projtest.api.serialization_deserialization;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.projtest.api.serialization_deserialization.pojos.PojoLib;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Deserialization {
    @Test
    public void deserializationTest() throws IOException {

        PojoLib obj = new ObjectMapper().readValue(new File("./jsonFiles/pojoLibJson.json"), PojoLib.class);
        System.out.println(obj.getName());
        System.out.println(obj.getEmpId());
        System.out.println(obj.getEmailId());
        System.out.println(obj.getContactNumber());
    }
}
