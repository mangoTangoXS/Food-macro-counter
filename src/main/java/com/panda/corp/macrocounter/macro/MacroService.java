package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.*;
import com.panda.corp.macrocounter.macro.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MacroService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAvailableProducts() {
        return productMapper.convertEntityToDTO(productRepository.findAll());
    }


    public MealMacroDTO getMealMacro(MealDTO meal) {
        Map<String, ProductEntity> productEntityMap = new HashMap<>();
        productRepository.getAllByProductNameIn(meal.getProducts().stream().map(ProductDTO::getProductName).collect(Collectors.toList()))
                .forEach(productEntity -> productEntityMap.put(productEntity.getProductName(), productEntity));

        List<Double> calories = meal.getProducts()
                .stream()
                .map(productDTO -> calculateCalories(productEntityMap.get(productDTO.getProductName()), productDTO.getAmount()))
                .collect(Collectors.toList());
        double sumKcal = calories.stream().mapToDouble(Double::doubleValue).sum();
        return MealMacroDTO.of(sumKcal);
    }

    private double calculateCalories(ProductEntity productEntity, double amount) {
        return (productEntity.getKcal() * amount) / productEntity.getAmount();
    }

}
