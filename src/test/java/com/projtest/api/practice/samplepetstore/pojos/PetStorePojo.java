package com.projtest.api.practice.samplepetstore.pojos;

import lombok.*;

import java.util.List;

@Data
@Builder
//@JsonIgnoreProperties(value = {"status"})
public class PetStorePojo {

    public int id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tags[] tags;
    public String status;
}
