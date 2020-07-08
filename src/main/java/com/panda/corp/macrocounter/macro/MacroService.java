package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.*;
import com.panda.corp.macrocounter.macro.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MacroService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAvailableProducts() {
        return productMapper.convertEntityToDTO(productRepository.findAll());
    }

    public MealMacroDTO getMealMacro(MealDTO meal) {
        return null;
    }

    private double calculateCalories(ProductEntity productEntity, double amount) {
        return (productEntity.getKcal() * amount) / productEntity.getAmount();
    }
}
