package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxPositionTest {
    @SuppressWarnings("unused") // this method uses a more general data source and not all params are required
    @ParameterizedTest(name = "{0} should be \"{3}\"")
    @DisplayName("Should return the correct index for grid coordinates")
    @MethodSource("com.mechanitis.sudoku.data.GridBoxAndPositionData#gridCoordinatesBoxCoordinatesAndPosition")
    void shouldReturnTheCorrectPositionForGridCoordinates(final GridCoords gridCoords, final BoxCoords boxCoords,
                                                          final int index, final BoxPosition boxPosition) {
        assertEquals(boxPosition, BoxPosition.fromCoords(gridCoords));
    }
}