package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.mechanitis.sudoku.data.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PositionTest {
    @ParameterizedTest
    @DisplayName("Should return the correct position for an index")
    @MethodSource("indexAndPositions")
    void shouldReturnTheCorrectPositionForAnIndex(int index, Position position) {
        assertEquals(position, Position.positionFromIndex(index));
    }

    @ParameterizedTest (name = "{0}")
    @DisplayName("Should throw an Exception for an invalid index")
    @ValueSource(ints = {-1, 9, 10, Integer.MAX_VALUE})
    void shouldThrowAnExceptionForAnInvalidIndex(int index) {
        assertThrows(InvalidValueException.class, () -> Position.positionFromIndex(index));
    }

    @ParameterizedTest
    @DisplayName("Should return the correct index for coordinates")
    @MethodSource("coordinatesAndIndex")
    void shouldReturnTheCorrectIndexForCoordinates(int rowIndex, int columnIndex, int index) {
        assertEquals(index, Position.indexFromCoords(rowIndex, columnIndex));
    }

    static Stream<Arguments> indexAndPositions() {
        return Stream.of(
                arguments(0, TopLeft),
                arguments(1, TopCentre),
                arguments(2, TopRight),
                arguments(3, CentreLeft),
                arguments(4, CentreCentre),
                arguments(5, CentreRight),
                arguments(6, BottomLeft),
                arguments(7, BottomCentre),
                arguments(8, BottomRight)
        );
    }

    static Stream<Arguments> coordinatesAndIndex() {
        return Stream.of(
                arguments(0, 0, 0),
                arguments(0, 3, 1),
                arguments(0, 6, 2),
                arguments(3, 0, 3),
                arguments(3, 3, 4),
                arguments(3, 6, 5),
                arguments(6, 0, 6),
                arguments(6, 3, 7),
                arguments(6, 6, 8)
        );
    }

    //This can be turned into the general data method - add the index and the position and you can reuse it
    static Stream<Arguments> gridCoordinatesToBoxCoordinates() {
        return Stream.of(
                // Top Left
                arguments(0, 0, 0, 0),
                arguments(0, 1, 0, 1),
                arguments(0, 2, 0, 2),
                arguments(1, 0, 1, 0),
                arguments(1, 1, 1, 1),
                arguments(1, 2, 1, 2),
                arguments(2, 0, 2, 0),
                arguments(2, 1, 2, 1),
                arguments(2, 2, 2, 2),
                // Top Centre
                arguments(0, 3, 0, 0),
                arguments(0, 4, 0, 1),
                arguments(0, 5, 0, 2),
                arguments(1, 3, 1, 0),
                arguments(1, 4, 1, 1),
                arguments(1, 5, 1, 2),
                arguments(2, 3, 2, 0),
                arguments(2, 4, 2, 1),
                arguments(2, 5, 2, 2),
                // Top Right
                arguments(0, 6, 0, 0),
                arguments(0, 7, 0, 1),
                arguments(0, 8, 0, 2),
                arguments(1, 6, 1, 0),
                arguments(1, 7, 1, 1),
                arguments(1, 8, 1, 2),
                arguments(2, 6, 2, 0),
                arguments(2, 7, 2, 1),
                arguments(2, 8, 2, 2),
                // Centre Left
                arguments(3, 0, 0, 0),
                arguments(3, 1, 0, 1),
                arguments(3, 2, 0, 2),
                arguments(4, 0, 1, 0),
                arguments(4, 1, 1, 1),
                arguments(4, 2, 1, 2),
                arguments(5, 0, 2, 0),
                arguments(5, 1, 2, 1),
                arguments(5, 2, 2, 2),
                // Centre Centre
                arguments(3, 3, 0, 0),
                arguments(3, 4, 0, 1),
                arguments(3, 5, 0, 2),
                arguments(4, 3, 1, 0),
                arguments(4, 4, 1, 1),
                arguments(4, 5, 1, 2),
                arguments(5, 3, 2, 0),
                arguments(5, 4, 2, 1),
                arguments(5, 5, 2, 2),
                // Centre Right
                arguments(3, 6, 0, 0),
                arguments(3, 7, 0, 1),
                arguments(3, 8, 0, 2),
                arguments(4, 6, 1, 0),
                arguments(4, 7, 1, 1),
                arguments(4, 8, 1, 2),
                arguments(5, 6, 2, 0),
                arguments(5, 7, 2, 1),
                arguments(5, 8, 2, 2),
                // Bottom Left
                arguments(6, 0, 0, 0),
                arguments(6, 1, 0, 1),
                arguments(6, 2, 0, 2),
                arguments(7, 0, 1, 0),
                arguments(7, 1, 1, 1),
                arguments(7, 2, 1, 2),
                arguments(8, 0, 2, 0),
                arguments(8, 1, 2, 1),
                arguments(8, 2, 2, 2),
                // Bottom Centre
                arguments(6, 3, 0, 0),
                arguments(6, 4, 0, 1),
                arguments(6, 5, 0, 2),
                arguments(7, 3, 1, 0),
                arguments(7, 4, 1, 1),
                arguments(7, 5, 1, 2),
                arguments(8, 3, 2, 0),
                arguments(8, 4, 2, 1),
                arguments(8, 5, 2, 2),
                // Bottom Right
                arguments(6, 6, 0, 0),
                arguments(6, 7, 0, 1),
                arguments(6, 8, 0, 2),
                arguments(7, 6, 1, 0),
                arguments(7, 7, 1, 1),
                arguments(7, 8, 1, 2),
                arguments(8, 6, 2, 0),
                arguments(8, 7, 2, 1),
                arguments(8, 8, 2, 2)
        );
    }


}