package com.akifozdemir.companyservice.exceptions;

import org.hibernate.FetchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CompanyNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(CompanyNotFoundException companyNotFoundException){
        return new ErrorResponse(companyNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {FetchNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(FetchNotFoundException fetchException){
        return new ErrorResponse(fetchException.getMessage());
    }
}
