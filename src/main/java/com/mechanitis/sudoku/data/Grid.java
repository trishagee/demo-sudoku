package com.mechanitis.sudoku.data;

/**
 * 0,0 0,1 0,2
 */
public class Grid {
    private static final int SIZE = 9;
    // the data is effectively duplicated in both, but it makes it easy to access
    Row[] rows = new Row[SIZE];
    Column[] columns = new Column[SIZE];
    private final Box[] boxes = new Box[SIZE];

    public Grid() {
        for (int i = 0; i < SIZE; i++) {
            rows[i] = new Row();
            columns[i] = new Column();
            boxes[i] = new Box();
        }
    }

    public Row rowAt(int rowIndex) {
        return rows[rowIndex];
    }

    public Column columnAt(int columnIndex) {
        return columns[columnIndex];
    }

    public Box boxAt(Position position) {
        return boxes[position.getIndex()];
    }

    public Mutator changeCell() {
        return new Mutator();
    }

    public Cell cellAt(GridCoords gridCoords) {
        return rows[gridCoords.rowIndex()].cellAt(gridCoords.columnIndex());
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
            columns[columnIndex].changeCell().atPosition(rowIndex).toValue(value);
            // this is not super pretty, there must be a neater way
            GridCoords gridCoords = new GridCoords(rowIndex, columnIndex);
            boxes[Position.indexFromCoords(gridCoords)].changeCell().atPosition(gridCoords).toValue(value);
        }
    }

}
