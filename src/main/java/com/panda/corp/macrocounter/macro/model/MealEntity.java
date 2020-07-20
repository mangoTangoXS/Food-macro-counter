package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "MEALS")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private LocalDateTime date;

    @Column(name = "user_name")
    private String userName;
    private double calories;
    private double carbo;
    private double protein;
    private double fat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_product",
            joinColumns = @JoinColumn(name = "mealid"),
            inverseJoinColumns = @JoinColumn(name = "product_name")
    )
    private List<ProductEntity> products;
}
