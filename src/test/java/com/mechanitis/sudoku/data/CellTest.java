package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    // should be able to get its Column

    @Test
    @DisplayName("Should allow empty value")
    void shouldAllowBlankValue() {
        Cell cell = new Cell();
        assertTrue(cell.isEmpty());
    }

    @Nested
    @DisplayName("When creating a new cell")
    class WhenNew {

        @DisplayName("this value should be valid")
        @ParameterizedTest(name = "{0}")
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
        void isValid(int value) {
            Cell cell = new Cell(value);
            assertFalse(cell.isEmpty());
            assertEquals(value, cell.getValue());
        }

        @DisplayName("this value should not be allowed")
        @ParameterizedTest(name = "{0}")
        @ValueSource(ints = {-1, 0, 10, 7837843})
        void isNotValid(int value) {
            assertThrows(InvalidValueException.class, () -> new Cell(value));
        }
    }

    @Test
    @DisplayName("Should be able to get a value from a cell")
    void shouldBeAbleToGetAValueFromACell() {
        Cell cell = new Cell(3);
        assertEquals(3, cell.getValue());
    }

    @Test
    @DisplayName("Should be able to get its row")
    @Disabled("Not implemented")
    void shouldBeAbleToGetItsRow() {
        Cell cell = new Cell(0);
        assertNotNull(cell.getRow());
    }

}