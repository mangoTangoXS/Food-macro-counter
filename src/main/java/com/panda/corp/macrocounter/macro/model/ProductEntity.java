package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    @Column(name = "productname")
    private String productName;

    private String units;
    private long amount;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
}
