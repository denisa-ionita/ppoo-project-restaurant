package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.Item;

public class OrderItem{

    private Item item;
    private Integer itemCantity;

    public OrderItem(Item item, Integer noItems) {
        this.item = item;
        this.itemCantity = noItems;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemCantity() {
        return itemCantity;
    }

    public void setItemCantity(Integer itemCantity) {
        this.itemCantity = itemCantity;
    }
}