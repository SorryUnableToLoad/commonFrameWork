package com.projtest.api.serialization_deserialization;

import com.projtest.api.serialization_deserialization.pojos.PojoArrayObjectLib;
import com.projtest.api.serialization_deserialization.pojos.PojoMultipleArrayObjectLib;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PojoMultipleArrayObjectTest {

    @Test
    public void serialization() throws IOException {
        long[] contactNumber1 = {1234567890L, 2345678901L};
        long[] contactNumber2 = {2345679876L, 9876543456709L};

        PojoArrayObjectLib pao1 = new PojoArrayObjectLib("Suraj", "E345", "acded@gmail.com", contactNumber1);
        PojoArrayObjectLib pao2 = new PojoArrayObjectLib("Varun", "E346", "acded@gmail.com", contactNumber2);
        Object[] pao = {pao1, pao2};

        PojoMultipleArrayObjectLib pojoMultipleArrayObjectLib = new PojoMultipleArrayObjectLib(pao, 456, "line23", "bangalore", "karnataka", "india", 567432);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(pao1));
        System.out.println(mapper.writeValueAsString(pao2));
        System.out.println(mapper.writeValueAsString(pojoMultipleArrayObjectLib));


        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("./jsonFiles/multipleArrayObject.json"), pojoMultipleArrayObjectLib);

    }

}
