package com.mechanitis.sudoku;

import com.mechanitis.sudoku.data.DuplicateValueException;
import com.mechanitis.sudoku.data.Grid;
import com.mechanitis.sudoku.data.GridCoords;

import java.util.Random;

// there will be some overlap between the generator and the solver, since both are going to need to figure out if a
// particular number is allowed in a particular spot.
public class Generator {
    private Grid grid;

    public Generator() {
        grid = new Grid();

        for (int i = 0; i <= 17; i++) {
            Random random = new Random();
            try {
                grid.changeCell(new GridCoords(random.nextInt(9),
                                               random.nextInt(9)))
                    .toValue(random.nextInt(9) + 1);
            } catch (DuplicateValueException e) {
                //brute force: try again
            }
        }
    }

    public Grid getPuzzle() {
        return grid;
    }
}
