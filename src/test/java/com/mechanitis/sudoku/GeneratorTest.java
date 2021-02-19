package com.mechanitis.sudoku;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

class GeneratorTest {
    // is the generated sudoku solvable?
    // does it have at least 17 items

    @Test
    @DisplayName("Should have at least 17 items")
    void shouldHaveAtLeast17Items() {
        Generator generator = new Generator();
        assertThat(generator.getPuzzle().getNumberOfFilledSquares(), greaterThanOrEqualTo(17));
    }
}