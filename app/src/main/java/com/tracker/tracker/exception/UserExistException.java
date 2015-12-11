package com.tracker.tracker.exception;


import java.io.Serializable;

public class UserExistException extends Exception implements Serializable{
    public UserExistException(){
        super();
    }

    public String getMessage(){
        return "The user has already exist";
    }
}
