package com.tracker.tracker.exception;

import java.io.Serializable;

/**
 * This exception is to deal with condition where username and password is wrong
 */
public class InvalidAccountException extends Exception implements Serializable{
    public InvalidAccountException(){
        super();
    }
    public String getMessage(){
        return "Invalid username or password";
    }
}
