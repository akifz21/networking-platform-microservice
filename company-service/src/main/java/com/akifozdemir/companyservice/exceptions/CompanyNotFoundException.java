package com.akifozdemir.companyservice.exceptions;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException(String message){
        super(message);
    }

    public CompanyNotFoundException(){
        super("Company Not Found !");
    }

}
