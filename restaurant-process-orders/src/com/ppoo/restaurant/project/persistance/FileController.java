package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.constants.Constants;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.restaurantObjects.DrinkItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.FoodItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;
import com.ppoo.restaurant.project.domains.restaurantObjects.OrderItem;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.sun.glass.ui.Menu;

import java.io.*;
import java.util.*;

public class FileController {

    File file;

    List<MenuItem> menuItems;
    List<Order> orderList;
    List<RestaurantEmployee> employeeList;


    ObjectInputStream objectInputStream;
    FileInputStream fileInputStream;

    FileReader fileReader;
    BufferedReader bufferedReader;

    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    StringBuffer stringBufferOfData;

    RestaurantEmployee currentEmployee;

    SystemInputController systemInputController;

    public FileController(){
        this.menuItems = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
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

    public List<RestaurantEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void getMenu(){

        file = configureFile("meniu.txt");

        List<MenuItem> myCurrentMenuItemsList = new ArrayList<>();

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] splittedLine = null;

            while((line = bufferedReader.readLine()) != null){
                splittedLine = line.split("\t");
                // id name price menuItemType specific

//                System.out.println(line);
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

        Date currentDate = new Date();

        file = configureFile("comenzi-" + Constants.simpleDateFormat.format(currentDate) + ".txt");

        try{

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] splittedLine;
            String[] orderItems;
            List<OrderItem> orderItemList = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null){

                splittedLine = line.split("\t");


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Long getLastOrderId(){

        return (orderList.size()>0)?orderList.get(orderList.size()-1).getOrderId():0L;
    }

    public void insertOrder(Order order){
        // scrie cate un fisier separat pt fiecare comanda

//        Order order = systemInputController.addNewOrder(waiter);

        file = configureFile("comanda-" + order.getOrderId() + ".txt");

        orderList.add(order);

        Double totalPrice = 0d;

        try{

            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            //orderId waiterId menuItem x cantity; menuItem x cantity....
            bufferedWriter.write("Comanda numarul: " + order.getOrderId() + System.lineSeparator());
            bufferedWriter.write("Ospatar: " + order.getWaiter().getName().toUpperCase() + System.lineSeparator());
            bufferedWriter.write("--------------------------------------------------------------" + System.lineSeparator());

            for(OrderItem orderItem:order.getOrderItemsList()){
                bufferedWriter.write(orderItem.getItem().getName() + " x " + orderItem.getItemCantity() + " = " + orderItem.getItem().getPrice()*orderItem.getItemCantity() + System.lineSeparator());
                totalPrice += orderItem.getItem().getPrice()*orderItem.getItemCantity();
            }

            bufferedWriter.write("--------------------------------------------------------------" + System.lineSeparator());

            bufferedWriter.write("TOTAL: " + totalPrice);

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateOrder(Order order){

        file = configureFile("comanda-" + order.getOrderId() + ".txt");

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmployeeInfo(Long employeeId, String name, EmployeeType employeeType){

        boolean isActive = false;

        for(RestaurantEmployee employee: employeeList){
            if(employee.getName().compareTo(name) == 0 && employee.getEmployeeId() == employeeId && employee.getEmployeeType() == employeeType)
                isActive = true;
        }

        return  isActive;
    }

    public void getEmployeeListFromFile(){
        file = configureFile("utilizatori.txt");

        try {

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] splittedLine;

            while((line = bufferedReader.readLine()) != null){
                splittedLine = line.split("\t");

                if(splittedLine[1].compareTo(EmployeeType.WAITER.name()) == 0){
                    employeeList.add(new Waiter(Long.valueOf(splittedLine[0]), splittedLine[2]));
                } else
                    if(splittedLine[1].compareTo(EmployeeType.ADMINISTRATOR.name()) == 0){
                    employeeList.add(new Administrator(Long.valueOf(splittedLine[0]), splittedLine[2]));
                    }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
            System.out.println("Final de fisier");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(RestaurantEmployee restaurantEmployee: employeeList){
//            System.out.println(restaurantEmployee.toString());
//        }
    }

    public Long getLastEmployeeId(){
        return employeeList.size()>0?(employeeList.get(employeeList.size()-1).getEmployeeId()):1L;
    }

    public void insertNewEmployee(RestaurantEmployee employee){

        file = configureFile("utilizatori.txt");

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(employee.getEmployeeId() + "\t" + employee.getEmployeeType().name() + "\t" + employee.getName() + System.lineSeparator());

            bufferedWriter.close();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RestaurantEmployee getEmployeeById(Long id){

        int indexOfCurrentEmployee = -1;

        for(RestaurantEmployee restaurantEmployee: employeeList){
            if(restaurantEmployee.getEmployeeId() == id)
                indexOfCurrentEmployee = employeeList.indexOf(restaurantEmployee);
        }

        return employeeList.get(indexOfCurrentEmployee);
    }

}
