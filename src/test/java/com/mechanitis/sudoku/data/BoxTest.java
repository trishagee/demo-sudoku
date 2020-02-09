package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BoxTest {
    private final Box box = new Box(6, 7, 1,
                                    2, 4, 3,
                                    5, 8, 9);

    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should get the correct cell from coordinates")
    @CsvSource({"0,0, 6", "0,1, 7", "0,2, 1", "1,0, 2", "1,1, 4", "1,2, 3", "2,0, 5", "2,1, 8", "2,2, 9"})
    void shouldGetTheCorrectCellFromCoordinates(int rowIndex, int columnIndex, int value) {
        assertEquals(value, box.cellAt(rowIndex, columnIndex).getValue());
    }

    @ParameterizedTest(name = "{0}, {1} = {2}")
    @DisplayName("Should throw an exception for invalid coordinates")
    @CsvSource({"0,3", "3,0", "3,3", "-1,0", "0,-1"})
    void shouldThrowAnExceptionForInvalidCoordinates(int rowIndex, int columnIndex) {
        assertThrows(InvalidValueException.class, () -> box.cellAt(rowIndex, columnIndex));
    }

    @Test
    @DisplayName("Should set empty from coordinate")
    void shouldSetEmptyFromCoordinate() {
        assumeTrue(box.cellAt(1, 2).getValue() == 3);
        box.changeCell().atPosition(1, 2).toEmpty();

        assertTrue(box.cellAt(1, 2).isEmpty());
    }

    @Test
    @DisplayName("Should set the value from coordinate")
    void shouldSetTheValueFromCoordinate() {
        //if we want to use a value, we first have to remove it from its original position
        assumeTrue(box.cellAt(2, 2).getValue() == 9);
        box.changeCell().atPosition(2, 2).toEmpty();
        assumeTrue(box.cellAt(2, 1).getValue() == 8);

        box.changeCell().atPosition(2, 1).toValue(9);

        assertEquals(9, box.cellAt(2, 1).getValue());
    }

    @Test
    @DisplayName("Should not be able to add duplicate values")
    void shouldNotBeAbleToAddDuplicateValues() {
        assumeTrue(box.cellAt(0, 0).getValue() == 6);

        assertThrows(InvalidValueException.class, () -> box.changeCell().atPosition(2, 1).toValue(3));
    }
}