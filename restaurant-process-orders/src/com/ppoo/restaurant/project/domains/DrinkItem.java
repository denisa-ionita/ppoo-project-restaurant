package com.ppoo.restaurant.project.domains;

public class DrinkItem extends Item {

    private Double alchoolDegrees;

    public DrinkItem(Long id, String name, Double price, Integer cantity, Double alchoolDegrees) {
        super(id, name, price, cantity);
        this.setVat(20);
        this.alchoolDegrees = alchoolDegrees;
    }

    public DrinkItem(Double alchoolDegrees) {
        this.alchoolDegrees = alchoolDegrees;
    }
}
