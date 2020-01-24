package com.mechanitis.sudoku.data;

class Cell {
    private static final int EMPTY_VALUE = -1;
    private int value;

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

    public boolean isEmpty() {
        return value == EMPTY_VALUE;
    }

    void setValue(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (value < 1 || value > 9) {
            throw new InvalidValueException();
        }
    }

    static Cell copy(Cell cell) {
        if (cell.isEmpty()) {
            // tried using a singleton empty cell, but that makes the logic of edits from empty to a value really
            // complicated
            return new Cell();
        } else {
            return new Cell(cell.value);
        }
    }
}
