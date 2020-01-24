package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        Row row = new Row();
        assertEquals(9, row.getLength());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int position) {
        Row row = new Row();
        Cell cell = row.cellAt(position);
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
        // given
        int expectedValue = 5;
        Row row = new Row();

        // when
        Cell cell = row.cellAt(3);
        cell.setValue(expectedValue);

        // then
        assertEquals(expectedValue, cell.getValue());
    }

    @Nested
    @DisplayName("When the row contains some values")
    class WhenValuesExist {
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
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("duplicate values")
            void shouldNotAllowDuplicateValues() {
                assertThrows(InvalidValueException.class, () -> {
                    row.changeCell().atPosition(position + 1).toValue(value);
                });
            }
        }
    }

}