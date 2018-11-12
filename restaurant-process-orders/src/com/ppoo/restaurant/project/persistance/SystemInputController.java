package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.constants.Constants;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.enums.MenuItemType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;
import com.ppoo.restaurant.project.domains.restaurantObjects.DrinkItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.FoodItem;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;
import com.ppoo.restaurant.project.domains.restaurantObjects.OrderItem;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;

import java.text.ParseException;
import java.util.*;

public class SystemInputController {


    FileController fileController;
    Scanner scanner;

    Waiter waiter;

    Map<Date, List<Order>> orderMap;

    public SystemInputController(FileController fileController){
        scanner = new Scanner(System.in);
        this.fileController = fileController;
        this.orderMap = new HashMap<>();
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public Map<Date, List<Order>> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Date, List<Order>> orderMap) {
        this.orderMap = orderMap;
    }

    public List<OrderItem> insertOrderItemsFromSystemInput(){

        Integer orderItemNo;
        List<OrderItem> orderItemList = new ArrayList<>();
        Long menuItemId;
        Integer menuItemCount;

        System.out.print("Numar de obiecte din meniu pe care le veti introduce: ");
        orderItemNo = scanner.nextInt();

        OrderItem orderItem;

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

        return orderItemList;
    }

    public Order addNewOrder(Waiter waiter){

        Order order = null;

        Integer orderItemNo = null;
        String viewItemsReposonse;
        Long menuItemId;
        Integer menuItemCount;
        List<OrderItem> orderItemList = new ArrayList<>();

        System.out.println("Comanda noua: ");

        System.out.print("Doriti vizualizarea codurilor obiectelor? (D/N): ");
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

        orderItemList = insertOrderItemsFromSystemInput();

        order = new Order(fileController.getLastOrderId() + 1, orderItemList, waiter);

        fileController.insertOrder(order);

        fileController.writeToOrderFile("comanda-" + order.getOrderId());
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
            menuItem = new DrinkItem(fileController.getLastMenuItemId() + 1, name, price, menuItemType1, alcoolDegrees);
        }
        else if(menuItemType1 == MenuItemType.FOOD){
            System.out.print("Numar calorii: ");
            calories = scanner.nextDouble();
            menuItem = new FoodItem(fileController.getLastMenuItemId() + 1, name, price, menuItemType1, calories);
        }

        fileController.insertMenuItem(menuItem);

        return menuItem;
    }

    public boolean verifyWaiterInfo(){

        System.out.print("Cod angajat: ");
        Long employeeId;
        employeeId = scanner.nextLong();

        System.out.print("Nume: ");
        String name;
        name = scanner.next();

        waiter = new Waiter(employeeId, name);

        return fileController.checkEmployeeInfo(employeeId, name, EmployeeType.WAITER);
    }

    public boolean verifyAdminInfo(){

        System.out.print("Cod angajat: ");
        Long employeeId;
        employeeId = scanner.nextLong();

        System.out.print("Nume: ");
        String name;
        name = scanner.next();

        return fileController.checkEmployeeInfo(employeeId, name, EmployeeType.ADMINISTRATOR);
    }

    public void deleteMenuItem(){

        Long menuItemId;

        System.out.print("Introduceti id-ul item-ului pe care doriti sa il stergeti: ");
        menuItemId = scanner.nextLong();

        fileController.deleteMenuItem(menuItemId);
    }

    public void printOrder(){

        Long orderId;

        System.out.print("Introduceti id-ul comenzii: ");
        orderId = scanner.nextLong();

        fileController.printOrder(orderId);
    }

    public void modifyMenuItem(){

        System.out.println("Alegeti id-ul MenuItem-ului: ");
        Long id;
        id = scanner.nextLong();

        MenuItem menuItem = fileController.getMenuItemById(id);

        System.out.print("Pret nou: ");

        Double price;
        price = scanner.nextDouble();
        menuItem.setPrice(price);

        fileController.updateMenuItem(menuItem);
        fileController.rewriteMenuItemListToFile();

    }

    public void editOrder(){

        fileController.showOrders();

        Long id;
        System.out.print("Introduceti id-ul comenzii pe care doriti sa o modificati: ");
        id = scanner.nextLong();

        List<Order> orderList = fileController.getOrderList();

        Order order = fileController.getOrderById(id);

        List<OrderItem> newOrderItemList = insertOrderItemsFromSystemInput();

        List<OrderItem> currentOrderItemList = order.getOrderItemsList();

        for(OrderItem orderItem: newOrderItemList){
            currentOrderItemList.add(orderItem);
        }

        order.setOrderItemsList(currentOrderItemList);
//        order = new Order(fileController.getLastOrderId() + 1, newOrderItemList, waiter);

        fileController.insertOrder(order);

    }

    public void insertNewRestaurantEmployee() throws InvalidInputException{

        String employeeTypeString;
        EmployeeType employeeType = null;
        System.out.print("Tipul angajatului (A - Admin/O - Ospatar): ");
        employeeTypeString = scanner.next();

        try{
            employeeType = (employeeTypeString.compareTo("A") == 0)?EmployeeType.ADMINISTRATOR:
                    ((employeeTypeString.compareTo("O") == 0)?EmployeeType.WAITER:null);
        } catch (InvalidInputException e){
            System.out.println(e.toString());
        }

        String employeeName;
        System.out.print("Numele angajatului: ");
        employeeName = scanner.next();

        RestaurantEmployee newEmployee;
        newEmployee = (employeeType == EmployeeType.WAITER)?new Waiter(fileController.getLastEmployeeId() + 1,employeeName):
                new Administrator(fileController.getLastEmployeeId() + 1, employeeName);

        fileController.insertNewEmployee(newEmployee);

    }

    public void popularMenuItems(){

        List<OrderItem> allOrderItems = new ArrayList<>();

        List<Order> allOrders = fileController.getOrderList();

        for(Order order:allOrders){
            allOrderItems.addAll(order.getOrderItemsList());
        }

        System.out.println("allOrders size: " + allOrders.size());
        System.out.println("allOrderItems size: " + allOrderItems.size());
        Set<OrderItem> orderItemsSet = new HashSet<>();

        for(OrderItem orderItem:allOrderItems){

            System.out.println("list");
            System.out.println(orderItem.toString());
            if(orderItemsSet.size() > 0)
            for(OrderItem setOrderItem:orderItemsSet){
                System.out.println("set");
                System.out.println(setOrderItem.toString());
                if(setOrderItem.getItem().getName().compareTo(orderItem.getItem().getName()) == 0){
                    orderItem.setItemCantity(orderItem.getItemCantity() + setOrderItem.getItemCantity());
                }
            }
            orderItemsSet.add(orderItem);
        }

        System.out.println(orderItemsSet.size());
    }

    public void putValuesIntoHashMap(){

        List<Order> currentOrderList = new ArrayList<>();
        List<Order> orderListExtractedFromFile = fileController.getOrderList();

        for(Order order: orderListExtractedFromFile){
            if(orderMap.get(Constants.simpleDateFormat.format(order.getOrderDate())) != null){
                currentOrderList = orderMap.get(Constants.simpleDateFormat.format(order.getOrderDate()));
            } else {
                currentOrderList = new ArrayList<>();
            }
            currentOrderList.add(order);
            orderMap.put(order.getOrderDate(), currentOrderList);
        }

//        System.out.println("size hashMap: " + orderMap.size());


    }

    public void valueOfAllOrders(){

        putValuesIntoHashMap();

        List<Order> currentOrderList = new ArrayList<>();

        for(Map.Entry<Date, List<Order>> entry:orderMap.entrySet()){
            currentOrderList = entry.getValue();
//            System.out.println(entry.getKey());
//            System.out.println("List size of map: " + currentOrderList.size());
            fileController.printStatisticsOrdersByDate(currentOrderList, "Statistici-valori-totale");
        }
    }

    public void valueOfOrdersByDate(String dateString){

        putValuesIntoHashMap();
        List<Order> currentOrderList;
        System.out.println(orderMap.size());

        try {
            currentOrderList = orderMap.get(Constants.simpleDateFormat.parse(dateString));

            fileController.printStatisticsOrdersByDate(currentOrderList, "Statistici-comenzi-" + dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
