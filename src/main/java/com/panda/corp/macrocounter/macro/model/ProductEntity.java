package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    @Column(name = "product_name")
    private String productName;
    private String units;
    private long amount;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
    private String fruit_vegetable_check;
}
