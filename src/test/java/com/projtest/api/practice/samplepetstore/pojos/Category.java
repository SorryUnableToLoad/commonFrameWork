package com.projtest.api.practice.samplepetstore.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    public int id;
    public String name;


}
