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

    public Mutator changeCell(GridCoords gridCoords) {
        return new Mutator(gridCoords);
    }

    public Cell cellAt(GridCoords gridCoords) {
        return rows[gridCoords.row()].cellAt(gridCoords.column());
    }

    // TODO: not sure still if we need the mutator.
    class Mutator {
        private final GridCoords gridCoords;

        private Mutator(GridCoords gridCoords) {
            this.gridCoords = gridCoords;
        }

        void toValue(int value) {
            var rowIndex = gridCoords.row();
            var columnIndex = gridCoords.column();
            rows[rowIndex].changeCell(columnIndex).toValue(value);
            columns[columnIndex].changeCell(rowIndex).toValue(value);
            boxes[Position.indexFromCoords(gridCoords)].changeCell(gridCoords).toValue(value);
        }
    }

}
