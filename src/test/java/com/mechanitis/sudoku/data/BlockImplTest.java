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
import static org.junit.jupiter.api.Assertions.*;

class BlockImplTest {

    @Test
    @DisplayName("Should have exactly 9 cells")
    void shouldHaveExactly9Cells() {
        assertEquals(9, new BlockImpl().getSize());
    }

    @DisplayName("Should be able to get a cell at any position from 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldBeAbleToGetACellAtAnyValidPosition(int position) {
        Cell cell = new BlockImpl().cellAt(position);
        // this should be changed so we know we have the correct cell!
        assertTrue(cell.isEmpty());
    }

    @DisplayName("Should not be able to get a cell at any position not 0 to 8 inclusive")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-1, 9, 7847843})
    void shouldNotBeAbleToGetACellAtInvalidPosition(int position) {
        Block block = new BlockImpl();
        assertThrows(InvalidPositionException.class, () -> block.cellAt(position));
    }

    @Test
    @DisplayName("Should not be able to change a cell value")
    void shouldNotBeAbleToChangeACellValue() {
        // odd test / name - actually I mean sure, in theory you can change a cell value, but it won't change the
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

    @Nested
    @DisplayName("When the block contains a value")
    class WhenValueExists {
        private final int position = 3;
        private final int value = 5;
        // Subject
        private final BlockImpl block = new BlockImpl();

        @BeforeEach
        void createBlockWithOneValue() {
            block.changeCell().atPosition(position).toValue(value);
        }

        @Test
        @DisplayName("Should get the correct existing value")
        void getTheCorrectExistingValue() {
            assertEquals(value, block.cellAt(position).getValue());
        }

        @Nested
        @DisplayName("Should allow ")
        class ShouldAllow {
            private final int newValue = 7;

            @Test
            @DisplayName("a change to existing value")
            void shouldAllowAChangeToExistingValue() {
                block.changeCell().atPosition(position).toValue(newValue);
                assertEquals(newValue, block.cellAt(position).getValue());
            }

            @Test
            @DisplayName("new unique values")
            void shouldAllowNewUniqueValues() {
                int differentPosition = position + 1;
                block.changeCell().atPosition(differentPosition).toValue(newValue);
                assertEquals(newValue, block.cellAt(differentPosition).getValue());
            }

            @Test
            @DisplayName("removing a value from one cell and adding to another")
            void shouldAllowRemovingAValueAndReading() {
                int differentPosition = position + 1;

                block.changeCell().atPosition(position).toEmpty();
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
                assertThrows(InvalidValueException.class, () -> block.changeCell().atPosition(position + 1).toValue(value));
            }
        }
    }

    @Nested
    @DisplayName("When the block is full of values")
    class WhenBlockIsPopulated {
        private final List<Integer> expectedValues = List.of(2, 5, 3, 4, 9, 8, 1, 7, 6);
        // Subject
        private final Block block = new BlockImpl(2, 5, 3, 4, 9, 8, 1, 7, 6);

        @Test
        @DisplayName("Should be able to see all the values in a given block")
        void shouldBeAbleToSeeAllTheValuesInAGivenBlock() {
            assertEquals(2, block.cellAt(0).getValue());
            assertEquals(5, block.cellAt(1).getValue());
            assertEquals(3, block.cellAt(2).getValue());
            assertEquals(4, block.cellAt(3).getValue());
            assertEquals(9, block.cellAt(4).getValue());
            assertEquals(8, block.cellAt(5).getValue());
            assertEquals(1, block.cellAt(6).getValue());
            assertEquals(7, block.cellAt(7).getValue());
            assertEquals(6, block.cellAt(8).getValue());
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
    }

}