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

    // should be able to view all items in a row
    // should be able to view all times in a column

    @Test
    @DisplayName("Should be able to get a specific cell")
    void shouldBeAbleToGetASpecificCell() {
        //(maybe microtypes are useful for this?)
        Cell cell = grid.cellAt(0, 0);
        assertNotNull(cell);
    }

    @Test
    @DisplayName("Should be able to set the value of a specific cell")
    void shouldBeAbleToSetTheValueOfASpecificCell() {
        grid.changeCell().onRow(1).atPosition(3).toValue(5);
        assertEquals(5, grid.cellAt(1, 3).getValue());
    }

    @Nested
    @DisplayName("When the grid contains some values")
    class WhenValuesExist {
        private final int columnIndex = 3;
        private final int value = 5;
        private final int rowIndex = 7;

        @BeforeEach
        void setValueInOneRow() {
            grid.changeCell().onRow(rowIndex).atPosition(columnIndex).toValue(value);
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