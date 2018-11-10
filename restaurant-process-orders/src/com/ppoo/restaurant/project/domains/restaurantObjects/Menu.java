package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;

import java.util.List;

public class Menu {

    private List<MenuItem> menuList;

    public Menu() {
    }

    public Menu(List<MenuItem> stockList) {
        this.menuList = stockList;
    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }
}
