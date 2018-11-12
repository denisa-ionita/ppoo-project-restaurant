package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;

public class FoodItem extends MenuItem {

    private Double caloriesNumber;

    public FoodItem(Long id, String name, Double price, MenuItemType menuItemType, Double caloriesNumber) throws InvalidInputException {
        super(id, name, price, menuItemType);
        this.setVat(9);
        if(caloriesNumber < 0)
            throw new InvalidInputException("ERROR: Bautura nu a fost inregistrata! Verificati ca datele pe care le introduceti sunt valide!");
        else
            this.caloriesNumber = caloriesNumber;
    }

    public Double getCaloriesNumber() {
        return caloriesNumber;
    }

    public void setCaloriesNumber(Double caloriesNumber) {
        this.caloriesNumber = caloriesNumber;
    }

    public FoodItem(Double caloriesNumber) {
        this.caloriesNumber = caloriesNumber;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "itemId=" + this.getItemId() +
                ", name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                ", vat=" + this.getVat() +
                ", caloriesNumber=" + caloriesNumber +
                '}';
    }
}
