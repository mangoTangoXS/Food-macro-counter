package com.panda.corp.macrocounter.macro.repository;

import com.panda.corp.macrocounter.macro.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

//    @Query(value = "SELECT * from meals" WHERE )
    List<ProductEntity> getAllByProductNameIn(List<String> products);

}
