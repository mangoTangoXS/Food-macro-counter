package com.panda.corp.macrocounter.macro.model;

public interface Counter {
    String showProductsList();
    MealMacroDTO countMealMacro(String productName, double amount);
}
