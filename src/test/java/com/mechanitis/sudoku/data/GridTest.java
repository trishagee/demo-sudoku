package com.mechanitis.sudoku.data;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private final Grid grid = new Grid();

    @Test ()
    @DisplayName("Grid should have nine rows")
    void should_have_nine_rows() {
        Assertions.assertEquals(9, grid.rows.length);
    }

    @Test
    void should_have_nine_columns() {
        Assertions.assertEquals(9, grid.columns.length);
    }

    @Test
    void should_have_exactly_81_cells() {
        fail("Not implemented");
    }

    // should be able to navigate to row from cell
    // should be able to navigate to column from cell
    // should be able to view all items in a row
    // should be able to view all times in a column

    @Test
    @DisplayName("Should be able to get to a specific cell")
    void shouldBeAbleToGetToASpecificCell() {
        //(maybe microtypes are useful for this?)
        Cell cell = grid.getCell(0, 0);

        assertNotNull(cell);
    }

    // each number (1-9) should appear 9 times in the grid


    //ideas
    // is this a good place to try out different testing types, mutation testing for example
}