package com.ppoo.restaurant.project.domains.users;

import com.ppoo.restaurant.project.domains.absstract.RestaurantEmployee;
import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.interfaces.AdministratorTasks;

public class Administrator extends RestaurantEmployee implements AdministratorTasks{

    public Administrator(Long employeeId, String name) {
        super(employeeId, name);
        this.setEmployeeType(EmployeeType.ADMINISTRATOR);
    }

    @Override
    public void employeeTasks() {
        System.out.println("Add stock");
    }
}
