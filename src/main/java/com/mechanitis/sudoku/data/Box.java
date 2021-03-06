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
        block = new BlockImpl(i0, i1, i2,
                              i3, i4, i5,
                              i6, i7, i8);
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

    Cell cellAt(RowIndex row, ColumnIndex column) {
        return cellAt(BoxCoords.fromIndices(row, column));
    }

    Cell cellAt(BoxCoords boxCoords) {
        return block.cellAt(get1DIndexFromCoords(boxCoords.row(), boxCoords.column()));
    }

    BlockImpl.Mutator changeCell(BoxCoords boxCoords) {
        return block.changeCell(get1DIndexFromCoords(boxCoords.row(), boxCoords.column()));
    }

    private int get1DIndexFromCoords(int rowIndex, int columnIndex) {
        return (3 * rowIndex) + columnIndex;
    }

}
