package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;

public class OrderItem {

    private MenuItem item;
    private Integer itemCantity;

    public OrderItem(MenuItem item, Integer noItems) {
        this.item = item;
        this.itemCantity = noItems;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public Integer getItemCantity() {
        return itemCantity;
    }

    public void setItemCantity(Integer itemCantity) {
        this.itemCantity = itemCantity;
    }
}
