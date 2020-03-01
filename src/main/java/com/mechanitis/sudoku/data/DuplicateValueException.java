package com.mechanitis.sudoku.data;

import static java.lang.String.format;

class DuplicateValueException extends RuntimeException {
    @SuppressWarnings("FieldCanBeLocal")
    private final int value;

    DuplicateValueException(int value) {
        super(format("The value %s is already present", value));
        this.value = value;
    }
}
