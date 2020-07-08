package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MealDTO {

    private long mealID;
    private LocalDate date;
    private List<ProductDTO> products;
}
