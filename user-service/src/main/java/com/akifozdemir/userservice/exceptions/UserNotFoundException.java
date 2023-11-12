package com.akifozdemir.userservice.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){
        super("User Not Found");
    }
}
