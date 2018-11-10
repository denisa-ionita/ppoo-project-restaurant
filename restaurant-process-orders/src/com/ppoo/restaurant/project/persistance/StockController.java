package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.restaurantObjects.Menu;

import java.util.Date;
import java.util.List;


public class StockController {

    Menu stock;

    List<OrderOrStockItem> itemsFromStock;

    public StockController(Menu stock){
        stock.setStockDate(new Date());
        stock.setMenuList(getAllItemsFromStock());
    }

    public List<OrderOrStockItem> getAllItemsFromStock(){



        return itemsFromStock;
    }
}
