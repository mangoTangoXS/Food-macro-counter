package com.panda.corp.macrocounter.macro.repository;

import com.panda.corp.macrocounter.macro.model.MealEntity;
import com.panda.corp.macrocounter.macro.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<MealEntity, Long> {
}
