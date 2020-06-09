package com.mechanitis.sudoku.data;

import java.util.stream.Stream;

public interface Block extends Iterable<Cell> {
    Stream<Cell> stream();
}
