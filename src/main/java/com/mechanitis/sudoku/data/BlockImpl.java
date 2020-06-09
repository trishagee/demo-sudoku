package com.mechanitis.sudoku.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        List.of(i0, i1, i2, i3, i4, i5, i6, i7, i8)
            .forEach(this::checkForDuplicatesAndTrackAddedValues);
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
    public Cell cellAt(int position) {
        validatePosition(position);
        return Cell.copy(cells[position]);
    }

    Mutator changeCell(int position) {
        validatePosition(position);
        return new Mutator(position);
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

    private void checkForDuplicatesAndTrackAddedValues(int value) {
        if (!cellValues.add(value)) {
            throw new DuplicateValueException(value);
        }
    }

    private void validatePosition(int position) {
        if (position < 0 || position > cells.length - 1) {
            throw new InvalidPositionException();
        }
    }

    /**
     * This Mutator a) provides a nice API and b) protects against mutability issue by controlling access through the
     * row/column/box.
     */
    class Mutator {
        private final Cell cell;

        private Mutator(int position) {
            cell = cells[position];
        }

        void toValue(int value) {
            if (cellValues.add(value)) {
                cell.setValue(value);
            } else {
                throw new DuplicateValueException(value);
            }
        }

        void toEmpty() {
            cellValues.remove(cell.getValue());
            cell.setEmpty();
        }
    }
}
