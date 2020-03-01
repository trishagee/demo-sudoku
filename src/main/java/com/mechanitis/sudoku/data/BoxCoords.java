package com.mechanitis.sudoku.data;

record BoxCoords(int row, int column) implements Coords {
    public BoxCoords {
        if (row < 0 || column < 0 || row > 2 || column > 2) {
            throw new InvalidValueException("Box coordinates are 0 to 2 inclusive");
        }
    }

    static BoxCoords boxCoords(int row, int column) {
        return new BoxCoords(row, column);
    }

}
