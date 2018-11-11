package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.ppoo.restaurant.project.persistance.FileController;

import java.util.List;

public class Restaurant {

    public static void main(String[] args) {

        FileController fileController = new FileController();

//        RestaurantEmployee waiter = new Waiter(1L, "Gigel");
//        RestaurantEmployee admin = new Administrator(2L, "Diana");
//
//        fileController.insertNewEmployee(waiter);
//        fileController.insertNewEmployee(admin);

        fileController.getEmployeeListFromFile();
//
//        List<RestaurantEmployee> list = fileController.getEmployeeList();
//        System.out.println(list.size());
        fileController.getMenu();
        MainAppMenu mainAppMenu = new MainAppMenu(fileController);
        mainAppMenu.startMainMenu();





    }
}
