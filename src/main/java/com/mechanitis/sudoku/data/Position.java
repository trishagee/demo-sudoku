package com.mechanitis.sudoku.data;

enum Position {
    TopLeft(0), TopCentre(1), TopRight(2),
    CentreLeft(3), CentreCentre(4), CentreRight(5),
    BottomLeft(6), BottomCentre(7), BottomRight(8);

    private final int index;

    Position(int index) {
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

    public int getIndex() {
        return index;
    }
}
