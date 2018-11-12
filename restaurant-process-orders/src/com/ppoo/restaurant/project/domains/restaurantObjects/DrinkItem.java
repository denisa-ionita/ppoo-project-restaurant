package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;

public class DrinkItem extends MenuItem {

    private Double alchoolDegrees;

    public DrinkItem(Long id, String name, Double price, MenuItemType menuItemType, Double alchoolDegrees) throws InvalidInputException {
        super(id, name, price, menuItemType);
        this.setVat(20);
        if(alchoolDegrees < 0)
            throw new InvalidInputException("ERROR: Bautura nu a fost inregistrata! Verificati ca datele pe care le introduceti sunt valide!");
        else
            this.alchoolDegrees = alchoolDegrees;
    }

    public Double getAlchoolDegrees() {
        return alchoolDegrees;
    }

    public void setAlchoolDegrees(Double alchoolDegrees) {
        this.alchoolDegrees = alchoolDegrees;
    }

    public DrinkItem(Double alchoolDegrees) {
        this.alchoolDegrees = alchoolDegrees;
    }

    @Override
    public String toString() {
        return "DrinkItem{" +
                "itemId=" + this.getItemId() +
                ", name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                ", vat=" + this.getVat() +
                ", alchoolDegrees=" + alchoolDegrees +
                '}';
    }


}
