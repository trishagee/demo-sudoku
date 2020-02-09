package com.mechanitis.sudoku.data;

record GridCoords(int rowIndex, int columnIndex) implements Coords {
    public GridCoords {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex > 8 || columnIndex > 8) {
            throw new InvalidValueException("Box coordinates are 0 to 8 inclusive");
        }
    }
}
