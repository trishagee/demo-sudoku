package com.mechanitis.sudoku.data;

public class Grid {
    private static final int SIZE = 9;
    // the data is effectively duplicated in both, but it makes it easy to access
    private final Row[] rows = new Row[SIZE];
    private final Column[] columns = new Column[SIZE];
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
        return rows[gridCoords.row()].cellAt(gridCoords.column());
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
            rows[rowIndex].changeCell(columnIndex).toValue(value);
            columns[columnIndex].changeCell(rowIndex).toValue(value);
            // TODO: this is not super pretty, there must be a neater way
            GridCoords gridCoords = new GridCoords(rowIndex, columnIndex);
            boxes[Position.indexFromCoords(gridCoords)].changeCell(gridCoords).toValue(value);
        }
    }

}
