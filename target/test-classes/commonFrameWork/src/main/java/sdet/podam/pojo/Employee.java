package sdet.podam.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdet.podam.strategy.DesignationStrategy;
import sdet.podam.strategy.NameStrategy;
import uk.co.jemos.podam.common.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    //@PodamIntValue(minValue = 100,maxValue = 999)
    @PodamIntValue(numValue = "300")
    private int id;

    //@PodamStringValue(strValue = "Suraj")
    @PodamStringValue(length = 6)
    @PodamStrategyValue(value = NameStrategy.class)
    private String fname;

    @PodamExclude
    private String lastName;

    private Boolean isFTE;


    private Address address;

    @PodamCollection(nbrElements = 3, mapElementStrategy = DesignationStrategy.class)
    private List<String> roles;

   /* @PodamCollection(nbrElements = 5)
    private Map<Integer, String> map;*/
}
