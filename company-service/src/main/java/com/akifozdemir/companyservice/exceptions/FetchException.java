package com.akifozdemir.companyservice.exceptions;

public class FetchException extends RuntimeException{
    public FetchException(String message){
        super(message);
    }
    public FetchException(){
        super("An error occurred during fetch");
    }
}
