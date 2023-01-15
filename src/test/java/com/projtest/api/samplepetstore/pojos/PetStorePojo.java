package com.projtest.api.samplepetstore.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"status"})
public class PetStorePojo {

    private int id;
    private Category1 category;
    private String name;
    private List photoUrls;
    private List tags;
    private String status;
}
