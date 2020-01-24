package com.mechanitis.sudoku.data;


import com.mechanitis.sudoku.data.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class GridTest {
    private final Grid grid = new Grid();

    @Test
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
    // should be able to get to a specific cell (column, row) (maybe microtypes are useful for this?)

    // each row should only have the numbers 1-9
    // each column should only have the numbers 1-9
    // each number (1-9) should appear 9 times in the grid


    //ideas
    // is this a good place to try out different testing types, mutation testing for example
}