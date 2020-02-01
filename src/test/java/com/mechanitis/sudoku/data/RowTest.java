package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RowTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        assertEquals(9, new Row().getLength());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int position) {
        Cell cell = new Row().cellAt(position);
        // this should be changed so we know we have the correct cell!
        assertTrue(cell.isEmpty());
    }

    @DisplayName("Should not be able to get a cell at any position not 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-1, 9, 7847843})
    void shouldNotBeAbleToGetACellAtInvalidPosition(int position) {
        Row row = new Row();
        assertThrows(InvalidPositionException.class, () -> row.cellAt(position));
    }

    @Test
    @DisplayName("Should be able to set a value at a given position")
    void shouldBeAbleToSetAValueAtAGivenPosition() {
        int expectedValue = 5;
        Cell cell = new Row().cellAt(3);
        cell.setValue(expectedValue);

        assertEquals(expectedValue, cell.getValue());
    }

    @Nested
    @DisplayName("When the row contains a value")
    class WhenValueExists {
        private final int position = 3;
        private final int value = 5;
        // Subject
        private final Row row = new Row();

        @BeforeEach
        void createRowWithOneValue() {
            row.changeCell().atPosition(position).toValue(value);
        }

        @Test
        @DisplayName("Should get the correct existing value")
        void getTheCorrectExistingValue() {
            assertEquals(value, row.cellAt(position).getValue());
        }

        @Nested
        @DisplayName("Should allow ")
        class ShouldAllow {
            private final int newValue = 7;

            @Test
            @DisplayName("a change to existing value")
            void shouldAllowAChangeToExistingValue() {
                row.changeCell().atPosition(position).toValue(newValue);
                assertEquals(newValue, row.cellAt(position).getValue());
            }

            @Test
            @DisplayName("new unique values")
            void shouldAllowNewUniqueValues() {
                int differentPosition = position + 1;
                row.changeCell().atPosition(differentPosition).toValue(newValue);
                assertEquals(newValue, row.cellAt(differentPosition).getValue());
            }

            @Test
            @DisplayName("removing a value from one cell and adding to another")
            void shouldAllowRemovingAValueAndReading() {
                int differentPosition = position + 1;

                row.changeCell().atPosition(position).toEmpty();
                row.changeCell().atPosition(differentPosition).toValue(value);

                assertEquals(value, row.cellAt(differentPosition).getValue());
            }
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("duplicate values")
            void shouldNotAllowDuplicateValues() {
                assertThrows(InvalidValueException.class, () -> row.changeCell().atPosition(position + 1).toValue(value));
            }
        }
    }

    @Nested
    @DisplayName("When the row is full of values")
    class WhenRowIsPopulated {
        private final List<Integer> expectedValues = List.of(2, 5, 3, 4, 9, 8, 1, 7, 6);
        // Subject
        private final Row row = new Row(2, 5, 3, 4, 9, 8, 1, 7, 6);

        @Test
        @DisplayName("Should be able to see all the values in a given row")
        void shouldBeAbleToSeeAllTheValuesInAGivenRow() {
            assertEquals(2, row.cellAt(0).getValue());
            assertEquals(5, row.cellAt(1).getValue());
            assertEquals(3, row.cellAt(2).getValue());
            assertEquals(4, row.cellAt(3).getValue());
            assertEquals(9, row.cellAt(4).getValue());
            assertEquals(8, row.cellAt(5).getValue());
            assertEquals(1, row.cellAt(6).getValue());
            assertEquals(7, row.cellAt(7).getValue());
            assertEquals(6, row.cellAt(8).getValue());
        }

        @Test
        @DisplayName("Should be able to stream the values in a row")
        void shouldBeAbleToStreamTheValuesInARow() {
            List<Integer> rowValues = row.stream()
                                         .map(Cell::getValue)
                                         .collect(toUnmodifiableList());
            assertEquals(expectedValues, rowValues);
        }

        @Test
        @DisplayName("Should be able to iterate over the values in a row")
        void shouldBeAbleToIterateOverTheValuesInARow() {
            int index = 0;
            for (Cell cell : row) {
                assertEquals(expectedValues.get(index++), cell.getValue());
            }
        }

        @Test
        @DisplayName("Should be able to call forEach on a row")
        void shouldBeAbleToCallForEachOnARow() {
            AtomicInteger index = new AtomicInteger(0);
            row.forEach(cell -> assertEquals(expectedValues.get(index.getAndIncrement()), cell.getValue()));
            assertEquals(9, index.get());
        }
    }

}