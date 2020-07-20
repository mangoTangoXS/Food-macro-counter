package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MealDTO {

    private long mealID;
    private LocalDate date;
    private List<ProductDTO> products;
    private String userName;
    private double calories;
    private double carbo;
    private double protein;
    private double fat;
}
