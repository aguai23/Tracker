package com.tracker.tracker.exception;

import java.io.Serializable;

/**
 * This is the exception to deal with conditions where input is null
 */
public class NoInputException extends  Exception implements Serializable{
    public NoInputException(){
        super();
    }

    public String getMessage(){
        return "Input could not be null";
    }
}
