package com.projtest.api.practice.pojolibtest.deserialization;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.practice.pojolib.PojoLib;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Deserialization {
    @Test
    public void deserialization() throws IOException {

        PojoLib obj = new ObjectMapper().readValue(new File("./json/pojolibjson.json"), PojoLib.class);
        System.out.println(obj.getName());
        System.out.println(obj.getEmpId());
        System.out.println(obj.getEmailId());
        System.out.println(obj.getContactNumber());
    }
}
