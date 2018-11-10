package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.users.Waiter;

import java.util.List;

public class Order {

    private Long orderId;
    private List<OrderItem> orderItemsList;
    private Waiter waiter;

    public Order(Long orderId, List<OrderItem> orderItemsList, Waiter waiter) {
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

    public List<OrderItem> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
