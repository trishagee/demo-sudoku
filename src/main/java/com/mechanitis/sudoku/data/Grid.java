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

    public Cell cellAt(GridCoords gridCoords) {
        return rows[gridCoords.row()].cellAt(gridCoords.column());
    }

    public Mutator changeCell(GridCoords gridCoords) {
        return new Mutator(gridCoords);
    }

    public int getNumberOfFilledSquares() {
        return 0;
    }

    // TODO: not sure still if we need the mutator. I do like the API this way though
    public class Mutator {
        private final GridCoords gridCoords;

        private Mutator(GridCoords gridCoords) {
            this.gridCoords = gridCoords;
        }

        public void toValue(int value) {
            rows[gridCoords.row()].changeCell(gridCoords.column()).toValue(value);
            columns[gridCoords.column()].changeCell(gridCoords.row()).toValue(value);
            boxes.get(BoxPosition.fromCoords(gridCoords)).changeCell(gridCoords.toBoxCoords()).toValue(value);
        }
    }

}
