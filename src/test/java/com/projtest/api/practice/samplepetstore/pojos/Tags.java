package com.projtest.api.practice.samplepetstore.pojos;

import lombok.*;

@Data
@Builder
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder(value = {"String", "Integer"})
public class Tags {
    public Integer id;
    public String name;
}
