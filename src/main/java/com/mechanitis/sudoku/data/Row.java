package com.mechanitis.sudoku.data;

class Row {
    private Cell[] cells = new Cell[9];

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

    public Cell cellAt(int position) {
        validatePosition(position);
        return cells[position];
    }

    public Mutator changeCell() {
        return new Mutator();
    }

    private void validatePosition(int position) {
        if (position < 0 || position > cells.length - 1) {
            throw new InvalidPositionException();
        }
    }

    class Builder {
        private int position;
        private int value;

        private Builder position(int position) {
            this.position = position;
            return this;
        }

        Builder hasValue(int value) {
            this.value = value;
            return this;
        }

        Cell build() {
            return new Cell(value);
        }
    }

    class Mutator {
        private Cell cell;

        Mutator atPosition(int position) {
            cell = cells[position];
            return this;
        }

        public Cell toValue(int value) {
            return cell;
        }
    }
}
