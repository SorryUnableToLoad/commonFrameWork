package sdet.podam.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStringValue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @PodamStringValue(strValue = "Bangalore")
    protected String city;
    protected String street;
}
