package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BoxCoordsTest {
    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should throw an exception for invalid coordinates")
    @CsvSource({"0,3", "3,0", "3,3", "-1,0", "0,-1"})
    void shouldThrowAnExceptionForInvalidCoordinates(int rowIndex, int columnIndex) {
        assertThrows(InvalidValueException.class, () -> new BoxCoords(rowIndex, columnIndex));
    }
}