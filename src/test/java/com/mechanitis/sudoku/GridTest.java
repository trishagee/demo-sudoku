package com.mechanitis.sudoku;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class GridTest {
    private final Grid grid = new Grid();

    @Test
    void test() {
        Assertions.assertEquals(9, grid.rows.length);
    }

    @Test
    void should_have_nine_columns() {
        fail("Not implemented");
    }

    @Test
    void should_have_exactly_81_cells() {
        fail("Not implemented");
    }
}