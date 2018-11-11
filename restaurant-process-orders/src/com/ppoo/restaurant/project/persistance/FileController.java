package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.restaurantObjects.DrinkItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.FoodItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.sun.glass.ui.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class FileController {

    File file;

    List<MenuItem> menuItems;
    List<Order> orderList;


    ObjectInputStream objectInputStream;
    FileInputStream fileInputStream;

    FileReader fileReader;
    BufferedReader bufferedReader;

    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    StringBuffer stringBufferOfData;

    RestaurantEmployee currentEmployee;

    public FileController(){
        this.menuItems = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.stringBufferOfData = new StringBuffer();
    }

    public File configureFile(String fileName){
        return new File(fileName);
    }

    public List<MenuItem> getMenuItemsList() {
        return menuItems;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<MenuItem> getMenu(){

        file = configureFile("meniu.txt");

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] splittedLine = null;

            while((line = bufferedReader.readLine()) != null){
                splittedLine = line.split("\t");
                // id name price menuItemType specific

                System.out.println(line);
                MenuItem menuItem;
                //DrinkItem(Long id, String name, Double price, MenuItemType menuItemType, Double alchoolDegrees) {
                if(splittedLine[3].compareTo(MenuItemType.DRINK.name()) == 0){
                    menuItem = new DrinkItem(Long.valueOf(splittedLine[0]), splittedLine[1], Double.valueOf(splittedLine[2]), MenuItemType.DRINK, Double.valueOf(splittedLine[4]));
                }
                else{
                    menuItem = new FoodItem(Long.valueOf(splittedLine[0]), splittedLine[1], Double.valueOf(splittedLine[2]), MenuItemType.FOOD, Double.valueOf(splittedLine[4]));
                }

                menuItems.add(menuItem);

            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return menuItems;
    }

    public void showMenu(){

        for(MenuItem menuItem: menuItems){
            System.out.println(menuItem.toString());
        }
    }

    //MENU_ITEM
    public void insertMenuItem(MenuItem menuItem){

        file = configureFile("meniu.txt");

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            // Header: id name price menuItemType specific
            if(menuItem.getClass() == DrinkItem.class){
                bufferedWriter.write(menuItem.getItemId() + "\t" + menuItem.getName() + "\t" + menuItem.getPrice().toString() + "\t" + menuItem.getMenuItemType().name() + "\t" + ((DrinkItem) menuItem).getAlchoolDegrees() + System.lineSeparator());
            }
            else
            if(menuItem.getClass() == FoodItem.class){
                bufferedWriter.write(menuItem.getItemId() + "\t" + menuItem.getName() + "\t" + menuItem.getPrice().toString() + "\t" + menuItem.getMenuItemType().name() + "\t" + ((FoodItem) menuItem).getCaloriesNumber() + System.lineSeparator());
            }

            menuItems.add(menuItem);

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getLastMenuItemId(){

        return menuItems.get(menuItems.size()-1).getItemId();
    }

    public MenuItem getMenuItemById(Long id){

        MenuItem menuItem = null;

        for(MenuItem mi: menuItems){
            if(mi.getItemId() == id)
                menuItem = mi;
        }

        return menuItem;
    }

    public void writeNewLineForUpdateMenuItem(String newLine, int noOfLineToModify){
        int lineNoFind = 0;
        String line;
        String[] splittedLine;
        Long currentId = 0L;

        System.out.println("intra in writeNewLineForUpdateMenuItem");
        System.out.println(noOfLineToModify);

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);


            while((line = bufferedReader.readLine()) != null){
                lineNoFind++;
                splittedLine = line.split("\t");

                if(lineNoFind == noOfLineToModify){
                    System.out.println("UPDATE");
//                    System.out.println(newLine);

                    fileWriter = new FileWriter(file, true);
                    bufferedWriter = new BufferedWriter(fileWriter);

                    currentId = Long.valueOf(splittedLine[0]);
                    bufferedWriter.write(newLine);

                    bufferedWriter.close();
                    fileWriter.close();
                }
            }

            bufferedReader.close();
            fileReader.close();

//            deleteMenuItem(currentId);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateMenuItem(MenuItem menuItem){

        file = configureFile("meniu.txt");

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] splittedLine;

            int lineNo = 0;

            int lineToModify = 0;
            String newLine = null;

            while((line = bufferedReader.readLine()) != null){
                splittedLine = line.split("\t");
                // id name price menuItemType specific

                lineNo++;

                if(Long.valueOf(splittedLine[0]) == menuItem.getItemId()){
                    newLine =  line.replace(splittedLine[2], menuItem.getPrice().toString());

                    lineToModify = lineNo;
                }

            }

            bufferedReader.close();
            fileReader.close();

            writeNewLineForUpdateMenuItem(newLine, lineToModify);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteMenuItem(Long id){

        MenuItem menuItemToRemove = null;

        for(MenuItem menuItem: menuItems){
            if(menuItem.getItemId() == id){
                menuItemToRemove = menuItem;
                break;
            }
        }

        menuItems.remove(menuItemToRemove);

        rewriteMenuItemListToFile();

    }

    public void rewriteMenuItemListToFile(){

        file = configureFile("meniu.txt");

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            for(MenuItem menuItem: menuItems){
                if(menuItem.getClass() == DrinkItem.class){
                    bufferedWriter.write(menuItem.getItemId() + "\t" + menuItem.getName() + "\t" + menuItem.getPrice().toString() + "\t" + menuItem.getMenuItemType().name() + "\t" + ((DrinkItem) menuItem).getAlchoolDegrees() + System.lineSeparator());
                }
                else
                if(menuItem.getClass() == FoodItem.class){
                    bufferedWriter.write(menuItem.getItemId() + "\t" + menuItem.getName() + "\t" + menuItem.getPrice().toString() + "\t" + menuItem.getMenuItemType().name() + "\t" + ((FoodItem) menuItem).getCaloriesNumber() + System.lineSeparator());
                }
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //ORDER
    public void getAllOrders(){

        file = configureFile("comenzi-restaurant.txt");


    }

    public void writeCurrentOrder(Order order){

        file = configureFile("comanda-" + order.getOrderId() + ".txt");
    }

    public Long getLastOrderId(){

        return orderList.get(orderList.size()-1).getOrderId();
    }

    public void getOrdersByDate(String date){

        file = configureFile("comenzi-" + date + ".txt");

//        try {
//            fileReader = new FileReader(file);
//            bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//
//            while((line = bufferedReader.readLine()) != null){
//
//            }
//
//            bufferedReader.close();
//            fileReader.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void insertOrder(Order order){

        file = configureFile("comenzi-restaurant.txt");

    }

    public void updateOrder(Order order){

    }

    public boolean checkEmployeeInfo(Long employeeId, String name, EmployeeType employeeType){

        file = configureFile("utilizatori.dat");

        boolean isActive = false;

        if(employeeType.name().compareTo(EmployeeType.ADMINISTRATOR.name()) == 0){
            currentEmployee = new Administrator(employeeId,name);
        }
        else{
            currentEmployee = new Waiter(employeeId,name);
        }

        System.out.println(currentEmployee.toString());

        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            RestaurantEmployee obj;

            while(objectInputStream.available() == 0){
                obj = (RestaurantEmployee) objectInputStream.readObject();
                System.out.println(obj.toString());
                System.out.println(obj.getEmployeeId() + " " + currentEmployee.getEmployeeId());
//                if(obj.getName().compareTo(currentEmployee.getName()) == 0)
//                    isActive = true;
            }

            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
            System.out.println("Final de fisier");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  isActive;
    }

    public void insertNewEmployee(RestaurantEmployee employee){

        file = configureFile("utilizatori.dat");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(employee);

            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
