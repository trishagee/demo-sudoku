package com.mechanitis.sudoku.data;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
