package com.mechanitis.sudoku.data;

import java.util.HashSet;
import java.util.Set;

class Row {
    private Cell[] cells = new Cell[9];
    // needs to be kept in sync with the array of values
    private Set<Integer> cellValues = new HashSet<>();

    public Row() {
        // profiling? iteration vs streams?
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
        //Cells are immutable so need to be replaced in this structure
        //But... then we might mess up the useful use of references...
        //so let's make them (limited) mutable
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
