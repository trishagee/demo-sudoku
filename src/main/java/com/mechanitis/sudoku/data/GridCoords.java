package com.mechanitis.sudoku.data;

import java.util.Objects;

public final class GridCoords implements Coords {
    private final RowIndex row;

    private final ColumnIndex column;

    public GridCoords(int row, int column) {
        if (row < 0 || column < 0 || row > 8 || column > 8) {
            throw new InvalidValueException("Grid coordinates are 0 to 8 inclusive");
        }
        this.row = RowIndex.row(row);
        this.column = ColumnIndex.column(column);
    }

    BoxCoords toBoxCoords() {
        return new BoxCoords(row.index() % 3, column.index() % 3);
    }

    public int row() {
        return row.index();
    }

    public int column() {
        return column.index();
    }

    public RowIndex rowIndex() {
        return row;
    }

    public ColumnIndex columnIndex() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GridCoords) obj;
        return this.row == that.row &&
                this.column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "GridCoords[" +
                "row=" + row.index() + ", " +
                "column=" + column.index() + ']';
    }

}
