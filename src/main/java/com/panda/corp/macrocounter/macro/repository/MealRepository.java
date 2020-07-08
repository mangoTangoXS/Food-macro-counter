package com.panda.corp.macrocounter.macro.repository;

import com.panda.corp.macrocounter.macro.model.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {

    @Query(value = "SELECT * from meals")
    List<MealEntity> getMeals();
}
