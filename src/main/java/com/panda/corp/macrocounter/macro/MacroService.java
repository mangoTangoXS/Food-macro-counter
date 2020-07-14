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

    public List<String> getAvailableProductNames() {
        return productMapper.convertEntityProductNameToDTOProductName(productRepository.findAll());
    }

    public MealMacroDTO getMealMacro(MealDTO meal) {
        Map<String, ProductEntity> productEntityMap = new HashMap<>();
        productRepository.getAllByProductNameIn(meal.getProducts().stream().map(ProductDTO::getProductName).collect(Collectors.toList()))
                .forEach(productEntity -> productEntityMap.put(productEntity.getProductName(), productEntity));

        double sumKcal = meal.getProducts()
                .stream()
                .map(productDTO -> calculateKcal(productDTO.getAmount(),productEntityMap.get(productDTO.getProductName())))
                .mapToDouble(Double::doubleValue).sum();
        double sumCarbo = meal.getProducts()
                .stream()
                .map(productDTO -> calculateCarbo(productDTO.getAmount(), productEntityMap.get(productDTO.getProductName())))
                .mapToDouble(Double::doubleValue).sum();

        return MealMacroDTO.of(sumCarbo,sumKcal);
    }

    private double calculateCarbo(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getCarbo());
    }
    private double calculateKcal(long amount, ProductEntity productEntity) {
        return calculateNutrients(productEntity, amount, productEntity.getKcal());
    }

    private double calculateNutrients(ProductEntity productEntity, double amount, double nutrients) {
        return (nutrients * amount) / productEntity.getAmount();
    }

}
