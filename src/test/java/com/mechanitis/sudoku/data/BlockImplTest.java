package com.mechanitis.sudoku.data;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockImplTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        assertEquals(9, new BlockImpl().getSize());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int index) {
        Cell cell = new BlockImpl().cellAt(index);
        // this should be changed so we know we have the correct cell!
        assertTrue(cell.isEmpty());
    }

    @DisplayName("Should not be able to get a cell at any position not 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-1, 9, 7847843})
    void shouldNotBeAbleToGetACellAtInvalidPosition(int index) {
        Block block = new BlockImpl();
        assertThrows(InvalidPositionException.class, () -> block.cellAt(index));
    }

    @Test
    @DisplayName("Should not be able to change a cell value via the cell itself")
    void shouldNotBeAbleToChangeACellValueViaTheCellItself() {
        // sure, in theory you can change a cell value, but it won't change the
        // actual value in the block
        int expectedValue = 5;
        Block block = new BlockImpl();
        block.cellAt(3).setValue(expectedValue);

        assertNotEquals(expectedValue, block.cellAt(3).getValue());
    }

    @Test
    @DisplayName("Should be able to set a value at a given position")
    void shouldBeAbleToSetAValueAtAGivenPosition() {
        int expectedValue = 5;
        var block = new BlockImpl();
        block.changeCell().atPosition(3).toValue(expectedValue);

        assertEquals(expectedValue, block.cellAt(3).getValue());
    }


    @Test
    @DisplayName("Should not be able to create a block with duplicate values")
    void shouldNotBeAbleToCreateABlockWithDuplicateValues() {
        assertThrows(InvalidValueException.class, () -> new BlockImpl(4, 4, 6, 8, 1, 2, 9, 7, 5));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Should not be able to create a block with invalid values")
    @ValueSource(ints = {-1, 0, 11, Integer.MAX_VALUE})
    void shouldNotBeAbleToCreateABlockWithInvalidValues(int invalidValue) {
        assertThrows(InvalidValueException.class, () -> new BlockImpl(invalidValue, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Nested
    @DisplayName("When the block contains a value")
    class WhenValueExists {
        private final int index = 3;
        private final int value = 5;
        // Subject
        private final BlockImpl block = new BlockImpl();

        @BeforeEach
        void createBlockWithOneValue() {
            block.changeCell().atPosition(index).toValue(value);
        }

        @Nested
        @DisplayName("Should allow ")
        class ShouldAllow {
            private final int newValue = 7;

            @Test
            @DisplayName("a change to existing value")
            void shouldAllowAChangeToExistingValue() {
                block.changeCell().atPosition(index).toValue(newValue);
                assertEquals(newValue, block.cellAt(index).getValue());
            }

            @Test
            @DisplayName("new unique values")
            void shouldAllowNewUniqueValues() {
                int differentPosition = index + 1;
                block.changeCell().atPosition(differentPosition).toValue(newValue);
                assertEquals(newValue, block.cellAt(differentPosition).getValue());
            }

            @Test
            @DisplayName("removing a value from one cell and adding to another")
            void shouldAllowRemovingAValueAndReading() {
                int differentPosition = index + 1;

                block.changeCell().atPosition(index).toEmpty();
                block.changeCell().atPosition(differentPosition).toValue(value);

                assertEquals(value, block.cellAt(differentPosition).getValue());
            }
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("duplicate values")
            void shouldNotAllowDuplicateValues() {
                assertThrows(InvalidValueException.class,
                             () -> block.changeCell().atPosition(index + 1).toValue(value));
            }
        }
    }

    @Nested
    @DisplayName("When the block is full of values")
    class WhenBlockIsPopulated {
        private final List<Integer> expectedValues = List.of(2, 5, 3, 4, 9, 8, 1, 7, 6);
        // Subject
        private final BlockImpl block = new BlockImpl(2, 5, 3, 4, 9, 8, 1, 7, 6);

        @ParameterizedTest(name = "{0}: {1}")
        @DisplayName("Should be able to see all the values in a given block")
        @CsvSource({"0,2", "1,5", "2,3", "3,4", "4,9", "5,8", "6,1", "7,7", "8,6"})
        void shouldBeAbleToSeeAllTheValuesInAGivenBlock(int index, int expectedValue) {
            assertEquals(expectedValue, block.cellAt(index).getValue());
        }

        @Test
        @DisplayName("Should be able to stream the values in a block")
        void shouldBeAbleToStreamTheValuesInABlock() {
            List<Integer> blockValues = block.stream()
                                             .map(Cell::getValue)
                                             .collect(toUnmodifiableList());
            assertEquals(expectedValues, blockValues);
        }

        @Test
        @DisplayName("Should be able to iterate over the values in a block")
        void shouldBeAbleToIterateOverTheValuesInABlock() {
            int index = 0;
            for (Cell cell : block) {
                assertEquals(expectedValues.get(index++), cell.getValue());
            }
        }

        @Test
        @DisplayName("Should be able to call forEach on a block")
        void shouldBeAbleToCallForEachOnABlock() {
            AtomicInteger index = new AtomicInteger(0);
            block.forEach(cell -> assertEquals(expectedValues.get(index.getAndIncrement()), cell.getValue()));
            assertEquals(9, index.get());
        }

        @Test
        @DisplayName("Should allow a change to existing value")
        void shouldAllowAChangeToExistingValue() {
            // to do this in a valid way we have to clear the cell with the value we want to use
            int newValue = 4;
            int indexForTheValue4 = 3;
            Assumptions.assumeTrue(block.cellAt(indexForTheValue4).getValue() == newValue);
            block.changeCell().atPosition(indexForTheValue4).toEmpty(); // this should have held the value 4

            int indexForTheValue8 = 5;
            block.changeCell().atPosition(indexForTheValue8).toValue(newValue);
            assertEquals(newValue, block.cellAt(indexForTheValue8).getValue());
        }

        @Nested
        @DisplayName("Should not allow ")
        class ShouldNotAllow {
            @Test
            @DisplayName("duplicate values")
            void shouldNotAllowDuplicateValues() {
                assertThrows(InvalidValueException.class,
                             () -> block.changeCell().atPosition(2).toValue(6));
            }
        }
    }

}