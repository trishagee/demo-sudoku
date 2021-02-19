package com.mechanitis.sudoku.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.mechanitis.sudoku.data.BoxPosition.*;
import static com.mechanitis.sudoku.data.ColumnIndex.column;
import static com.mechanitis.sudoku.data.RowIndex.row;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SuppressWarnings("unused") // used as a method source in test classes
public class GridBoxAndPositionData {
    static Stream<Arguments> gridCoordinatesBoxCoordinatesAndPosition() {
        return Stream.of(
                arguments(row(0), column(0), new BoxCoords(0, 0), 0, TopLeft),
                arguments(row(0), column(1), new BoxCoords(0, 1), 0, TopLeft),
                arguments(row(0), column(2), new BoxCoords(0, 2), 0, TopLeft),
                arguments(row(1), column(0), new BoxCoords(1, 0), 0, TopLeft),
                arguments(row(1), column(1), new BoxCoords(1, 1), 0, TopLeft),
                arguments(row(1), column(2), new BoxCoords(1, 2), 0, TopLeft),
                arguments(row(2), column(0), new BoxCoords(2, 0), 0, TopLeft),
                arguments(row(2), column(1), new BoxCoords(2, 1), 0, TopLeft),
                arguments(row(2), column(2), new BoxCoords(2, 2), 0, TopLeft),

                arguments(row(0), column(3), new BoxCoords(0, 0), 1, TopCentre),
                arguments(row(0), column(4), new BoxCoords(0, 1), 1, TopCentre),
                arguments(row(0), column(5), new BoxCoords(0, 2), 1, TopCentre),
                arguments(row(1), column(3), new BoxCoords(1, 0), 1, TopCentre),
                arguments(row(1), column(4), new BoxCoords(1, 1), 1, TopCentre),
                arguments(row(1), column(5), new BoxCoords(1, 2), 1, TopCentre),
                arguments(row(2), column(3), new BoxCoords(2, 0), 1, TopCentre),
                arguments(row(2), column(4), new BoxCoords(2, 1), 1, TopCentre),
                arguments(row(2), column(5), new BoxCoords(2, 2), 1, TopCentre),

                arguments(row(0), column(6), new BoxCoords(0, 0), 2, TopRight),
                arguments(row(0), column(7), new BoxCoords(0, 1), 2, TopRight),
                arguments(row(0), column(8), new BoxCoords(0, 2), 2, TopRight),
                arguments(row(1), column(6), new BoxCoords(1, 0), 2, TopRight),
                arguments(row(1), column(7), new BoxCoords(1, 1), 2, TopRight),
                arguments(row(1), column(8), new BoxCoords(1, 2), 2, TopRight),
                arguments(row(2), column(6), new BoxCoords(2, 0), 2, TopRight),
                arguments(row(2), column(7), new BoxCoords(2, 1), 2, TopRight),
                arguments(row(2), column(8), new BoxCoords(2, 2), 2, TopRight),

                arguments(row(3), column(0), new BoxCoords(0, 0), 3, CentreLeft),
                arguments(row(3), column(1), new BoxCoords(0, 1), 3, CentreLeft),
                arguments(row(3), column(2), new BoxCoords(0, 2), 3, CentreLeft),
                arguments(row(4), column(0), new BoxCoords(1, 0), 3, CentreLeft),
                arguments(row(4), column(1), new BoxCoords(1, 1), 3, CentreLeft),
                arguments(row(4), column(2), new BoxCoords(1, 2), 3, CentreLeft),
                arguments(row(5), column(0), new BoxCoords(2, 0), 3, CentreLeft),
                arguments(row(5), column(1), new BoxCoords(2, 1), 3, CentreLeft),
                arguments(row(5), column(2), new BoxCoords(2, 2), 3, CentreLeft),

                arguments(row(3), column(3), new BoxCoords(0, 0), 4, CentreCentre),
                arguments(row(3), column(4), new BoxCoords(0, 1), 4, CentreCentre),
                arguments(row(3), column(5), new BoxCoords(0, 2), 4, CentreCentre),
                arguments(row(4), column(3), new BoxCoords(1, 0), 4, CentreCentre),
                arguments(row(4), column(4), new BoxCoords(1, 1), 4, CentreCentre),
                arguments(row(4), column(5), new BoxCoords(1, 2), 4, CentreCentre),
                arguments(row(5), column(3), new BoxCoords(2, 0), 4, CentreCentre),
                arguments(row(5), column(4), new BoxCoords(2, 1), 4, CentreCentre),
                arguments(row(5), column(5), new BoxCoords(2, 2), 4, CentreCentre),

                arguments(row(3), column(6), new BoxCoords(0, 0), 5, CentreRight),
                arguments(row(3), column(7), new BoxCoords(0, 1), 5, CentreRight),
                arguments(row(3), column(8), new BoxCoords(0, 2), 5, CentreRight),
                arguments(row(4), column(6), new BoxCoords(1, 0), 5, CentreRight),
                arguments(row(4), column(7), new BoxCoords(1, 1), 5, CentreRight),
                arguments(row(4), column(8), new BoxCoords(1, 2), 5, CentreRight),
                arguments(row(5), column(6), new BoxCoords(2, 0), 5, CentreRight),
                arguments(row(5), column(7), new BoxCoords(2, 1), 5, CentreRight),
                arguments(row(5), column(8), new BoxCoords(2, 2), 5, CentreRight),

                arguments(row(6), column(0), new BoxCoords(0, 0), 6, BottomLeft),
                arguments(row(6), column(1), new BoxCoords(0, 1), 6, BottomLeft),
                arguments(row(6), column(2), new BoxCoords(0, 2), 6, BottomLeft),
                arguments(row(7), column(0), new BoxCoords(1, 0), 6, BottomLeft),
                arguments(row(7), column(1), new BoxCoords(1, 1), 6, BottomLeft),
                arguments(row(7), column(2), new BoxCoords(1, 2), 6, BottomLeft),
                arguments(row(8), column(0), new BoxCoords(2, 0), 6, BottomLeft),
                arguments(row(8), column(1), new BoxCoords(2, 1), 6, BottomLeft),
                arguments(row(8), column(2), new BoxCoords(2, 2), 6, BottomLeft),

                arguments(row(6), column(3), new BoxCoords(0, 0), 7, BottomCentre),
                arguments(row(6), column(4), new BoxCoords(0, 1), 7, BottomCentre),
                arguments(row(6), column(5), new BoxCoords(0, 2), 7, BottomCentre),
                arguments(row(7), column(3), new BoxCoords(1, 0), 7, BottomCentre),
                arguments(row(7), column(4), new BoxCoords(1, 1), 7, BottomCentre),
                arguments(row(7), column(5), new BoxCoords(1, 2), 7, BottomCentre),
                arguments(row(8), column(3), new BoxCoords(2, 0), 7, BottomCentre),
                arguments(row(8), column(4), new BoxCoords(2, 1), 7, BottomCentre),
                arguments(row(8), column(5), new BoxCoords(2, 2), 7, BottomCentre),

                arguments(row(6), column(6), new BoxCoords(0, 0), 8, BottomRight),
                arguments(row(6), column(7), new BoxCoords(0, 1), 8, BottomRight),
                arguments(row(6), column(8), new BoxCoords(0, 2), 8, BottomRight),
                arguments(row(7), column(6), new BoxCoords(1, 0), 8, BottomRight),
                arguments(row(7), column(7), new BoxCoords(1, 1), 8, BottomRight),
                arguments(row(7), column(8), new BoxCoords(1, 2), 8, BottomRight),
                arguments(row(8), column(6), new BoxCoords(2, 0), 8, BottomRight),
                arguments(row(8), column(7), new BoxCoords(2, 1), 8, BottomRight),
                arguments(row(8), column(8), new BoxCoords(2, 2), 8, BottomRight)
        );
    }
}
