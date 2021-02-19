package com.mechanitis.sudoku;

import com.mechanitis.sudoku.data.DuplicateValueException;
import com.mechanitis.sudoku.data.Grid;

import java.util.Random;

import static com.mechanitis.sudoku.data.ColumnIndex.column;
import static com.mechanitis.sudoku.data.RowIndex.row;

// there will be some overlap between the generator and the solver, since both are going to need to figure out if a
// particular number is allowed in a particular spot.
public class Generator {
    private static final int MIN_NUMBER_OF_VALUES = 17;
    private final Grid grid;

    public Generator() {
        grid = new Grid();
        populateWithRandomValues(MIN_NUMBER_OF_VALUES);
    }

    private void populateWithRandomValues(int minNumberOfValues) {
        Random random = new Random();
        while (grid.getNumberOfFilledSquares() <= minNumberOfValues) {
            try {
                grid.setCellValue(row(generateRandomIndex(random)), column(generateRandomIndex(random)), generateRandomValue(random));
            } catch (DuplicateValueException e) {
                // brute force, continue and try again
            }
        }
    }

    private int generateRandomValue(Random random) {
        return random.nextInt(9) + 1;
    }

    private int generateRandomIndex(Random random) {
        return random.nextInt(9);
    }

    public Grid getPuzzle() {
        return grid;
    }
}
