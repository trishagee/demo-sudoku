package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    // should be able to get its Column

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