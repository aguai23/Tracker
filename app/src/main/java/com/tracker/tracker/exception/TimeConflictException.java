package com.tracker.tracker.exception;


public class TimeConflictException extends Exception{
    public TimeConflictException(){
        super();
    }
    public String getMessage(){
        return "time conflict";
    }
}
