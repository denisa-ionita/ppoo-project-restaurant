package com.ppoo.restaurant.project.domains.users;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.interfaces.AdministratorTasks;

import java.io.Serializable;

public class Administrator extends RestaurantEmployee implements AdministratorTasks, Serializable{

    static final long serialVersionUID = 2L;

    public Administrator(Long employeeId, String name) {
        super(employeeId, name);
        setEmployeeType(EmployeeType.ADMINISTRATOR);
    }

    @Override
    public void employeeTasks() {
        System.out.println("Add stock");
    }
}
