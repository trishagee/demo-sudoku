package com.mechanitis.sudoku;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

class GeneratorTest {
    // is the generated sudoku solvable?
    // does it have at least 17 items

    @Test
    @DisplayName("Should have at least 17 items in the generated puzzle")
    @Disabled("not implemented yet")
    void shouldHaveAtLeast17ItemsInTheGeneratedPuzzle() {
        Generator generator = new Generator();

        assertThat(generator.getPuzzle().getNumberOfFilledSquares(), greaterThan(17));
    }
}