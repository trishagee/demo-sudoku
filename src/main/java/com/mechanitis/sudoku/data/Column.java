package com.mechanitis.sudoku.data;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Column implements Iterable<Cell> {
    private Cell[] cells = new Cell[9];

    public Column() {

    }

    public Column(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {

    }

    public int getLength() {
        return 0;
    }

    public Cell cellAt(int position) {
        return null;
    }

    public Mutator changeCell() {
        return new Mutator();
    }

    public Stream<Cell> stream() {
        return null;
    }

    @Override
    public Iterator<Cell> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {

    }

    @Override
    public Spliterator<Cell> spliterator() {
        return null;
    }

    static class Mutator {
        public Mutator atPosition(int position) {
            return null;
        }

        public void toValue(int value) {

        }

        public void toEmpty() {

        }
    }
}
