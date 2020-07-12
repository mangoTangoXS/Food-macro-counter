package com.panda.corp.macrocounter.macro.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<ProductDTO> convertEntityToDTO(List<ProductEntity> productEntities) {
        List<ProductDTO> productDTOList;
        productDTOList = productEntities.stream().map(this::convert).collect(Collectors.toList());
        return productDTOList;
    }

    private ProductDTO convert(ProductEntity productEntity) {
        return ProductDTO.of(
                productEntity.getProductName(),
                productEntity.getUnits(),
                productEntity.getAmount(),
                productEntity.getProtein(),
                productEntity.getFat(),
                productEntity.getCarbo(),
                productEntity.getKcal(),
                productEntity.getFruit_vegetable_check()
        );
    }
}
