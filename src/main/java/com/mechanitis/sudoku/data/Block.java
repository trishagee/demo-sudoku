package com.mechanitis.sudoku.data;

import java.util.stream.Stream;

public interface Block extends Iterable<Cell> {
    int getSize();

    Cell cellAt(int position);

    Stream<Cell> stream();

}
