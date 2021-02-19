package com.mechanitis.sudoku;

import com.mechanitis.sudoku.data.Grid;

// there will be some overlap between the generator and the solver, since both are going to need to figure out if a
// particular number is allowed in a particular spot.
public class Generator {
    public Grid getPuzzle() {
        return new Grid();
    }
}
