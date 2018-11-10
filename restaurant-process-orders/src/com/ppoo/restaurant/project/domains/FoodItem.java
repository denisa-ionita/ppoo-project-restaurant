package com.ppoo.restaurant.project.domains;

public class FoodItem extends Item {

    private Integer caloriesNumber;

    public FoodItem(Long id, String name, Double price, Integer cantity, Integer caloriesNumber) {
        super(id, name, price, cantity);
        this.setVat(9);
        this.caloriesNumber = caloriesNumber;
    }

    public FoodItem(Integer caloriesNumber) {
        this.caloriesNumber = caloriesNumber;
    }
}
