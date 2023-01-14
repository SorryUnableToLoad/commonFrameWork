package com.projtest.api.practice.serialization;

import com.projtest.api.practice.serialization.pojos.PojoLib;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PojoLibTest {
    @Test
    public void serialization() throws IOException {

        //we are creating an object into library
        PojoLib pojoLib = new PojoLib("Suraj", "E987", "sjn6724@gmail.com", 7019020148L);
        //converting java object into json
        //creating object of ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //converting printing the json object into the console
        System.out.println(mapper.writeValueAsString(pojoLib));

        //converting and generating a json file and json data
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("./jsonFiles/pojoLibJson.json"), pojoLib);
    }
}
