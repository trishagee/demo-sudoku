package com.mechanitis.sudoku.data;

class Cell {
    private static final int EMPTY_VALUE = -1;

    private final int value;
    private final int position = 0;
    private final Row row = null;
    private final Column column = null;

    Cell(int value) {
        validateValue(value);
        this.value = value;
    }

    Cell() {
        this.value = EMPTY_VALUE;
    }

    public int getValue() {
        return value;
    }

    public Row getRow() {
        return null;
    }

    public boolean isEmpty() {
        return value == EMPTY_VALUE;
    }

    private void validateValue(int value) {
        if (value < 1 || value > 9) {
            throw new InvalidValueException();
        }
    }
}
