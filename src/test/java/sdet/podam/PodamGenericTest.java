package sdet.podam;

import org.testng.annotations.Test;
import sdet.podam.pojo.Student;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PodamGenericTest {
    @Test
    public void test() {
        PodamFactory factory = new PodamFactoryImpl();
        Student student = factory.manufacturePojo(Student.class, String.class, Integer.class, Boolean.class);
        System.out.println(student);
    }
}
