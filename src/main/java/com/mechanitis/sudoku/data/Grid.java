package com.mechanitis.sudoku.data;

import javax.swing.*;

/**
 * 0,0 0,1 0,2
 */
public class Grid {
    private static final int SIZE = 9;
    // the data is effectively duplicated in both, but it makes it easy to access
    Row[] rows = new Row[SIZE];
    Column[] columns = new Column[SIZE];
    Box[] boxes = new Box[SIZE];

    public Grid() {
        for (int i = 0; i < SIZE; i++) {
            rows[i] = new Row();
            columns[i] = new Column();
            boxes[i] = new Box();
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

    public Block boxAt(Position position) {
        return boxes[position.index];
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
            columns[columnIndex].changeCell().atPosition(rowIndex).toValue(value);
            boxes[Position.indexFromCoords(rowIndex, columnIndex)].changeCell().atPosition(0).toValue(value);
        }
    }

    enum Position {
        TopLeft(0), TopCentre(0), TopRight(0),
        CentreLeft(0), CentreCentre(0), CentreRight(0),
        BottomLeft(0), BottomCentre(0), BottomRight(0);

        private final int index;

        Position(int index) {
            this.index = index;
        }

        private static int indexFromCoords(int rowIndex, int columnIndex) {
            return 0;
        }
    }
}
