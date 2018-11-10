package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Menu;

import java.util.Date;
import java.util.List;


public class StockController {

    Menu stock;

    List<MenuItem> itemsFromStock;

    public StockController(Menu stock){
        stock.setMenuList(getAllItemsFromStock());
    }

    public List<MenuItem> getAllItemsFromStock(){



        return itemsFromStock;
    }
}
