package com.mechanitis.sudoku.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Block implements Iterable<Cell> {
    private final Cell[] cells = new Cell[9];
    // needs to be kept in sync with the array of values
    private final Set<Integer> cellValues = new HashSet<>();

    Block() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    Block(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
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

    int getLength() {
        return cells.length;
    }

    Cell cellAt(int position) {
        validatePosition(position);
        return Cell.copy(cells[position]);
    }

    Block.Mutator changeCell() {
        return new Block.Mutator();
    }

    Stream<Cell> stream() {
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

        Block.Mutator atPosition(int position) {
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
