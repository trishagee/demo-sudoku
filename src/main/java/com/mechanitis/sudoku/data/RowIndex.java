package com.mechanitis.sudoku.data;

public record RowIndex(int index) {
    public static RowIndex row(int index) {
        return new RowIndex(index);
    }
}
