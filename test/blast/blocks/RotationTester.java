package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;
import blast.blocks.shared.exception.RotationImpossibleException;

public class RotationTester extends AbstractReflectionTestCase {

    public final void testSimpleConcentricRotation() throws Exception {
        final String[][] initialMatrix = {
                {" ", " ", " "},
                {"X", "X", "X"},
                {" ", " ", " "},
        };
        final String[][] initialMatrixExpectation = {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", " "},
        };
        final Object[][] rotatedInitialMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedInitialMatrix);

        final String[][] secondInitialMatrix = {
                {" ", " ", "o"},
                {"X", "X", "X"},
                {" ", "X", " "},
        };
        final String[][] secondInitialMatrixExpectation = {
                {" ", "X", " "},
                {"X", "X", " "},
                {" ", "X", "o"},
        };
        final Object[][] secondRotatedInitialMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(secondInitialMatrixExpectation, secondRotatedInitialMatrix);
    }

    public final void testExcentricRotationFail() throws Exception {
        //Should catch RotationImpossibleException:
        final String[][] firstInitialMatrix = {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", " ", " "}
        };
        try {
            MatrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
            assert false; // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }

        //Should catch RotationImpossibleException:
        final String[][] secondInitialMatrix = {
                {" ", " ", " "},
                {" ", " ", " "},
                {"X", "X", "X"}
        };
        try {
            MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
            assert false; //never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

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

        //more
        final String[][] thirdInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedClockwiseThirdMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedClockwiseThirdMatrix = MatrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseThirdMatrixExpection, rotatedClockwiseThirdMatrix);
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

        /*
        //TODO fix or use symmetry stabilizer
        final String[][] secondInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {"X", " ", " ", " ", " "}, //<-- Start row
                {"X", " ", " ", " ", " "},
                {"X", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedCounterclockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "}, //<-- Start row
                {"X", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        MatrixRotator.printMatrix(rotatedCounterclockwiseMatrix);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
        */

        ///////////////
        final String[][] thirdInitialMatrix = {
                {"X", " ", " ", " ", " "},
                {"X", "X", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] thirdMatrixExpection = {
                {"X", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] thirdRotatedMatrix = MatrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(thirdMatrixExpection, thirdRotatedMatrix);
    }

    public final void testExcentricRotationWithSymmetry() throws Exception {
        //counter-clockwise: "o" is an invisible block to keep the rotation in symmetry
        final String[][] secondInitialMatrix = {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", "o", " "}, //<-- Start row
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] rotatedCounterclockwiseMatrixExpection = {
                {" ", " ", " ", " ", " "},
                {" ", "o", " ", " ", " "},
                {" ", " ", " ", "X", " "}, //<-- Start row
                {" ", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);

        final String[][] thirdInitialMatrix = {
                {"X", " ", "o", " ", " "},
                {"X", "X", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final String[][] thirdMatrixExpection = {
                {"X", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", "o", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        final Object[][] thirdRotatedMatrix = MatrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(thirdMatrixExpection, thirdRotatedMatrix);
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
