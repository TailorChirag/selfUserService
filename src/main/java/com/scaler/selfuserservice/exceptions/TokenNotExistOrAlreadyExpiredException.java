package com.scaler.selfuserservice.exceptions;

public class TokenNotExistOrAlreadyExpiredException extends Exception{

    public TokenNotExistOrAlreadyExpiredException(String message){
        super(message);
    }

}
