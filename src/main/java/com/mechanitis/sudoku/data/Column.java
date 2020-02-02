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

    Column(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        block = new BlockImpl(i0, i1, i2, i3, i4, i5, i6, i7, i8);
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

    public BlockImpl.Mutator changeCell() {
        return block.changeCell();
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
}
