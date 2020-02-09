package com.mechanitis.sudoku.data;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A Box is a section of the grid that is a 3x3 matrix. So unlike Row and Column has two dimensions, isn't just a
 * simple list. For this reason, we need to map from two-dimensional co-ordinates to the one dimension for Block.
 *
 *  1  2  3
 *  4  5  6
 *  7  8  9
 *
 * Value 1 is at position 0,0 value 2 is at 0,1 and value 9 is at 2,2. These are stored sequentially in a Block.
 */
public class Box implements Block {
    private final BlockImpl block;

    Box() {
        block = new BlockImpl();
    }

    Box(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
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

    public Mutator changeCell() {
        return new Mutator(block.changeCell());
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

    private int get1DIndexFromCoords(int rowIndex, int columnIndex) {
        return (3 * rowIndex) + columnIndex;
    }

    Cell cellAt(GridCoords gridCoords) {
        return cellAt(gridCoords.convertToBoxCoords());
    }

    Cell cellAt(BoxCoords boxCoords) {
        return block.cellAt(get1DIndexFromCoords(boxCoords.rowIndex(), boxCoords.columnIndex()));
    }

    class Mutator {
        // TODO: need to work out how to enforce ordering here, i.e. you have to select a cell before you can change it
        private BlockImpl.Mutator mutator;

        private Mutator(BlockImpl.Mutator mutator) {
            this.mutator = mutator;
        }

        Mutator atPosition(int rowIndex, int columnIndex) {
            // this row and column index is just for this box, not the whole grid
            // The box shouldn't know about its position in the grid.
            // But this is kinda sucky as all callers need to convert grid coords to box coords
            this.mutator = mutator.atPosition(get1DIndexFromCoords(rowIndex, columnIndex));
            return this;
        }

        void toValue(int value) {
            mutator.toValue(value);
        }

        void toEmpty() {
            mutator.toEmpty();
        }
    }

}
