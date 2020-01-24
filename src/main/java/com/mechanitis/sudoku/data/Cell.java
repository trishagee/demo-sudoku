package com.mechanitis.sudoku.data;

class Cell {
    private final int value;
    private final int position = 0;
    private final Row row = null;
    private final Column column = null;

    Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Row getRow() {
        return null;
    }
}
