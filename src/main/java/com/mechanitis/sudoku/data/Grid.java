package com.mechanitis.sudoku.data;

/**
 * 0,0 0,1 0,2
 */
public class Grid {
    private static final int SIZE = 9;
    Row[] rows = new Row[SIZE];
    Column[] columns = new Column[SIZE];

    public Grid() {
        for (int i = 0; i < SIZE; i++) {
            rows[i] = new Row();
            columns[i] = new Column();
        }
    }

    public Cell cellAt(int rowIndex, int columnIndex) {
        return rows[rowIndex].cellAt(columnIndex);
    }

    public Row rowAt(int rowIndex) {
        return rows[rowIndex];
    }

    public Column columnAt(int columnIndex) {
        return columns[columnIndex];
    }

    public Mutator changeCell() {
        return new Mutator();
    }

    class Mutator {
        private int rowIndex;
        private int columnIndex;

        Mutator onRow(int rowIndex) {
            this.rowIndex = rowIndex;
            return this;
        }

        Mutator atPosition(int columnIndex) {
            this.columnIndex = columnIndex;
            return this;
        }

        void toValue(int value) {
            rows[rowIndex].changeCell().atPosition(columnIndex).toValue(value);
        }
    }
}
