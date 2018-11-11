package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;
import com.ppoo.restaurant.project.domains.restaurantObjects.DrinkItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.FoodItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;
import com.ppoo.restaurant.project.domains.restaurantObjects.OrderItem;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemInputController {

    Scanner scanner;

    public SystemInputController(){
        scanner = new Scanner(System.in);
    }

    public Order addNewOrder(Waiter waiter){

        Order order = null;
        FileController fileController = new FileController();

        Integer orderItemNo = null;
        String viewItemsReposonse;
        Long menuItemId;
        Integer menuItemCount;
        List<OrderItem> orderItemList = new ArrayList<>();

        System.out.println("Comanda noua: ");

        System.out.print("Numar de obiecte din meniu pe care le veti introduce: ");
        orderItemNo = scanner.nextInt();

        System.out.print("Doriti vizualizarea codurilor obiectelor? (D/N)");
        viewItemsReposonse = scanner.next();
        try{
            if(viewItemsReposonse.compareTo("D") == 0){
                fileController.showMenu();
            } else
            if(viewItemsReposonse.compareTo("N") == 0){
            } else
                throw new InvalidInputException("Input invalid!");
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }

        OrderItem orderItem = new OrderItem();

        while(orderItemNo > 0){

            System.out.println();

            System.out.print("Cod produs " + (orderItemList.size() + 1) + ": ");
            menuItemId = scanner.nextLong();

            System.out.print("Cantitate produs " + (orderItemList.size() + 1) + ": ");
            menuItemCount = scanner.nextInt();

            orderItem = new OrderItem(fileController.getMenuItemById(menuItemId), menuItemCount);

            orderItemList.add(orderItem);

            orderItemNo--;
        }

        order.setOrderItemsList(orderItemList);


        return order;
    }

    public MenuItem addNewMenuItem() throws InvalidInputException{

        MenuItem menuItem  = null;
        String menuItemType;
        String name;
        Double price;
        Double alcoolDegrees;
        Double calories;
        MenuItemType menuItemType1 = null;

        System.out.println("Formular introducere produs nou in meniu");

        System.out.print("Mancare sau Bautura (M/B): ");
        menuItemType = scanner.next();
        try{
            if(menuItemType.compareTo("M") == 0){
                menuItemType1 = MenuItemType.FOOD;
            } else
                if(menuItemType.compareTo("B") == 0){
                menuItemType1 = MenuItemType.DRINK;
            } else
                throw new InvalidInputException("Input invalid!");
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }

        System.out.print("Denumire: ");
        name = scanner.next();

        System.out.print("Pret: ");
        price = scanner.nextDouble();

        if(menuItemType1 == MenuItemType.DRINK){
            System.out.print("Grade alcool: ");
            alcoolDegrees = scanner.nextDouble();
            menuItem = new DrinkItem(1L, name, price, menuItemType1, alcoolDegrees);
        }
        else if(menuItemType1 == MenuItemType.FOOD){
            System.out.print("Numar calorii: ");
            calories = scanner.nextDouble();
            menuItem = new FoodItem(1L, name, price, menuItemType1, calories);
        }

        return menuItem;
    }
}
