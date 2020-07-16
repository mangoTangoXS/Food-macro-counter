package com.panda.corp.macrocounter.macro.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class MealMacroDTO {

    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
}
