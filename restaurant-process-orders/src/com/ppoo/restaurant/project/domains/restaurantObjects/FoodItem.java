package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;

public class FoodItem extends MenuItem {

    private Integer caloriesNumber;

    public FoodItem(Long id, String name, Double price, Integer cantity, Integer caloriesNumber) {
        super(id, name, price);
        this.setVat(9);
        this.caloriesNumber = caloriesNumber;
    }

    public FoodItem(Integer caloriesNumber) {
        this.caloriesNumber = caloriesNumber;
    }
}
