package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.*;
import com.panda.corp.macrocounter.macro.repository.MealRepository;
import com.panda.corp.macrocounter.macro.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MacroService {

    private final ProductRepository productRepository;
    private final MealRepository mealRepository;
    private final ProductMapper productMapper;
    DecimalFormat df = new DecimalFormat("#.##");

    public List<ProductDTO> getAvailableProducts() {
        return productMapper.convertEntityToDTO(productRepository.findAll());
    }

    public List<String> getAvailableProductNames() {
        return productMapper.convertEntityProductNameToDTOProductName(productRepository.findAll());
    }

    public MealMacroDTO getMealMacro(String username, MealDTO meal) {
        List<ProductEntity> productEntityList;
        productEntityList = productRepository.getAllByProductNameIn(meal.getProducts().stream().map(ProductDTO::getProductName).collect(Collectors.toList()));
        NutrientsCounter nutrientsCounter = new NutrientsCounter(productEntityList);

        double sumKcal = nutrientsCounter.sumKcal(meal);
        double sumCarbo = nutrientsCounter.sumCarbo(meal);
        double sumProtein = nutrientsCounter.sumProtein(meal);
        double sumFat = nutrientsCounter.sumFat(meal);

        MealEntity mealEntity = addMeal(meal);
        mealEntity.setUserName(username);
        mealEntity.setCalories(sumKcal);
        mealEntity.setCarbo(sumCarbo);
        mealEntity.setProtein(sumProtein);
        mealEntity.setFat(sumFat);
        mealRepository.save(mealEntity);

        return MealMacroDTO.of(roundToTwoDecimals(sumProtein), roundToTwoDecimals(sumFat), roundToTwoDecimals(sumCarbo), roundToTwoDecimals(sumKcal));
    }


    private MealEntity addMeal(MealDTO meal) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setDate(LocalDateTime.now());
        mealEntity.setProducts(productRepository.getAllByProductNameIn(meal.getProducts().stream().map(ProductDTO::getProductName).collect(Collectors.toList())));
        return mealEntity;
    }

    private double roundToTwoDecimals(double valueToBeRounded) {
        return Double.parseDouble(df.format(valueToBeRounded).replace(',', '.'));
    }


}
