package com.projtest.api.practice.serialization_deserialization;

import com.projtest.api.practice.serialization_deserialization.pojos.PojoArrayObject;
import com.projtest.api.practice.serialization_deserialization.pojos.PojoMultipleArrayObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PojoMultipleArrayObjectTest {

    @Test
    public void serialization() throws IOException {
        long[] contactNumber1 = {1234567890L, 2345678901L};
        long[] contactNumber2 = {2345679876L, 9876543456709L};

        PojoArrayObject pao1 = new PojoArrayObject("Suraj", "E345", "acded@gmail.com", contactNumber1);
        PojoArrayObject pao2 = new PojoArrayObject("Varun", "E346", "acded@gmail.com", contactNumber2);
        PojoArrayObject[] pojoArrayObject = new PojoArrayObject[]{pao1, pao2};

        PojoMultipleArrayObject pojoMultipleArrayObjectLib = new PojoMultipleArrayObject(pojoArrayObject, 456, "line23", "bangalore", "karnataka", "india", 567432);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(pao1));
        System.out.println(mapper.writeValueAsString(pao2));
        System.out.println(mapper.writeValueAsString(pojoMultipleArrayObjectLib));


        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("./src/test/resources/api-resources/jsonFiles/multipleArrayObject.json"), pojoMultipleArrayObjectLib);

    }

}
