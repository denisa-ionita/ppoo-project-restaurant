package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.ppoo.restaurant.project.persistance.FileController;

import java.util.List;

public class Restaurant {

    public static void main(String[] args) {

        FileController fileController = new FileController();
//
//        RestaurantEmployee waiter = new Waiter(3L, "Gigel");
//        RestaurantEmployee admin = new Administrator(4L, "Denisa");
////
//        fileController.insertNewEmployee(waiter);
//        fileController.insertNewEmployee(admin);

//
//        List<RestaurantEmployee> list = fileController.getEmployeeList();
//        System.out.println(list.size());
        fileController.getEmployeeListFromFile();
        fileController.getMenu();
        fileController.getAllOrders();

        MainAppMenu mainAppMenu = new MainAppMenu(fileController);
        mainAppMenu.startMainMenu();

    }
}
