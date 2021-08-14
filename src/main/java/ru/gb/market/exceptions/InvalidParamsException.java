package ru.gb.market.exceptions;

public class InvalidParamsException extends RuntimeException{

    public InvalidParamsException(String message) {
        super(message);
    }
}
