package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fruits_amd_vegetables")

public class FruitOrVegetableEntity {
    @Id
    @Column
    private String productName;

    private String units;
    private long amount;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
}
