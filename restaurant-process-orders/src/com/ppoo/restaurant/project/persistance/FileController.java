package com.ppoo.restaurant.project.persistance;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.restaurantObjects.Order;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;

import java.io.*;
import java.util.List;

public class FileController {

    File file;

    List<MenuItem> menuItems;


    ObjectInputStream objectInputStream;
    FileInputStream fileInputStream;

    RestaurantEmployee currentEmployee;

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

    public boolean checkEmployeeInfo(Long employeeId, String name, EmployeeType employeeType){

        file = configureFile("utilizatori.dat");

        boolean isActive = false;

        if(employeeType.name().compareTo(EmployeeType.ADMINISTRATOR.name()) == 0){
            currentEmployee = new Administrator(employeeId,name, employeeType);
        }
        else{
            currentEmployee = new Waiter(employeeId,name, employeeType);
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
