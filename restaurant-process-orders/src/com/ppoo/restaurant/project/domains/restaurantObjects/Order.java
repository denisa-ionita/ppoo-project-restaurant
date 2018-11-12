package com.ppoo.restaurant.project.domains.restaurantObjects;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.users.Waiter;

import java.util.Date;
import java.util.List;

public class Order {

    private Long orderId;
    private List<OrderItem> orderItemsList;
    private Waiter waiter;
    private Date orderDate;

    public Order(Long orderId, List<OrderItem> orderItemsList, Waiter waiter) {
        this.orderId = orderId;
        this.orderItemsList = orderItemsList;
        this.waiter = waiter;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderItemsList=" + orderItemsList +
                ", waiter=" + waiter +
                ", orderDate=" + orderDate +
                '}';
    }
}
