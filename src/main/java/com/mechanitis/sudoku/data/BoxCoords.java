package com.mechanitis.sudoku.data;

record BoxCoords(int rowIndex, int columnIndex) implements Coords {

    public BoxCoords {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex > 2 || columnIndex > 2) {
            throw new InvalidValueException("Box coordinates are 0 to 2 inclusive");
        }
    }
}
