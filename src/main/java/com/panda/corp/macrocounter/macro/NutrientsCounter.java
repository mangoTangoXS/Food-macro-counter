package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.MealDTO;
import com.panda.corp.macrocounter.macro.model.ProductEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutrientsCounter {

    private Map<String, ProductEntity> productEntityMap;

    public NutrientsCounter(List<ProductEntity> productEntities) {
        productEntityMap = new HashMap<>();
        productEntities
                .forEach(productEntity -> productEntityMap.put(productEntity.getProductName(), productEntity));
    }

    public double sumKcal(MealDTO meal) {
        return meal.getProducts()
                .stream()
                .map(product -> calculateKcal(product.getAmount(), productEntityMap.get(product.getProductName())))
                .mapToDouble(Double::doubleValue).sum();
    }


    public double sumCarbo(MealDTO meal) {
        return meal.getProducts()
                .stream()
                .map(product -> calculateCarbo(product.getAmount(), productEntityMap.get(product.getProductName())))
                .mapToDouble(Double::doubleValue).sum();
    }

    public double sumProtein(MealDTO meal) {
        return meal.getProducts()
                .stream()
                .map(product -> calculateProtein(product.getAmount(), productEntityMap.get(product.getProductName())))
                .mapToDouble(Double::doubleValue).sum();
    }

    public double sumFat(MealDTO meal) {
        return meal.getProducts()
                .stream()
                .map(product -> calculateFat(product.getAmount(), productEntityMap.get(product.getProductName())))
                .mapToDouble(Double::doubleValue).sum();
    }

    private double calculateCarbo(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getCarbo());
    }

    private double calculateKcal(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getKcal());
    }

    private double calculateProtein(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getProtein());
    }

    private double calculateFat(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getFat());
    }

    private double calculateNutrients(ProductEntity productEntity, double amount, double nutrients) {
        return (nutrients * amount) / productEntity.getAmount();
    }

}
