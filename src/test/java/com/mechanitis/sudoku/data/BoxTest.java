package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BoxTest {
    private final Box box = new Box(6, 7, 1,
                                    2, 4, 3,
                                    5, 8, 9);

    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should get the correct cell from coordinates")
    @MethodSource("rowColAndValue")
    void shouldGetTheCorrectCellFromCoordinates(int rowIndex, int columnIndex, int value) {
        assertEquals(value, box.cellAt(rowIndex, columnIndex).getValue());
    }

    static Stream<Arguments> rowColAndValue() {
        return Stream.of(
                arguments(0, 0, 6),
                arguments(0, 1, 7),
                arguments(0, 2, 1),
                arguments(1, 0, 2),
                arguments(1, 1, 4),
                arguments(1, 2, 3),
                arguments(2, 0, 5),
                arguments(2, 1, 8),
                arguments(2, 2, 9)
        );
    }
}