package com.panda.corp.macrocounter.macro.model;


import lombok.Data;

@Data

public class FruitOrVegetableDTO {
    private String productName;
    private String units;
    private long amount;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
}
