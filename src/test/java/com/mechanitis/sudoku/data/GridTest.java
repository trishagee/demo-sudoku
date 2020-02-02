package com.mechanitis.sudoku.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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

        @Test
        @DisplayName("Should be able to change a value")
        void shouldBeAbleToChangeAValue() {
            int newValue = 9;
            grid.changeCell().onRow(rowIndex).atPosition(columnIndex).toValue(newValue);
            assertEquals(newValue, grid.cellAt(rowIndex, columnIndex).getValue());
        }

    }

    @Test
    @DisplayName("Should be able to draw the grid")
    @Disabled("Not implemented yet")
    void shouldBeAbleToDrawTheGrid() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("Should be able to get a column")
    void shouldBeAbleToGetAColumn() {
        Column column = grid.columnAt(3);
        assertNotNull(column);
    }

    @Test
    @DisplayName("Should be able to get a row")
    void shouldBeAbleToGetARow() {
        Row row = grid.rowAt(4);
        assertNotNull(row);
    }

    @Test
    @DisplayName("Should be able to get a box")
    @Disabled("Not implemented yet")
    void shouldBeAbleToGetABox() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("Should have the same value whether accessed via Column or Row")
    void shouldHaveTheSameValueWhetherAccessedViaColumnOrRow() {
        int value = 7;
        int rowIndex = 4;
        int columnIndex = 2;
        grid.changeCell().onRow(rowIndex).atPosition(columnIndex).toValue(value);

        assertEquals(value, grid.rowAt(rowIndex).cellAt(columnIndex).getValue());
        assertEquals(value, grid.columnAt(columnIndex).cellAt(rowIndex).getValue());
    }

    @Test
    @DisplayName("Should error if trying to insert a value that is already duplicated in the row")
    void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheRow() {
        grid.changeCell().onRow(2).atPosition(3).toValue(7);

        assertThrows(InvalidValueException.class, () -> grid.changeCell().onRow(2).atPosition(8).toValue(7));
    }

    @Test
    @DisplayName("Should error if trying to insert a value that is already duplicated in the column")
    void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheColumn() {
        grid.changeCell().onRow(2).atPosition(3).toValue(7);

        assertThrows(InvalidValueException.class, () -> grid.changeCell().onRow(8).atPosition(3).toValue(7));
    }

    @Test
    @DisplayName("Should error if trying to insert a value that is already duplicated in the box")
    @Disabled("Not implemented yet")
    void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheBox() {
        grid.changeCell().onRow(0).atPosition(0).toValue(7);

        assertThrows(InvalidValueException.class, () -> grid.changeCell().onRow(1).atPosition(1).toValue(7));
    }

    //ideas
    // is this a good place to try out different testing types, mutation testing for example
    // do we need a way to define / use / inject a generator?
}