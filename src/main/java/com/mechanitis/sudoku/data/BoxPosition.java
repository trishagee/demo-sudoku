package com.mechanitis.sudoku.data;

import java.util.Arrays;

enum BoxPosition {
    TopLeft(0), TopCentre(1), TopRight(2),
    CentreLeft(3), CentreCentre(4), CentreRight(5),
    BottomLeft(6), BottomCentre(7), BottomRight(8);

    private final int index;

    BoxPosition(int index) {
        this.index = index;
    }

    static int indexFromCoords(GridCoords gridCoords) {
        int columnOffset = gridCoords.column() / 3;
        return switch (gridCoords.row()) {
            case 0, 1, 2 -> columnOffset;
            case 3, 4, 5 -> columnOffset + 3;
            default -> columnOffset + 6;
        };
    }

    static BoxPosition fromCoords(GridCoords gridCoords) {
        var index = indexFromCoords(gridCoords);
        var position = Arrays.stream(values())
                             .filter(boxPosition -> boxPosition.index == index)
                             .findFirst();
        return position.orElseThrow(NoSuchPositionException::new);
    }

    public int getIndex() {
        return index;
    }

    /**
     * This should never happen, as there should not be a set of coordinates that does not correspond to a position
     */
    private static class NoSuchPositionException extends RuntimeException {
    }
}
