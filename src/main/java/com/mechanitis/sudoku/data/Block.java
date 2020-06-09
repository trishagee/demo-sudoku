package com.mechanitis.sudoku.data;

import java.util.stream.Stream;

public interface Block extends Iterable<Cell> {
    /**
     * Use this method to get a specific Cell in the Block (Row/Column/Box). This should be an immutable value.
     *
     * @param position Zero-based index for the cell
     * @return a copy of the Cell at this position
     */
    Cell cellAt(int position);

    Stream<Cell> stream();

}
