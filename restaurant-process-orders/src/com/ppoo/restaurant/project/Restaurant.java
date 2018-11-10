package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.ppoo.restaurant.project.persistance.FileController;

public class Restaurant {

    public static void main(String[] args) {
        System.out.println("Hello World!");


        FileController fileController = new FileController();

//        RestaurantEmployee restaurantEmployee = new Administrator(1L,"Gigel", EmployeeType.ADMINISTRATOR);
//        fileController.insertNewEmployee(restaurantEmployee);
//        System.out.println(restaurantEmployee.toString());

        System.out.println(fileController.checkEmployeeInfo(1L, "Gigel", EmployeeType.ADMINISTRATOR));

    }
}
