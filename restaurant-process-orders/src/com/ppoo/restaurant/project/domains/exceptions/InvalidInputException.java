package com.ppoo.restaurant.project.domains.exceptions;

public class InvalidInputException extends IllegalArgumentException {

    private String myExceptionMessage;

    public InvalidInputException(String s) {
        super(s);
        this.myExceptionMessage = s;
    }

    public String toString(){
        return myExceptionMessage ;
    }
}
