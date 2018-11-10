package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;

import java.io.File;
import java.util.List;

public class FileController {

    File file;

    List<MenuItem> menuItems;

    public File configureFile(String fileName){
        return new File(fileName);
    }

    public List<MenuItem> getMenu(){

        file = configureFile("meniu.txt");



        return menuItems;
    }

    public void updateMenuItem(MenuItem menuItem){

        file = configureFile("meniu.txt");


    }

    public void deleteMenuItem(MenuItem menuItem){

    }

    public void getAllOrders(){

    }

    public void getOrdersByDate(){

    }

    public void insertOrder(Order order){

        file = configureFile("comenzi-restaurant.txt");

    }

    public void updateOrder(Order order){

    }
}
