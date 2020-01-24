package com.mechanitis.sudoku.data;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private final Grid grid = new Grid();

    @Test
    @DisplayName("Should have nine rows")
    void shouldHaveNineRows() {
        assertEquals(9, grid.rows.length);
    }

    @Test
    @DisplayName("Should have nine columns")
    void shouldHaveNineColumns() {
        assertEquals(9, grid.columns.length);
    }

    // should be able to navigate to row from cell
    // should be able to navigate to column from cell
    // should be able to view all items in a row
    // should be able to view all times in a column

    @Test
    @DisplayName("Should be able to get to a specific cell")
    void shouldBeAbleToGetToASpecificCell() {
        //(maybe microtypes are useful for this?)
        Cell cell = grid.cellAt(0, 0);
        assertNotNull(cell);
    }

    @Nested
    @DisplayName("When the grid contains some values")
    class WhenValuesExist {
        private final int columnIndex = 3;
        private final int value = 5;
        private final int rowIndex = 7;

        @BeforeEach
        void setValueInOneRow() {
            //don't think this should be visible!
            grid.rows[7].changeCell().atPosition(columnIndex).toValue(value);
        }

        @Test
        @DisplayName("Should get the correct existing value")
        void getTheCorrectExistingValue() {
            assertEquals(value, grid.cellAt(rowIndex, columnIndex).getValue());
        }

        @Nested
        @DisplayName("Should allow ")
        class ShouldAllow {
            @Test
            @DisplayName("a change to existing value")
            @Disabled("not implemented")
            void should() {
                fail("Not implemented");
            }
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("??")
            @Disabled("not implemented")
            void shouldNot() {
                fail("Not implemented");
            }
        }
    }


    //ideas
    // is this a good place to try out different testing types, mutation testing for example
}