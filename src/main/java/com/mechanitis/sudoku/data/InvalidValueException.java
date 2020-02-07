package com.mechanitis.sudoku.data;

public class InvalidValueException extends RuntimeException {

    //TODO - create a specific DuplicateValueException

    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
