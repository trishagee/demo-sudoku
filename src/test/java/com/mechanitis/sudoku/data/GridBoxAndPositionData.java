package com.mechanitis.sudoku.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.mechanitis.sudoku.data.BoxPosition.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SuppressWarnings("unused") // used as a method source in test classes
public class GridBoxAndPositionData {
    static Stream<Arguments> gridCoordinatesBoxCoordinatesAndPosition() {
        return Stream.of(
                arguments(new GridCoords(0, 0), new BoxCoords(0, 0), 0, TopLeft),
                arguments(new GridCoords(0, 1), new BoxCoords(0, 1), 0, TopLeft),
                arguments(new GridCoords(0, 2), new BoxCoords(0, 2), 0, TopLeft),
                arguments(new GridCoords(1, 0), new BoxCoords(1, 0), 0, TopLeft),
                arguments(new GridCoords(1, 1), new BoxCoords(1, 1), 0, TopLeft),
                arguments(new GridCoords(1, 2), new BoxCoords(1, 2), 0, TopLeft),
                arguments(new GridCoords(2, 0), new BoxCoords(2, 0), 0, TopLeft),
                arguments(new GridCoords(2, 1), new BoxCoords(2, 1), 0, TopLeft),
                arguments(new GridCoords(2, 2), new BoxCoords(2, 2), 0, TopLeft),

                arguments(new GridCoords(0, 3), new BoxCoords(0, 0), 1, TopCentre),
                arguments(new GridCoords(0, 4), new BoxCoords(0, 1), 1, TopCentre),
                arguments(new GridCoords(0, 5), new BoxCoords(0, 2), 1, TopCentre),
                arguments(new GridCoords(1, 3), new BoxCoords(1, 0), 1, TopCentre),
                arguments(new GridCoords(1, 4), new BoxCoords(1, 1), 1, TopCentre),
                arguments(new GridCoords(1, 5), new BoxCoords(1, 2), 1, TopCentre),
                arguments(new GridCoords(2, 3), new BoxCoords(2, 0), 1, TopCentre),
                arguments(new GridCoords(2, 4), new BoxCoords(2, 1), 1, TopCentre),
                arguments(new GridCoords(2, 5), new BoxCoords(2, 2), 1, TopCentre),

                arguments(new GridCoords(0, 6), new BoxCoords(0, 0), 2, TopRight),
                arguments(new GridCoords(0, 7), new BoxCoords(0, 1), 2, TopRight),
                arguments(new GridCoords(0, 8), new BoxCoords(0, 2), 2, TopRight),
                arguments(new GridCoords(1, 6), new BoxCoords(1, 0), 2, TopRight),
                arguments(new GridCoords(1, 7), new BoxCoords(1, 1), 2, TopRight),
                arguments(new GridCoords(1, 8), new BoxCoords(1, 2), 2, TopRight),
                arguments(new GridCoords(2, 6), new BoxCoords(2, 0), 2, TopRight),
                arguments(new GridCoords(2, 7), new BoxCoords(2, 1), 2, TopRight),
                arguments(new GridCoords(2, 8), new BoxCoords(2, 2), 2, TopRight),

                arguments(new GridCoords(3, 0), new BoxCoords(0, 0), 3, CentreLeft),
                arguments(new GridCoords(3, 1), new BoxCoords(0, 1), 3, CentreLeft),
                arguments(new GridCoords(3, 2), new BoxCoords(0, 2), 3, CentreLeft),
                arguments(new GridCoords(4, 0), new BoxCoords(1, 0), 3, CentreLeft),
                arguments(new GridCoords(4, 1), new BoxCoords(1, 1), 3, CentreLeft),
                arguments(new GridCoords(4, 2), new BoxCoords(1, 2), 3, CentreLeft),
                arguments(new GridCoords(5, 0), new BoxCoords(2, 0), 3, CentreLeft),
                arguments(new GridCoords(5, 1), new BoxCoords(2, 1), 3, CentreLeft),
                arguments(new GridCoords(5, 2), new BoxCoords(2, 2), 3, CentreLeft),

                arguments(new GridCoords(3, 3), new BoxCoords(0, 0), 4, CentreCentre),
                arguments(new GridCoords(3, 4), new BoxCoords(0, 1), 4, CentreCentre),
                arguments(new GridCoords(3, 5), new BoxCoords(0, 2), 4, CentreCentre),
                arguments(new GridCoords(4, 3), new BoxCoords(1, 0), 4, CentreCentre),
                arguments(new GridCoords(4, 4), new BoxCoords(1, 1), 4, CentreCentre),
                arguments(new GridCoords(4, 5), new BoxCoords(1, 2), 4, CentreCentre),
                arguments(new GridCoords(5, 3), new BoxCoords(2, 0), 4, CentreCentre),
                arguments(new GridCoords(5, 4), new BoxCoords(2, 1), 4, CentreCentre),
                arguments(new GridCoords(5, 5), new BoxCoords(2, 2), 4, CentreCentre),

                arguments(new GridCoords(3, 6), new BoxCoords(0, 0), 5, CentreRight),
                arguments(new GridCoords(3, 7), new BoxCoords(0, 1), 5, CentreRight),
                arguments(new GridCoords(3, 8), new BoxCoords(0, 2), 5, CentreRight),
                arguments(new GridCoords(4, 6), new BoxCoords(1, 0), 5, CentreRight),
                arguments(new GridCoords(4, 7), new BoxCoords(1, 1), 5, CentreRight),
                arguments(new GridCoords(4, 8), new BoxCoords(1, 2), 5, CentreRight),
                arguments(new GridCoords(5, 6), new BoxCoords(2, 0), 5, CentreRight),
                arguments(new GridCoords(5, 7), new BoxCoords(2, 1), 5, CentreRight),
                arguments(new GridCoords(5, 8), new BoxCoords(2, 2), 5, CentreRight),

                arguments(new GridCoords(6, 0), new BoxCoords(0, 0), 6, BottomLeft),
                arguments(new GridCoords(6, 1), new BoxCoords(0, 1), 6, BottomLeft),
                arguments(new GridCoords(6, 2), new BoxCoords(0, 2), 6, BottomLeft),
                arguments(new GridCoords(7, 0), new BoxCoords(1, 0), 6, BottomLeft),
                arguments(new GridCoords(7, 1), new BoxCoords(1, 1), 6, BottomLeft),
                arguments(new GridCoords(7, 2), new BoxCoords(1, 2), 6, BottomLeft),
                arguments(new GridCoords(8, 0), new BoxCoords(2, 0), 6, BottomLeft),
                arguments(new GridCoords(8, 1), new BoxCoords(2, 1), 6, BottomLeft),
                arguments(new GridCoords(8, 2), new BoxCoords(2, 2), 6, BottomLeft),

                arguments(new GridCoords(6, 3), new BoxCoords(0, 0), 7, BottomCentre),
                arguments(new GridCoords(6, 4), new BoxCoords(0, 1), 7, BottomCentre),
                arguments(new GridCoords(6, 5), new BoxCoords(0, 2), 7, BottomCentre),
                arguments(new GridCoords(7, 3), new BoxCoords(1, 0), 7, BottomCentre),
                arguments(new GridCoords(7, 4), new BoxCoords(1, 1), 7, BottomCentre),
                arguments(new GridCoords(7, 5), new BoxCoords(1, 2), 7, BottomCentre),
                arguments(new GridCoords(8, 3), new BoxCoords(2, 0), 7, BottomCentre),
                arguments(new GridCoords(8, 4), new BoxCoords(2, 1), 7, BottomCentre),
                arguments(new GridCoords(8, 5), new BoxCoords(2, 2), 7, BottomCentre),

                arguments(new GridCoords(6, 6), new BoxCoords(0, 0), 8, BottomRight),
                arguments(new GridCoords(6, 7), new BoxCoords(0, 1), 8, BottomRight),
                arguments(new GridCoords(6, 8), new BoxCoords(0, 2), 8, BottomRight),
                arguments(new GridCoords(7, 6), new BoxCoords(1, 0), 8, BottomRight),
                arguments(new GridCoords(7, 7), new BoxCoords(1, 1), 8, BottomRight),
                arguments(new GridCoords(7, 8), new BoxCoords(1, 2), 8, BottomRight),
                arguments(new GridCoords(8, 6), new BoxCoords(2, 0), 8, BottomRight),
                arguments(new GridCoords(8, 7), new BoxCoords(2, 1), 8, BottomRight),
                arguments(new GridCoords(8, 8), new BoxCoords(2, 2), 8, BottomRight)
        );
    }
}
