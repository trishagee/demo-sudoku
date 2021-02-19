package com.mechanitis.sudoku.data;

/**
 * BoxCoords contains the co-ordinates to a value inside a 3x3 box.
 *
 * @param row    Values 0 to 2 inclusive allowed
 * @param column Values 0 to 2 inclusive allowed
 */
record BoxCoords(int row, int column) implements Coords {
    public BoxCoords {
        if (row < 0 || column < 0 || row > 2 || column > 2) {
            throw new InvalidValueException("Box coordinates are 0 to 2 inclusive");
        }
    }

    static BoxCoords fromIndices(RowIndex row, ColumnIndex column) {
        return new BoxCoords(row.index() % 3, column.index() % 3);
    }

}
