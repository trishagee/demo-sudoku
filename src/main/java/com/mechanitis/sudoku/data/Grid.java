package com.mechanitis.sudoku.data;

import java.util.Map;

import static com.mechanitis.sudoku.data.BoxPosition.*;

public class Grid {
    private static final int SIZE = 9;
    // the data is effectively duplicated in all, but it makes it easy to access
    private final Row[] rows = new Row[SIZE];
    private final Column[] columns = new Column[SIZE];
    // an array might be more memory efficient, but this is easier to use than doing mod/div operations on an array
    private final Map<BoxPosition, Box> boxes = Map.of(TopLeft, new Box(), TopCentre, new Box(), TopRight, new Box(),
                                                       CentreLeft, new Box(), CentreCentre, new Box(), CentreRight, new Box(),
                                                       BottomLeft, new Box(), BottomCentre, new Box(), BottomRight, new Box());

    public Grid() {
        for (int i = 0; i < SIZE; i++) {
            rows[i] = new Row();
            columns[i] = new Column();
        }
    }

    public Row rowAt(int rowIndex) {
        return rows[rowIndex];
    }

    public Column columnAt(int columnIndex) {
        return columns[columnIndex];
    }

    public Box boxAt(BoxPosition boxPosition) {
        return boxes.get(boxPosition);
    }

    public Cell cellAt(RowIndex row, ColumnIndex column) {
        return rows[row.index()].cellAt(column.index());
    }

    public Mutator changeCell(RowIndex row, ColumnIndex column) {
        return new Mutator(row, column);
    }

    // TODO: not sure still if we need the mutator. I do like the API this way though
    public class Mutator {
        private final RowIndex row;
        private final ColumnIndex column;

        private Mutator(RowIndex row, ColumnIndex column) {
            this.row = row;
            this.column = column;
        }

        public void toValue(int value) {
            rows[row.index()].changeCell(column.index()).toValue(value);
            columns[column.index()].changeCell(row.index()).toValue(value);
            boxes.get(BoxPosition.fromIndices(row, column)).changeCell(BoxCoords.fromIndices(row, column)).toValue(value);
        }
    }

}
