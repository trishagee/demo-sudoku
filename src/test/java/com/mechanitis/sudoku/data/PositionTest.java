package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @SuppressWarnings("unused") // this method uses a more general data source and not all params are required
    @ParameterizedTest
    @DisplayName("Should return the correct index for grid coordinates")
    @MethodSource("com.mechanitis.sudoku.data.GridBoxAndPositionData#gridCoordinatesBoxCoordinatesAndPosition")
    void shouldReturnTheCorrectIndexForGridCoordinates(final GridCoords gridCoords, final BoxCoords boxCoords,
                                                       final int index, final Position position) {
        assertEquals(index, Position.indexFromCoords(gridCoords));
    }

}