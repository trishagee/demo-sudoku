package com.mechanitis.sudoku.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;

public class BlockImpl implements Block {
    private final Cell[] cells = new Cell[9];
    // needs to be kept in sync with the array of values
    private final Set<Integer> cellValues = new HashSet<>(9);

    BlockImpl() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    BlockImpl(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        cellValues.addAll(asList(i0, i1, i2, i3, i4, i5, i6, i7, i8));
        if (cellValues.size() < 9) {
            // if, after adding all 9 arguments into the set there are not actually nine elements in the set, there's
            // at least one duplicate
            throw new InvalidValueException("Duplicate values are not allowed");
        }
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

    @Override
    public int getSize() {
        return cells.length;
    }

    @Override
    public Cell cellAt(int position) {
        validatePosition(position);
        return Cell.copy(cells[position]);
    }

    public Mutator changeCell() {
        return new Mutator();
    }

    @Override
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
        // TODO: need to work out how to enforce ordering here, i.e. you have to select a cell before you can change it
        private Cell cell;

        Mutator atPosition(int position) {
            cell = cells[position];
            return this;
        }

        void toValue(int value) {
            if (cellValues.add(value)) {
                cell.setValue(value);
            } else {
                throw new InvalidValueException("This is a duplicate");
            }
        }

        void toEmpty() {
            cellValues.remove(cell.getValue());
            cell.setEmpty();
        }
    }
}
