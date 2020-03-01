package com.mechanitis.sudoku.data;

record GridCoords(int row, int column) implements Coords {
    public GridCoords {
        if (row < 0 || column < 0 || row > 8 || column > 8) {
            throw new InvalidValueException("Grid coordinates are 0 to 8 inclusive");
        }
    }

    static GridCoords gridCoords(int row, int column) {
        return new GridCoords(row, column);
    }

    BoxCoords toBoxCoords() {
        return new BoxCoords(row % 3, column % 3);
    }
}
