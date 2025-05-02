package com.devalvesg.desafio_itau.infrastructure.api.exceptions;

public class BusinessException extends Exception {

    public BusinessException(String error){
        super(error);
    }
}
