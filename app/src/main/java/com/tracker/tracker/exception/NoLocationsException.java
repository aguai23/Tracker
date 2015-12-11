package com.tracker.tracker.exception;

/**
 * Created by zhangyunzhe on 12/11/15.
 */
public class NoLocationsException extends Exception{
    public NoLocationsException(){
        super();
    }

    public String getMessage(){
        return "no loactions available for some contact";
    }
}
