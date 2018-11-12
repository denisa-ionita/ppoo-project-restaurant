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
import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.*;
import java.text.ParseException;
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

    //GETTERS + SETTERS LISTS
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

    //MENU_ITEM
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

    public MenuItem getMenuItemByName(String name){
        MenuItem menuItem = null;

        for(MenuItem mi: menuItems){
            if(mi.getName().compareTo(name) == 0)
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

        System.out.println("MenuItem sters cu succes!");

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

        try{

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while((line = bufferedReader.readLine()) != null){

                getOrderDetailsFromFile(line);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getOrderDetailsFromFile(String fileName){

        file = configureFile(fileName + ".txt");

        String[] fileNameSplit = fileName.split("-");

        Order order;

        FileReader currentFileReader;
        BufferedReader currentBufferedReader;

        try {
            currentFileReader = new FileReader(file);
            currentBufferedReader = new BufferedReader(currentFileReader);

            String line;
            String[] lineSplitted;
            int lineNumber = 1;
            List<OrderItem> orderItemList = new ArrayList<>();
            RestaurantEmployee waiter = null;
            Date date = null;

            while((line = currentBufferedReader.readLine()) != null){

                if(lineNumber == 3) {
                    // ospatarul
                    lineSplitted = line.split(": ");
                    waiter = getEmployeeByName(lineSplitted[1]);
                } else if(lineNumber!=2 && !line.contains("-----") && !line.contains("TOTAL") && lineNumber !=1){
                    lineSplitted = line.split(" ");
                    // 0 - nume, 1 - x, 2 - number, 3 - =, 4 - total
                    MenuItem menuItem = getMenuItemByName(lineSplitted[0]);

                    OrderItem orderItem = new OrderItem(menuItem, Integer.valueOf(lineSplitted[2]));
                    orderItemList.add(orderItem);
                } else if(lineNumber == 2){
                    lineSplitted = line.split(": ");
                    date = Constants.simpleDateFormat.parse(lineSplitted[1]);
                }

                lineNumber++;
            }

            order = new Order(Long.valueOf(fileNameSplit[1]), orderItemList, (Waiter) waiter);
//            System.out.println(order.toString());
            order.setOrderDate(date);
            orderList.add(order);

            currentBufferedReader.close();
            currentFileReader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeToOrderFile(String fileName){

        file = configureFile("comenzi-restaurant.txt");

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(fileName + System.lineSeparator());

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getLastOrderId(){

        return (orderList.size()>0)?orderList.get(orderList.size()-1).getOrderId():0L;
    }

    public void insertOrder(Order order){
        // scrie cate un fisier separat pt fiecare comanda

        file = configureFile("comanda-" + order.getOrderId() + ".txt");

        orderList.add(order);

        Double totalPrice = 0d;

        Date currentDate = new Date();

        try{

            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            //orderId waiterId menuItem x cantity; menuItem x cantity....
            bufferedWriter.write("Comanda numarul: " + order.getOrderId() + System.lineSeparator());
            bufferedWriter.write("Data: " + Constants.simpleDateFormat.format(currentDate));
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

    public void printOrder(Long orderId){

        file = configureFile("comanda-" + orderId + ".txt");

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrders(){
        for(Order order: orderList){
            printOrder(order.getOrderId());
        }
    }

    public Order getOrderById(Long id){
        int indexOfCurrentEmployee = -1;

        for(Order order: orderList){
            if(order.getOrderId() == id)
                indexOfCurrentEmployee = orderList.indexOf(order);
        }

        return orderList.get(indexOfCurrentEmployee);
    }

    public void printStatisticsOrdersByDate(List<Order> orderList, String fileName){

        file = configureFile(fileName + ".txt");

        Double total, totalOrderValueOfDay = 0d;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            if(fileName.compareTo("Statistici-valori-totale") == 0){
                bufferedWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
                bufferedWriter.write(Constants.simpleDateFormat.format(orderList.get(0).getOrderDate()) + System.lineSeparator());
                bufferedWriter.write("Id_Comanda    Ospatar Valoare_totala" + System.lineSeparator());
            }
            else{
                bufferedWriter.write("Id_Comanda    Ospatar Valoare_totala" + System.lineSeparator());
            }

            List<Order> orderListToGetData = getOrderList();

            for(Order order:orderListToGetData){

                total = 0d;

                for(OrderItem orderItem:order.getOrderItemsList()){
                    total += orderItem.getItem().getPrice()*orderItem.getItemCantity();
                }

                totalOrderValueOfDay += total;
                bufferedWriter.write(order.getOrderId() + "\t" + order.getWaiter().getName() + "\t" + total + System.lineSeparator());
            }

            bufferedWriter.write("--------------------------------------------------------------" + System.lineSeparator());
            bufferedWriter.write("TOTAL: " + totalOrderValueOfDay + System.lineSeparator());

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EMPLOYEES
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

            bufferedWriter.write(employee.getEmployeeId() + "\t" + employee.getEmployeeType().name().toUpperCase() + "\t" + employee.getName() + System.lineSeparator());

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

    public RestaurantEmployee getEmployeeByName(String name){
        int indexOfCurrentEmployee = -1;

        for(RestaurantEmployee restaurantEmployee: employeeList){
            if(restaurantEmployee.getName().compareTo(name) == 0)
                indexOfCurrentEmployee = employeeList.indexOf(restaurantEmployee);
        }

        return employeeList.get(indexOfCurrentEmployee);
    }


    // PERSISTANCE DATA => EXIT
    public void exitAppWithFileUpdate(){
        rewriteMenuItemListToFile();
    }

}
