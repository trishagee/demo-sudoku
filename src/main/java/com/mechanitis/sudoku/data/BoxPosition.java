package com.mechanitis.sudoku.data;

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
        for (int i = 0; i < values().length; i++) {
            BoxPosition value = values()[i];
            if (value.index == index) {
                return value;
            }
        }
        throw new RuntimeException("These grid co-ordinates don't correspond to a BoxPosition: " + gridCoords);
    }

    public int getIndex() {
        return index;
    }
}
