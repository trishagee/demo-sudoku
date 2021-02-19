package com.mechanitis.sudoku.data;

public record ColumnIndex(int index) {
    public static ColumnIndex column(int index) {
        return new ColumnIndex(index);
    }
}
