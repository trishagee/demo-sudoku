package com.mechanitis.sudoku.data;

import java.util.Arrays;

/**
 * Represents where in the Grid a particular Box is located.
 */
enum BoxPosition {
    TopLeft(new BoxCoords(0, 0)), TopCentre(new BoxCoords(0, 1)), TopRight(new BoxCoords(0, 2)),
    CentreLeft(new BoxCoords(1, 0)), CentreCentre(new BoxCoords(1, 1)), CentreRight(new BoxCoords(1, 2)),
    BottomLeft(new BoxCoords(2, 0)), BottomCentre(new BoxCoords(2, 1)), BottomRight(new BoxCoords(2, 2));

    private final BoxCoords boxCoords;

    BoxPosition(BoxCoords boxCoords) {
        // is this an abuse of BoxCoords? BoxCoords are supposed to reference something in a 3x3 box, which
        // technically also works with the positions of the boxes inside the Grid. This might currently be a
        // coincidence rather than a firm correlation. For now make sure the BoxCoords are an implementation detail
        // hidden inside this class
        this.boxCoords = boxCoords;
    }

    static BoxPosition fromCoords(GridCoords gridCoords) {
        var boxPosition = Arrays.stream(values())
                                .filter(position -> position.boxCoords.row() == gridCoords.row() / 3)
                                .filter(position -> position.boxCoords.column() == gridCoords.column() / 3)
                                .findFirst();
        return boxPosition.orElseThrow(NoSuchPositionException::new);
    }

    static BoxPosition fromIndices(RowIndex row, ColumnIndex column) {
        var boxPosition = Arrays.stream(values())
                                .filter(position -> position.boxCoords.row() == row.index() / 3)
                                .filter(position -> position.boxCoords.column() == column.index() / 3)
                                .findFirst();
        return boxPosition.orElseThrow(NoSuchPositionException::new);
    }

    /**
     * This should never happen, as there should not be a set of coordinates that does not correspond to a position
     */
    private static class NoSuchPositionException extends RuntimeException {
    }
}
