package com.mechanitis.sudoku.data;

public class InvalidValueException extends RuntimeException {
    InvalidValueException(String message) {
        super(message);
    }
}
