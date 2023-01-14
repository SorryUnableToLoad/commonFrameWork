package com.projtest.api.practice.serialization;

import com.projtest.api.practice.serialization.pojos.PojoArrayObjectLib;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PojoArrayObjectTest {
    //Serialization
    @Test
    public void pojoArrayObjectTest() throws IOException {

        long[] contactNumbers = {1234567890L, 9987654321L, 6789054321L};
        PojoArrayObjectLib pojoArrayObjectLib = new PojoArrayObjectLib("Suraj", "E123", "abc@gmail.com", contactNumbers);

        ObjectMapper mapper = new ObjectMapper();
        String valueAsString = mapper.writeValueAsString(pojoArrayObjectLib);
        System.out.println(valueAsString);

        ObjectWriter value = mapper.writerWithDefaultPrettyPrinter();
        File file = new File("./jsonFiles/pojoArrayJson.json");
        value.writeValue(file, pojoArrayObjectLib);
    }
}
