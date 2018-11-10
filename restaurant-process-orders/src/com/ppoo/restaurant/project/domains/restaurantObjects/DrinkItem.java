package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;

public class DrinkItem extends MenuItem {

    private Double alchoolDegrees;

    public DrinkItem(Long id, String name, Double price, Integer cantity, Double alchoolDegrees) {
        super(id, name, price);
        this.setVat(20);
        this.alchoolDegrees = alchoolDegrees;
    }

    public DrinkItem(Double alchoolDegrees) {
        this.alchoolDegrees = alchoolDegrees;
    }
}
