package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.restaurantObjects.DrinkItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Menu;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.persistance.FileController;
import com.ppoo.restaurant.project.persistance.SystemInputController;

public class Restaurant {

    public static void main(String[] args) {
        System.out.println("Hello World!");


        FileController fileController = new FileController();

//        RestaurantEmployee restaurantEmployee = new Administrator(1L,"Gigel", EmployeeType.ADMINISTRATOR);
//        fileController.insertNewEmployee(restaurantEmployee);
//        System.out.println(restaurantEmployee.toString());

//        System.out.println(fileController.checkEmployeeInfo(1L, "Gigel", EmployeeType.ADMINISTRATOR));
//
//        fileController.getMenu();

//        DrinkItem drinkItem1 = new DrinkItem(fileController.getLastMenuItemId() + 1, "Prigat", 6.00, MenuItemType.DRINK, 0.00);
//        fileController.insertMenuItem(drinkItem1);
//        DrinkItem drinkItem = new DrinkItem(1L, "Coca-Cola", 5.00, MenuItemType.DRINK, 0.00);
//        fileController.insertMenuItem(drinkItem);




        fileController.getMenu();
        DrinkItem drinkItem1 = new DrinkItem(2L, "Fanta", 5.00, MenuItemType.DRINK, 0.00);
//        fileController.insertMenuItem(drinkItem1);
//        DrinkItem drinkItem2 = new DrinkItem(3L, "Prigat", 7.00, MenuItemType.DRINK, 0.00);
//        fileController.insertMenuItem(drinkItem2);

//        fileController.updateMenuItem(drinkItem1);

//    fileController.deleteMenuItem(2L);

//    fileController.rewriteMenuItemListToFile();


//        Menu menu = new Menu(fileController.getMenu());
//        System.out.println(menu.getMenuList().size());
//        menu.showMenuList();
//
//        MenuItem menuItem = new DrinkItem(1L, "Coca-Cola", 15.00, MenuItemType.DRINK, 0.00);
//        fileController.updateMenuItem(menuItem);

//        System.out.println(fileController.getTheLastMenuItemId());

        SystemInputController systemInputController = new SystemInputController();

        systemInputController.addNewMenuItem();
    }
}
