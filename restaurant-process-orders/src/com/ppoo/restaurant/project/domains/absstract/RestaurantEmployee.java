package com.ppoo.restaurant.project.domains.absstract;

import com.ppoo.restaurant.project.domains.enums.EmployeeType;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;

import java.io.Serializable;

public abstract class RestaurantEmployee{

//    private static final long serialVersionUID = 2L;

    private Long employeeId;
    private String name;
    private EmployeeType employeeType;

    public RestaurantEmployee(Long employeeId, String name) throws InvalidInputException {
        this.employeeId = employeeId;
        this.name = name;
//        this.employeeType = employeeType;
        if(employeeId < 1L || name.length() < 2)
            throw new InvalidInputException("ERROR: Utilizatorul nu a fost inregistrat! Verificati ca datele pe care le introduceti sunt valide!");
    }

    public RestaurantEmployee(){

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
