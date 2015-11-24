package com.tracker.tracker.exception;

/**
 * This is the exception to deal with conditions where input is null
 */
public class NoInputException extends  Exception{
    public NoInputException(){
        super();
    }

    public String getMessage(){
        return "Input could not be null";
    }
}
