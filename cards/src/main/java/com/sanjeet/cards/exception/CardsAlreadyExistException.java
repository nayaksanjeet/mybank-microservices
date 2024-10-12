package com.sanjeet.cards.exception;

public class CardsAlreadyExistException extends RuntimeException{

    public CardsAlreadyExistException(String message) {
        super(message);
    }
}
