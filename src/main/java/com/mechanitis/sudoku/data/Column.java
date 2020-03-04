package com.mechanitis.sudoku.data;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Column implements Block {
    private final BlockImpl block;

    Column() {
        block = new BlockImpl();
    }

    //currently for testing.
    public int getSize() {
        return block.getSize();
    }

    /**
     * @param position the zero-indexed position of the desired Cell
     * @return a copy of the Cell at this position
     */
    public Cell cellAt(int position) {
        return block.cellAt(position);
    }

    public Stream<Cell> stream() {
        return block.stream();
    }

    @Override
    public Iterator<Cell> iterator() {
        return block.iterator();
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        block.forEach(action);
    }

    @Override
    public Spliterator<Cell> spliterator() {
        return block.spliterator();
    }

    BlockImpl.Mutator changeCell(int position) {
        return block.changeCell(position);
    }
}
