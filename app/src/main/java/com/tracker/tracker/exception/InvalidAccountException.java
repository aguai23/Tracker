package com.tracker.tracker.exception;

/**
 * This exception is to deal with condition where username and password is wrong
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException(){
        super();
    }
    public String getMessage(){
        return "Invalid username or password";
    }
}
