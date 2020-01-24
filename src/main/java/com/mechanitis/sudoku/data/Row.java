package com.mechanitis.sudoku.data;

class Row {
    private Cell[] cells = new Cell[9];

    public Row() {
        // profiling? iteration vs streams?
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    //package level util, currently for testing.
    int getLength() {
        return cells.length;
    }

    public Cell cellAt(int position) {
        validatePosition(position);
        return cells[position];
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
}
