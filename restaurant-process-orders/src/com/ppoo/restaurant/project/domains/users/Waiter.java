package com.ppoo.restaurant.project.domains.users;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.interfaces.WaiterTasks;

import java.io.Serializable;

public class Waiter extends RestaurantEmployee implements WaiterTasks, Serializable{

    static final long serialVersionUID = 1L;

    public Waiter(Long employeeId, String name) {
        super(employeeId, name);
        setEmployeeType(EmployeeType.WAITER);
    }

    @Override
    public void employeeTasks() {
        System.out.println("Get an order");
    }


}
