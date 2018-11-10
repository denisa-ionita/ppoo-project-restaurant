package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.users.Waiter;

import java.util.List;

public class Order {

    private Long orderId;
    private List<MenuItem> orderItemsList;
    private Waiter waiter;

    public Order(Long orderId, List<MenuItem> orderItemsList, Waiter waiter) {
        this.orderId = orderId;
        this.orderItemsList = orderItemsList;
        this.waiter = waiter;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<MenuItem> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<MenuItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
