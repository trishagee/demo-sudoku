package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.mechanitis.sudoku.data.BoxPosition.*;
import static com.mechanitis.sudoku.data.ColumnIndex.column;
import static com.mechanitis.sudoku.data.RowIndex.row;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GridTest {
    private final Grid grid = new Grid();

    @Test
    @DisplayName("Should be able to get a specific cell with row and column index")
    void shouldBeAbleToGetASpecificCellWithRowAndColumnIndex() {
        Cell cell = grid.cellAt(row(0), column(0));
        assertNotNull(cell);
    }

    @Test
    @DisplayName("Should be able to set the value of a specific cell")
    void shouldBeAbleToSetTheValueOfASpecificCell() {
        // when
        grid.setCellValue(row(1), column(3), 5);

        // then
        assertEquals(5, grid.cellAt(row(1), column(3)).getValue());
    }

    @Nested
    @DisplayName("When the grid contains a value")
    class WhenValuesExist {
        private final int columnIndex = 3;
        private final int value = 5;
        private final int rowIndex = 7;

        @BeforeEach
        void setValueInOneRow() {
            grid.setCellValue(row(rowIndex), column(columnIndex), value);
        }

        @Test
        @DisplayName("Should get the correct existing value")
        void getTheCorrectExistingValue() {
            assertEquals(value, grid.cellAt(row(rowIndex), column(columnIndex)).getValue());
        }

        @Test
        @DisplayName("Should have one filled square")
        @Disabled("not implemented yet")
        void shouldKnowItHasOneFilledSquare() {
            assertEquals(1, grid.getNumberOfFilledSquares());
        }

        @Test
        @DisplayName("Should be able to change a value")
        void shouldBeAbleToChangeAValue() {
            // given
            int newValue = 9;
            var row = row(rowIndex);
            var column = column(columnIndex);

            // when
            grid.setCellValue(row, column, newValue);

            // then
            assertEquals(newValue, grid.cellAt(row, column).getValue());
        }

        @Test
        @DisplayName("Should not be able to change a value by accessing the cell directly")
        void shouldNotBeAbleToChangeAValueUsingCellAt() {
            grid.cellAt(row(rowIndex), column(columnIndex)).setValue(9);

            assertEquals(value, grid.cellAt(row(rowIndex), column(columnIndex)).getValue());
        }

        @Nested
        @DisplayName("Should error")
        class ShouldError {
            /* All of these are covered by row/column/box unit tests but for now I want it here too as
               it is a requirement of the Grid too.
             */
            @Test
            @DisplayName("when trying to insert a value that is already duplicated in the row")
            void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheRow() {
                assertThrows(DuplicateValueException.class,
                             () -> grid.setCellValue(row(rowIndex), column(8), value));
            }

            @Test
            @DisplayName("when trying to insert a value that is already duplicated in the column")
            void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheColumn() {
                assertThrows(DuplicateValueException.class,
                             () -> grid.setCellValue(row(8), column(columnIndex), value));
            }

            @Test
            @DisplayName("when trying to insert a value that is already duplicated in the box")
            void shouldErrorIfTryingToInsertAValueThatIsAlreadyDuplicatedInTheBox() {
                assertThrows(DuplicateValueException.class,
                             () -> grid.setCellValue(row(8), column(4), value));
            }
        }
    }

    @DisplayName("Should return the correct number of filled squares")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    @Disabled("not implemented yet")
    void shouldReturnTheCorrectNumberOfFilledSquares(int numberOfValues) {
        Grid grid = new Grid();
        for (int i = 0; i < numberOfValues; i++) {
            grid.setCellValue(row(i), column(i), i + 1);
        }
        System.out.println("grid = " + grid);
        assertEquals(numberOfValues, grid.getNumberOfFilledSquares());
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
    void shouldBeAbleToGetABox() {
        Block box = grid.boxAt(TopLeft);
        assertNotNull(box);
    }

    @Test
    @DisplayName("Should have the same value whether accessed via Column or Row or Box")
    void shouldHaveTheSameValueWhetherAccessedViaColumnOrRowOrBox() {
        int value = 7;
        int rowIndex = 4;
        int columnIndex = 1;
        var row = row(rowIndex);
        var column = column(columnIndex);
        grid.setCellValue(row, column, value);

        assertEquals(value, grid.cellAt(row, column).getValue());
        assertEquals(value, grid.rowAt(rowIndex).cellAt(columnIndex).getValue());
        assertEquals(value, grid.columnAt(columnIndex).cellAt(rowIndex).getValue());
        assertEquals(value, grid.boxAt(CentreLeft).cellAt(row, column).getValue());
    }

    @DisplayName("Should get the correct box for a position")
    //probably this test understands implementation details
    @ParameterizedTest(name = "{0}")
    @MethodSource("positionAndValueProvider")
    void shouldBeAbleToGetABox(BoxPosition boxPosition, int expectedValue) {
        insertValuesIntoFirstCellOfEachBox();

        Box box = grid.boxAt(boxPosition);
        BoxCoords boxCoords = new BoxCoords(0, 0);
        assertEquals(expectedValue, box.cellAt(boxCoords).getValue());
    }

    @Test
    @DisplayName("Should be able to set a value and read it from the box")
    void shouldBeAbleToSetAValueAndReadItFromTheBox() {
        int expectedValue = 1;
        var row = row(7);
        var column = column(8);
        grid.setCellValue(row, column, expectedValue);

        // not sure at the moment if we need to be able to access by both types of coords
        BoxCoords boxCoords = new BoxCoords(1, 2);
        assertEquals(expectedValue, grid.boxAt(BottomRight).cellAt(boxCoords).getValue());
        assertEquals(expectedValue, grid.boxAt(BottomRight).cellAt(row, column).getValue());
    }

    private void insertValuesIntoFirstCellOfEachBox() {
        grid.setCellValue(row(0), column(0), 1);
        grid.setCellValue(row(0), column(3), 2);
        grid.setCellValue(row(0), column(6), 3);
        grid.setCellValue(row(3), column(0), 4);
        grid.setCellValue(row(3), column(3), 5);
        grid.setCellValue(row(3), column(6), 6);
        grid.setCellValue(row(6), column(0), 7);
        grid.setCellValue(row(6), column(3), 8);
        grid.setCellValue(row(6), column(6), 9);
    }

    static Stream<Arguments> positionAndValueProvider() {
        return Stream.of(
                arguments(TopLeft, 1),
                arguments(TopCentre, 2),
                arguments(TopRight, 3),
                arguments(CentreLeft, 4),
                arguments(CentreCentre, 5),
                arguments(CentreRight, 6),
                arguments(BottomLeft, 7),
                arguments(BottomCentre, 8),
                arguments(BottomRight, 9)
        );
    }

    //ideas
    // is this a good place to try out different testing types, mutation testing for example
    // do we need a way to define / use / inject a generator?

    // TODO
    // - Use Coords record more
    // - Provide a better way of converting between grid and box coords
    // - reduce duplication in PositionTest to have just one set of data
    // - see if there's a way to simplify all the helpers in Position so that there aren't so many

    // Solver
    // - Benchmark

    // Generator
    // - Different approaches, e.g. brute force, "which numbers are left to try"
    // - Benchmark

}