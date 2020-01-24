package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        Row row = new Row();
        assertEquals(9, row.getLength());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int position) {
        Row row = new Row();
        Cell cell = row.cellAt(position);
        // this should be changed so we know we have the correct cell!
        assertTrue(cell.isEmpty());
    }

    @DisplayName("Should not be able to get a cell at any position not 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-1, 9, 7847843})
    void shouldNotBeAbleToGetACellAtInvalidPosition(int position) {
        Row row = new Row();
        assertThrows(InvalidPositionException.class, () -> row.cellAt(position));
    }

    @Test
    @DisplayName("Should be able to set a value at a given position")
    void shouldBeAbleToSetAValueAtAGivenPosition() {
        // given
        int expectedValue = 5;
        Row row = new Row();

        // when
        Cell cell = row.cellAt(3);
        cell.setValue(expectedValue);

        // then
        assertEquals(expectedValue, cell.getValue());
    }

    @Test
    @DisplayName("Should not allow duplicate values in a row")
    @Disabled("Not implemented")
    void shouldNotAllowDuplicateValuesInARow() {
        Row row = new Row();
//        row.cellAt(3).hasValue(5);
    }

}