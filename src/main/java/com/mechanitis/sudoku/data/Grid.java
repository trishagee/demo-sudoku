package com.mechanitis.sudoku.data;

/**
 * 0,0 0,1 0,2
 */
public class Grid {
    Row[] rows = new Row[9];
    Column[] columns = new Column[9];

    public Grid() {
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row();
        }
    }

    public Cell cellAt(int rowIndex, int columnIndex) {
        return rows[rowIndex].cellAt(columnIndex);
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
