package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GridCoordsTest {

    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should throw an exception for invalid coordinates")
    @CsvSource({"0,9", "9,0", "9,9", "-1,0", "0,-1"})
    void shouldThrowAnExceptionForInvalidCoordinates(int rowIndex, int columnIndex) {
        assertThrows(InvalidValueException.class, () -> new GridCoords(rowIndex, columnIndex));
    }


}