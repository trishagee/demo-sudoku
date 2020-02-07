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
    @DisplayName("Should return the correct position for coordinates")
    @MethodSource("coordinatesAndPositions")
    void shouldReturnTheCorrectPositionForCoordinates(int rowIndex, int columnIndex, Position position) {
        assertEquals(position, Position.positionFromCoords(rowIndex, columnIndex));
    }

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

    static Stream<Arguments> coordinatesAndPositions() {
        return Stream.of(
                arguments(0, 0, TopLeft),
                arguments(0, 3, TopCentre),
                arguments(0, 6, TopRight),
                arguments(3, 0, CentreLeft),
                arguments(3, 3, CentreCentre),
                arguments(3, 6, CentreRight),
                arguments(6, 0, BottomLeft),
                arguments(6, 3, BottomCentre),
                arguments(6, 6, BottomRight)
        );
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


}