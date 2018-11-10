package com.ppoo.restaurant.project.domains.absstract;

import com.ppoo.restaurant.project.domains.enums.EmployeeType;

public abstract class RestaurantEmployee {

    private Long employeeId;
    private String name;
    private EmployeeType employeeType;

    public RestaurantEmployee(Long employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public abstract void employeeTasks();
}
