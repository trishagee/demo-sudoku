package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.mechanitis.sudoku.data.BoxCoords.boxCoords;
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
        var boxCoords = boxCoords(rowIndex, columnIndex);
        assertEquals(value, box.cellAt(boxCoords).getValue());
    }

    @Test
    @DisplayName("Should set empty from coordinate")
    void shouldSetEmptyFromCoordinate() {
        var boxCoords = boxCoords(1, 2);
        assumeTrue(box.cellAt(boxCoords).getValue() == 3);
        box.changeCell().atPosition(boxCoords).toEmpty();

        assertTrue(box.cellAt(boxCoords).isEmpty());
    }

    @Test
    @DisplayName("Should set the value from coordinate")
    void shouldSetTheValueFromCoordinate() {
        var coordsForPositionWithValueWeWant = boxCoords(2, 2);
        //if we want to use a value, we first have to remove it from its original position
        assumeTrue(box.cellAt(coordsForPositionWithValueWeWant).getValue() == 9);
        box.changeCell().atPosition(coordsForPositionWithValueWeWant).toEmpty();
        assumeTrue(box.cellAt(coordsForPositionWithValueWeWant).getValue() == 8);

        var coordsForPositionWeWantToUpdate = boxCoords(2, 1);
        box.changeCell().atPosition(coordsForPositionWeWantToUpdate).toValue(9);

        assertEquals(9, box.cellAt(coordsForPositionWeWantToUpdate).getValue());
    }

    @Test
    @DisplayName("Should not be able to add duplicate values")
    void shouldNotBeAbleToAddDuplicateValues() {
        var boxCoords = boxCoords(0, 0);
        assumeTrue(box.cellAt(boxCoords).getValue() == 6);

        assertThrows(DuplicateValueException.class,
                () -> box.changeCell().atPosition(boxCoords(2, 1)).toValue(3));
    }
}