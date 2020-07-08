package com.panda.corp.macrocounter.macro.repository;

import com.panda.corp.macrocounter.macro.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
