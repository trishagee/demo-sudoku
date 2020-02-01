package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("Columns should basically do the same thing as rows. I've got the code compiling, but now I need to add an " +
          "abstraction to help reuse")
class ColumnTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        assertEquals(9, new Column().getLength());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int position) {
        Cell cell = new Column().cellAt(position);
        // this should be changed so we know we have the correct cell!
        assertTrue(cell.isEmpty());
    }

    @DisplayName("Should not be able to get a cell at any position not 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-1, 9, 7847843})
    void shouldNotBeAbleToGetACellAtInvalidPosition(int position) {
        Column column = new Column();
        assertThrows(InvalidPositionException.class, () -> column.cellAt(position));
    }

    @Test
    @DisplayName("Should be able to set a value at a given position")
    void shouldBeAbleToSetAValueAtAGivenPosition() {
        int expectedValue = 5;
        Cell cell = new Column().cellAt(3);
        cell.setValue(expectedValue);

        assertEquals(expectedValue, cell.getValue());
    }

    @Nested
    @DisplayName("When the column contains a value")
    class WhenValueExists {
        private final int position = 3;
        private final int value = 5;
        // Subject
        private final Column column = new Column();

        @BeforeEach
        void createColumnWithOneValue() {
            column.changeCell().atPosition(position).toValue(value);
        }

        @Test
        @DisplayName("Should get the correct existing value")
        void getTheCorrectExistingValue() {
            assertEquals(value, column.cellAt(position).getValue());
        }

        @Nested
        @DisplayName("Should allow ")
        class ShouldAllow {
            private final int newValue = 7;

            @Test
            @DisplayName("a change to existing value")
            void shouldAllowAChangeToExistingValue() {
                column.changeCell().atPosition(position).toValue(newValue);
                assertEquals(newValue, column.cellAt(position).getValue());
            }

            @Test
            @DisplayName("new unique values")
            void shouldAllowNewUniqueValues() {
                int differentPosition = position + 1;
                column.changeCell().atPosition(differentPosition).toValue(newValue);
                assertEquals(newValue, column.cellAt(differentPosition).getValue());
            }

            @Test
            @DisplayName("removing a value from one cell and adding to another")
            void shouldAllowRemovingAValueAndReading() {
                int differentPosition = position + 1;

                column.changeCell().atPosition(position).toEmpty();
                column.changeCell().atPosition(differentPosition).toValue(value);

                assertEquals(value, column.cellAt(differentPosition).getValue());
            }
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("duplicate values")
            void shouldNotAllowDuplicateValues() {
                assertThrows(InvalidValueException.class,
                          () -> column.changeCell().atPosition(position + 1).toValue(value));
            }
        }
    }

    @Nested
    @DisplayName("When the column is full of values")
    class WhenColumnIsPopulated {
        private final List<Integer> expectedValues = List.of(2, 5, 3, 4, 9, 8, 1, 7, 6);
        // Subject
        private final Column column = new Column(2, 5, 3, 4, 9, 8, 1, 7, 6);

        @Test
        @DisplayName("Should be able to see all the values in a given column")
        void shouldBeAbleToSeeAllTheValuesInAGivenColumn() {
            assertEquals(2, column.cellAt(0).getValue());
            assertEquals(5, column.cellAt(1).getValue());
            assertEquals(3, column.cellAt(2).getValue());
            assertEquals(4, column.cellAt(3).getValue());
            assertEquals(9, column.cellAt(4).getValue());
            assertEquals(8, column.cellAt(5).getValue());
            assertEquals(1, column.cellAt(6).getValue());
            assertEquals(7, column.cellAt(7).getValue());
            assertEquals(6, column.cellAt(8).getValue());
        }

        @Test
        @DisplayName("Should be able to stream the values in a column")
        void shouldBeAbleToStreamTheValuesInAColumn() {
            List<Integer> columnValues = column.stream()
                                         .map(Cell::getValue)
                                         .collect(toUnmodifiableList());
            assertEquals(expectedValues, columnValues);
        }

        @Test
        @DisplayName("Should be able to iterate over the values in a column")
        void shouldBeAbleToIterateOverTheValuesInAColumn() {
            int index = 0;
            for (Cell cell : column) {
                assertEquals(expectedValues.get(index++), cell.getValue());
            }
        }

        @Test
        @DisplayName("Should be able to call forEach on a column")
        void shouldBeAbleToCallForEachOnAColumn() {
            AtomicInteger index = new AtomicInteger(0);
            column.forEach(cell -> assertEquals(expectedValues.get(index.getAndIncrement()), cell.getValue()));
            assertEquals(9, index.get());
        }
    }

}