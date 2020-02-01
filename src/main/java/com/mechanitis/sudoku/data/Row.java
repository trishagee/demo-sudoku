package com.mechanitis.sudoku.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Row implements Iterable<Cell> {
    private final Cell[] cells = new Cell[9];
    // needs to be kept in sync with the array of values
    private final Set<Integer> cellValues = new HashSet<>();

    Row() {
        // profiling? iteration vs streams?
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
        //Cells are immutable so need to be replaced in this structure
        //But... then we might mess up the useful use of references...
        //so let's make them (limited) mutable
    }

    Row(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        cells[0] = new Cell(i0);
        cells[1] = new Cell(i1);
        cells[2] = new Cell(i2);
        cells[3] = new Cell(i3);
        cells[4] = new Cell(i4);
        cells[5] = new Cell(i5);
        cells[6] = new Cell(i6);
        cells[7] = new Cell(i7);
        cells[8] = new Cell(i8);
    }

    //package level util, currently for testing.
    int getLength() {
        return cells.length;
    }

    /**
     * @param position the zero-indexed position of the desired Cell
     * @return a copy of the Cell at this position
     */
    public Cell cellAt(int position) {
        validatePosition(position);
        return Cell.copy(cells[position]);
    }

    public Mutator changeCell() {
        return new Mutator();
    }


    public Stream<Cell> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<Cell> iterator() {
        return stream().iterator();
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        stream().forEach(action);
    }

    @Override
    public Spliterator<Cell> spliterator() {
        return Spliterators.spliterator(cells, Spliterator.ORDERED);
    }

    private void validatePosition(int position) {
        if (position < 0 || position > cells.length - 1) {
            throw new InvalidPositionException();
        }
    }

    class Mutator {
        private Cell cell;

        Mutator atPosition(int position) {
            cell = cells[position];
            return this;
        }

        public void toValue(int value) {
            if (cellValues.add(value)) {
                cell.setValue(value);
            } else {
                throw new InvalidValueException("This is a duplicate");
            }
        }
    }
}
