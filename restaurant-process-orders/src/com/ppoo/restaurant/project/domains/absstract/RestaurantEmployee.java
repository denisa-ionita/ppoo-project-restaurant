package com.ppoo.restaurant.project.domains.absstract;

import com.ppoo.restaurant.project.domains.enums.EmployeeType;

import java.io.Serializable;

public abstract class RestaurantEmployee implements  Serializable{

    private static final long serialVersionUID = 2L;

    private Long employeeId;
    private String name;
    private EmployeeType employeeType;

    public RestaurantEmployee(Long employeeId, String name, EmployeeType employeeType) {
        this.employeeId = employeeId;
        this.name = name;
        this.employeeType = employeeType;
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

    @Override
    public String toString() {
        return "RestaurantEmployee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", employeeType=" + employeeType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        RestaurantEmployee restaurantEmployee = (RestaurantEmployee) obj;
        return this.getEmployeeId() == restaurantEmployee.getEmployeeId() &&
                this.getEmployeeType().name().compareTo(restaurantEmployee.getEmployeeType().name()) == 0 &&
                this.getName().compareTo(restaurantEmployee.getName()) == 0;
    }
}
