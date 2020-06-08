package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GridCoordsTest {

    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should throw an exception for invalid coordinates")
    @CsvSource({"0,9", "9,0", "9,9", "-1,0", "0,-1"})
    void shouldThrowAnExceptionForInvalidCoordinates(int rowIndex, int columnIndex) {
        assertThrows(InvalidValueException.class, () -> new GridCoords(rowIndex, columnIndex));
    }

    @ParameterizedTest
    @DisplayName("Should convert grid coordinates into box coordinates")
    @MethodSource("com.mechanitis.sudoku.data.GridBoxAndPositionData#gridCoordinatesBoxCoordinatesAndPosition")
    void shouldConvertGridCoordinatesIntoBoxCoordinates(final GridCoords gridCoords,
                                                        final BoxCoords expectedBoxCoords) {
        // when
        var boxCoords = gridCoords.toBoxCoords();

        // then
        assertEquals(expectedBoxCoords.row(), boxCoords.row());
        assertEquals(expectedBoxCoords.column(), boxCoords.column());
    }
}