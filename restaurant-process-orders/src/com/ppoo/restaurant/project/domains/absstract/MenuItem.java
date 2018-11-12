package com.ppoo.restaurant.project.domains.absstract;

import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;

public abstract class MenuItem {

    private Long itemId;
    private String name;
    private Double price;
    private Integer vat;
    private MenuItemType menuItemType;

    public MenuItem(Long id, String name, Double price, MenuItemType menuItemType) throws InvalidInputException {
        this.itemId = id;
        this.name = name;
        this.price = price;
        this.menuItemType = menuItemType;

        if(name.length() < 2 || price == 0 || itemId < 1L)
            throw new InvalidInputException("ERROR: MenuItem nu a fost inregistrat! Verificati ca datele pe care le introduceti sunt valide!");
    }

    public MenuItem() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public MenuItemType getMenuItemType() {
        return menuItemType;
    }

    public void setMenuItemType(MenuItemType menuItemType) {
        this.menuItemType = menuItemType;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vat=" + vat +
                ", menuItemType=" + menuItemType +
                '}';
    }
}
