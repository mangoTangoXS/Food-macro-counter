package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.*;
import com.panda.corp.macrocounter.macro.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

class MacroServiceTest {

    private MacroService macroService;
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productMapper = new ProductMapper();
        macroService = new MacroService(productRepository, productMapper);
    }

    @Test
    void shouldReturnAvailableProducts() {
        //given
        Mockito.when(productRepository.findAll())
                .thenReturn(createProductEntities());

        //when
        List<ProductDTO> products = macroService.getAvailableProducts();

        //then
        assertEquals(products.get(0).getCarbo(), createProductEntities().get(0).getCarbo());
    }

    //getMealMacro: MealDTO -> MealMacroDTO
    @Test
    void shouldReturnMacroCalculationForGivenMeal() {
        //given
        MealDTO mealDTO = new MealDTO();
        mealDTO.setDate(LocalDate.now());
        mealDTO.setMealID(1);
        mealDTO.setProducts(createProductDTO());
//        List<String> products = new ArrayList<>();
//        products.add("Olive");
//        products.add("Grapes");
        /*Mockito.when(productRepository.getOne("Olive"))
                .thenReturn(createProductEntities().get(0));*/
        Mockito.when(productRepository.getAllByProductNameIn(anyList()))
                .thenReturn(createProductEntities());
        MealMacroDTO breakfast = macroService.getMealMacro(mealDTO);

        //then
        assertNotNull(breakfast);
        assertEquals(396, breakfast.getKcal());
    }

    private List<ProductEntity> createProductEntities() {
        List<ProductEntity> productEntities = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("Olive");
        productEntity.setUnits("g");
        productEntity.setKcal(980);
        productEntity.setAmount(100);
        productEntity.setCarbo(31);
        productEntity.setProtein(22);
        productEntity.setFat(4);
        productEntities.add(productEntity);

        productEntity = new ProductEntity();
        productEntity.setProductName("Grapes");
        productEntity.setUnits("g");
        productEntity.setKcal(150);
        productEntity.setAmount(120);
        productEntity.setCarbo(31);
        productEntity.setProtein(22);
        productEntity.setFat(4);
        productEntities.add(productEntity);
        return productEntities;
    }


    private List<ProductDTO> createProductDTO() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Olive");
        productDTO.setAmount(20);
        productDTOs.add(productDTO);

        productDTO = new ProductDTO();
        productDTO.setProductName("Grapes");
        productDTO.setAmount(160);
        productDTOs.add(productDTO);
        return productDTOs;
    }
}