package com.ppoo.restaurant.project.domains.restaurantObjects;

import java.util.Date;
import java.util.List;

public class Stock {

    private List<OrderItem> stockList;
    private Date stockDate;

    public Stock() {
    }

    public Stock(List<OrderItem> stockList, Date stockDate) {
        this.stockList = stockList;
        this.stockDate = stockDate;
    }

    public List<OrderItem> getStockList() {
        return stockList;
    }

    public void setStockList(List<OrderItem> stockList) {
        this.stockList = stockList;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }
}
