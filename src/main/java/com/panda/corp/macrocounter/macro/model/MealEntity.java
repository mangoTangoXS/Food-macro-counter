package com.panda.corp.macrocounter.macro.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "MEALS")
public class MealEntity {

    @Id
    @Column(name = "ID")
    private long id;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_product",
            joinColumns = @JoinColumn(name = "mealid"),
            inverseJoinColumns = @JoinColumn(name = "productname")
    )
    private List<ProductEntity> products;
}
