package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.Cell;
import blast.blocks.shared.MatrixRotator.RotationDirection;

public class RotationTester2D extends AbstractReflectionTestCase {

    public final void testConcentricRotationThreeL() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", "X", " "},
        });

        //clockwise:
        final Cell[][] rotatedClockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", "X"},
                {"X", " ", " "},
                {" ", " ", " "}
        });

        final Cell[][] rotatedClockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseMatrixExpection, rotatedClockwiseMatrix);

        //counter-clockwise
        final Cell[][] rotatedCounterclockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        final Cell[][] rotatedCounterClockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {" ", " ", "X"},
                {"X", "X", "X"}
        });
        assertArrayEquals(rotatedCounterClockwiseMatrixExpection, rotatedCounterclockwiseMatrix);

        //double rotation
        final Cell[][] rotateConcentricdTwiceMatrixC = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Cell[][] rotateConcentricdTwiceMatrixCc = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        final Cell[][] rotateConcentricdTwiveMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", "X"},
                {" ", " ", "X"},
                {" ", " ", "X"}
        });
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixC);
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixCc);

        //rotateConcentric four times
        final Cell[][] rotateConcentricdFourTimesC = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Cell[][] rotateConcentricdFourTimesCc = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesC);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesCc);
    }

    public final void testConcentricRotationFourL() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " ", " "},
                {"X", " ", " ", " "},
                {"X", "X", " ", " "},
                {" ", " ", " ", " "}
        });

        //clockwise:
        final Cell[][] rotateConcentricdClockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", "X", "X"},
                {" ", "X", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "}
        });
        final Cell[][] rotateConcentricdClockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotateConcentricdClockwiseMatrixExpection, rotateConcentricdClockwiseMatrix);

        //counter-clockwise
        final Cell[][] rotateConcentricdCounterclockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        final Cell[][] rotateConcentricdCounterClockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", "X", " "},
                {"X", "X", "X", " "}
        });
        assertArrayEquals(rotateConcentricdCounterClockwiseMatrixExpection, rotateConcentricdCounterclockwiseMatrix);

        //double rotation
        final Cell[][] rotateConcentricdTwiceMatrixC = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Cell[][] rotateConcentricdTwiceMatrixCc = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        final Cell[][] rotateConcentricdTwiveMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " "},
                {" ", " ", "X", "X"},
                {" ", " ", " ", "X"},
                {" ", " ", " ", "X"}
        });
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixC);
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixCc);

        //rotateConcentric four times
        final Cell[][] rotateConcentricdFourTimesC = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Cell[][] rotateConcentricdFourTimesCc = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesC);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesCc);
    }
}
