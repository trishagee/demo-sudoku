package com.mechanitis.sudoku.data;

import static java.lang.String.format;

enum Position {
    TopLeft(0), TopCentre(1), TopRight(2),
    CentreLeft(3), CentreCentre(4), CentreRight(5),
    BottomLeft(6), BottomCentre(7), BottomRight(8);

    private final int index;

    Position(int index) {
        this.index = index;
    }

    static int indexFromCoords(int rowIndex, int columnIndex) {
        int columnOffset = columnIndex / 3;
        if (rowIndex < 3) {
            return columnOffset;
        } else if (rowIndex < 6) {
            return 3 + columnOffset;
        } else {
            return 6 + columnOffset;
        }
    }

    public static Position positionFromCoords(int rowIndex, int columnIndex) {
        return positionFromIndex(indexFromCoords(rowIndex, columnIndex));
    }

    public static Position positionFromIndex(int index) {
        return switch (index) {
            case 0 -> TopLeft;
            case 1 -> TopCentre;
            case 2 -> TopRight;
            case 3 -> CentreLeft;
            case 4 -> CentreCentre;
            case 5 -> CentreRight;
            case 6 -> BottomLeft;
            case 7 -> BottomCentre;
            case 8 -> BottomRight;
            default -> throw new InvalidValueException(format("%s is not a valid index for a box", index));
        };
    }

    public static Coords boxCoordsFromGridCoords(int rowIndex, int columnIndex) {

        return new Coords(rowIndex % 3,columnIndex % 3);
    }

    public int getIndex() {
        return index;
    }
}
