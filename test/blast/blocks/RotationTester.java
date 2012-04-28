package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;

public class RotationTester extends AbstractReflectionTestCase {

    public final void testSimpleExcentricRotation() throws Exception {
        //clockwise:
        final String[][] firstInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedClockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedClockwiseMatrix = MatrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseMatrixExpection, rotatedClockwiseMatrix);

        //counter-clockwise:
        final String[][] secondInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedCounterclockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testExcentricRotation() throws Exception {
        //clockwise:
        final String[][] firstInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedClockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", "X", "X", "X", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedClockwiseMatrix = MatrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseMatrixExpection, rotatedClockwiseMatrix);

        //counter-clockwise:
        final String[][] secondInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedCounterclockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", "X", " "},
                {" ", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testConcentricRotationThree() throws Exception {
        final String[][] initialMatrix = {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", "X", " "},
        };

        //clockwise:
        final String[][] rotatedClockwiseMatrixExpection = {
                {"X", "X", "X"},
                {"X", " ", " "},
                {" ", " ", " "}
        };

        final Object[][] rotatedClockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseMatrixExpection, rotatedClockwiseMatrix);

        //counter-clockwise
        final Object[][] rotatedCounterclockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        final String[][] rotatedCounterClockwiseMatrixExpection = {
                {" ", " ", " "},
                {" ", " ", "X"},
                {"X", "X", "X"}
        };
        assertArrayEquals(rotatedCounterClockwiseMatrixExpection, rotatedCounterclockwiseMatrix);

        //double rotation
        final Object[][] rotateConcentricdTwiceMatrixC = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Object[][] rotateConcentricdTwiceMatrixCc = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        final String[][] rotateConcentricdTwiveMatrixExpection = {
                {" ", "X", "X"},
                {" ", " ", "X"},
                {" ", " ", "X"}
        };
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixC);
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixCc);

        //rotateConcentric four times
        final Object[][] rotateConcentricdFourTimesC = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Object[][] rotateConcentricdFourTimesCc = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesC);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesCc);
    }

    public final void testConcentricRotationFour() throws Exception {
        final String[][] initialMatrix = {
                {"X", " ", " ", " "},
                {"X", " ", " ", " "},
                {"X", "X", " ", " "},
                {" ", " ", " ", " "}
        };

        //clockwise:
        final String[][] rotateConcentricdClockwiseMatrixExpection = {
                {" ", "X", "X", "X"},
                {" ", "X", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "}
        };
        final Object[][] rotateConcentricdClockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotateConcentricdClockwiseMatrixExpection, rotateConcentricdClockwiseMatrix);

        //counter-clockwise
        final Object[][] rotateConcentricdCounterclockwiseMatrix = invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        final String[][] rotateConcentricdCounterClockwiseMatrixExpection = {
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", "X", " "},
                {"X", "X", "X", " "}
        };
        assertArrayEquals(rotateConcentricdCounterClockwiseMatrixExpection, rotateConcentricdCounterclockwiseMatrix);

        //double rotation
        final Object[][] rotateConcentricdTwiceMatrixC = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Object[][] rotateConcentricdTwiceMatrixCc = invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        final String[][] rotateConcentricdTwiveMatrixExpection = {
                {" ", " ", " ", " "},
                {" ", " ", "X", "X"},
                {" ", " ", " ", "X"},
                {" ", " ", " ", "X"}
        };
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixC);
        assertArrayEquals(rotateConcentricdTwiveMatrixExpection, rotateConcentricdTwiceMatrixCc);

        //rotateConcentric four times
        final Object[][] rotateConcentricdFourTimesC = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE), RotationDirection.CLOCKWISE);
        final Object[][] rotateConcentricdFourTimesCc = invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(invokeRotateConcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE), RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesC);
        assertArrayEquals(initialMatrix, rotateConcentricdFourTimesCc);
    }
}
