package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.*;
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

    @Test
    @DisplayName("Should only allow values 1 to 9")
    void shouldOnlyAllowTheNumbers1To9() {
        // TODO this is a horrible test
        assertThrows(InvalidValueException.class, () -> new Cell(11));
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