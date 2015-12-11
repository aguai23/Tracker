package com.tracker.tracker.exception;


import java.io.Serializable;

public class TimeConflictException extends Exception implements Serializable{
    public TimeConflictException(){
        super();
    }
    public String getMessage(){
        return "time conflict";
    }
}
