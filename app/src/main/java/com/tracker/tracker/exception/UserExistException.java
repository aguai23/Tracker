package com.tracker.tracker.exception;


public class UserExistException extends Exception {
    public UserExistException(){
        super();
    }

    public String getMessage(){
        return "The user has already exist";
    }
}
