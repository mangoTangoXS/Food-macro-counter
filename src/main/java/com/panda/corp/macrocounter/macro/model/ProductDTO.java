package com.panda.corp.macrocounter.macro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductDTO {

    private String productName;
    private String units;
    private long amount;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
}
