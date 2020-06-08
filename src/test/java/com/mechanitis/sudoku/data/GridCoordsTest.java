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
    @MethodSource("gridCoordinatesToBoxCoordinates")
    void shouldConvertGridCoordinatesIntoBoxCoordinates(final GridCoords gridCoords,
                                                        final BoxCoords expectedBoxCoords) {
        var boxCoords = gridCoords.toBoxCoords();
        assertEquals(expectedBoxCoords.row(), boxCoords.row());
        assertEquals(expectedBoxCoords.column(), boxCoords.column());
    }

    static Stream<Arguments> gridCoordinatesToBoxCoordinates() {
        return Stream.of(
                // Top Left Box
                arguments(new GridCoords(0, 0), new BoxCoords(0, 0)),
                arguments(new GridCoords(0, 1), new BoxCoords(0, 1)),
                arguments(new GridCoords(0, 2), new BoxCoords(0, 2)),
                arguments(new GridCoords(1, 0), new BoxCoords(1, 0)),
                arguments(new GridCoords(1, 1), new BoxCoords(1, 1)),
                arguments(new GridCoords(1, 2), new BoxCoords(1, 2)),
                arguments(new GridCoords(2, 0), new BoxCoords(2, 0)),
                arguments(new GridCoords(2, 1), new BoxCoords(2, 1)),
                arguments(new GridCoords(2, 2), new BoxCoords(2, 2)),
                // Top Centre Box
                arguments(new GridCoords(0, 3), new BoxCoords(0, 0)),
                arguments(new GridCoords(0, 4), new BoxCoords(0, 1)),
                arguments(new GridCoords(0, 5), new BoxCoords(0, 2)),
                arguments(new GridCoords(1, 3), new BoxCoords(1, 0)),
                arguments(new GridCoords(1, 4), new BoxCoords(1, 1)),
                arguments(new GridCoords(1, 5), new BoxCoords(1, 2)),
                arguments(new GridCoords(2, 3), new BoxCoords(2, 0)),
                arguments(new GridCoords(2, 4), new BoxCoords(2, 1)),
                arguments(new GridCoords(2, 5), new BoxCoords(2, 2)),
                // Top Right Box
                arguments(new GridCoords(0, 6), new BoxCoords(0, 0)),
                arguments(new GridCoords(0, 7), new BoxCoords(0, 1)),
                arguments(new GridCoords(0, 8), new BoxCoords(0, 2)),
                arguments(new GridCoords(1, 6), new BoxCoords(1, 0)),
                arguments(new GridCoords(1, 7), new BoxCoords(1, 1)),
                arguments(new GridCoords(1, 8), new BoxCoords(1, 2)),
                arguments(new GridCoords(2, 6), new BoxCoords(2, 0)),
                arguments(new GridCoords(2, 7), new BoxCoords(2, 1)),
                arguments(new GridCoords(2, 8), new BoxCoords(2, 2)),
                // Centre Left Box
                arguments(new GridCoords(3, 0), new BoxCoords(0, 0)),
                arguments(new GridCoords(3, 1), new BoxCoords(0, 1)),
                arguments(new GridCoords(3, 2), new BoxCoords(0, 2)),
                arguments(new GridCoords(4, 0), new BoxCoords(1, 0)),
                arguments(new GridCoords(4, 1), new BoxCoords(1, 1)),
                arguments(new GridCoords(4, 2), new BoxCoords(1, 2)),
                arguments(new GridCoords(5, 0), new BoxCoords(2, 0)),
                arguments(new GridCoords(5, 1), new BoxCoords(2, 1)),
                arguments(new GridCoords(5, 2), new BoxCoords(2, 2)),
                // Centre Centre Box
                arguments(new GridCoords(3, 3), new BoxCoords(0, 0)),
                arguments(new GridCoords(3, 4), new BoxCoords(0, 1)),
                arguments(new GridCoords(3, 5), new BoxCoords(0, 2)),
                arguments(new GridCoords(4, 3), new BoxCoords(1, 0)),
                arguments(new GridCoords(4, 4), new BoxCoords(1, 1)),
                arguments(new GridCoords(4, 5), new BoxCoords(1, 2)),
                arguments(new GridCoords(5, 3), new BoxCoords(2, 0)),
                arguments(new GridCoords(5, 4), new BoxCoords(2, 1)),
                arguments(new GridCoords(5, 5), new BoxCoords(2, 2)),
                // Centre Right Box
                arguments(new GridCoords(3, 6), new BoxCoords(0, 0)),
                arguments(new GridCoords(3, 7), new BoxCoords(0, 1)),
                arguments(new GridCoords(3, 8), new BoxCoords(0, 2)),
                arguments(new GridCoords(4, 6), new BoxCoords(1, 0)),
                arguments(new GridCoords(4, 7), new BoxCoords(1, 1)),
                arguments(new GridCoords(4, 8), new BoxCoords(1, 2)),
                arguments(new GridCoords(5, 6), new BoxCoords(2, 0)),
                arguments(new GridCoords(5, 7), new BoxCoords(2, 1)),
                arguments(new GridCoords(5, 8), new BoxCoords(2, 2)),
                // Bottom Left Box
                arguments(new GridCoords(6, 0), new BoxCoords(0, 0)),
                arguments(new GridCoords(6, 1), new BoxCoords(0, 1)),
                arguments(new GridCoords(6, 2), new BoxCoords(0, 2)),
                arguments(new GridCoords(7, 0), new BoxCoords(1, 0)),
                arguments(new GridCoords(7, 1), new BoxCoords(1, 1)),
                arguments(new GridCoords(7, 2), new BoxCoords(1, 2)),
                arguments(new GridCoords(8, 0), new BoxCoords(2, 0)),
                arguments(new GridCoords(8, 1), new BoxCoords(2, 1)),
                arguments(new GridCoords(8, 2), new BoxCoords(2, 2)),
                // Bottom Centre Box
                arguments(new GridCoords(6, 3), new BoxCoords(0, 0)),
                arguments(new GridCoords(6, 4), new BoxCoords(0, 1)),
                arguments(new GridCoords(6, 5), new BoxCoords(0, 2)),
                arguments(new GridCoords(7, 3), new BoxCoords(1, 0)),
                arguments(new GridCoords(7, 4), new BoxCoords(1, 1)),
                arguments(new GridCoords(7, 5), new BoxCoords(1, 2)),
                arguments(new GridCoords(8, 3), new BoxCoords(2, 0)),
                arguments(new GridCoords(8, 4), new BoxCoords(2, 1)),
                arguments(new GridCoords(8, 5), new BoxCoords(2, 2)),
                // Bottom Right Box
                arguments(new GridCoords(6, 6), new BoxCoords(0, 0)),
                arguments(new GridCoords(6, 7), new BoxCoords(0, 1)),
                arguments(new GridCoords(6, 8), new BoxCoords(0, 2)),
                arguments(new GridCoords(7, 6), new BoxCoords(1, 0)),
                arguments(new GridCoords(7, 7), new BoxCoords(1, 1)),
                arguments(new GridCoords(7, 8), new BoxCoords(1, 2)),
                arguments(new GridCoords(8, 6), new BoxCoords(2, 0)),
                arguments(new GridCoords(8, 7), new BoxCoords(2, 1)),
                arguments(new GridCoords(8, 8), new BoxCoords(2, 2))
        );
    }


}