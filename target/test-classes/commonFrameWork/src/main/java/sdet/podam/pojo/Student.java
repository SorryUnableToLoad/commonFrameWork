package sdet.podam.pojo;

import lombok.Data;

@Data
public class Student<S, I, B> {
    private S name;
    private I lastName;
    private B isPresent;

}
