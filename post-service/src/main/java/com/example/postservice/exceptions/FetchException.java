package com.example.postservice.exceptions;

public class FetchException extends RuntimeException{
    public FetchException(String message){
        super(message);
    }
}