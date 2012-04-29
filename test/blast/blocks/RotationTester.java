package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.Cell;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;
import blast.blocks.shared.exception.RotationImpossibleException;

public class RotationTester extends AbstractReflectionTestCase {

    public final void testSimpleConcentricRotation() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", "X"},
                {" ", " ", " "}
        });
        final Cell[][] initialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", " "}
        });
        final Cell[][] rotatedInitialMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedInitialMatrix);
    }

    public final void testSimpleConcentricRotationStab() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"o", "o", "o"},
                {"X", "X", "X"},
                {"o", "X", "o"},
        });
        final Cell[][] secondInitialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {"o", "X", "o"},
                {"X", "X", "o"},
                {"o", "X", "o"},
        });
        final Cell[][] secondRotatedInitialMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(secondInitialMatrixExpectation, secondRotatedInitialMatrix);
    }

    public final void testSimpleConcentricRotationNoStab() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", "X"},
                {" ", "X", " "},
        });
        final Cell[][] secondInitialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {"X", "X", " "},
                {" ", "X", " "},
        });
        final Cell[][] secondRotatedInitialMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(secondInitialMatrixExpectation, secondRotatedInitialMatrix);
    }

    public final void testSimpleConcentricRotationNoStabTwo() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {"X", "X", " "},
                {" ", "X", " "},
        });
        final Cell[][] secondInitialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {"X", "X", "X"},
                {" ", " ", " "},
        });
        final Cell[][] secondRotatedInitialMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(secondInitialMatrixExpectation, secondRotatedInitialMatrix);
    }

    public final void testSimpleConcentricRotationNoStabThree() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X"},
                {"X", "X", "X"},
                {" ", " ", " "},
        });
        final Cell[][] initialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", "X"},
        });
        final Cell[][] rotatedMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationNoStabFour() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", "X"},
        });
        final Cell[][] initialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", "X"},
                {"X", " ", " "},
        });
        final Cell[][] rotatedInitialMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedInitialMatrix);
    }

    public final void testExcentricRotationFailLeft() throws Exception {
        //Should catch RotationImpossibleException:
        final Cell[][] firstInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", " ", " "}
        });
        try {
            MatrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
            assert false; // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testExcentricRotationFailBottom() throws Exception {
        //Should catch RotationImpossibleException:
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {" ", " ", " "},
                {"X", "X", "X"}
        });
        try {
            MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
            assert false; //never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testSimpleExcentricRotationClockwise() throws Exception {
        final Cell[][] firstInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedClockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedClockwiseMatrix = MatrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseMatrixExpection, rotatedClockwiseMatrix);
    }

    public final void testSimpleExcentricRotationCounterClockwise() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testSimpleExcentricRotation() throws Exception {
        final Cell[][] thirdInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedClockwiseThirdMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedClockwiseThirdMatrix = MatrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedClockwiseThirdMatrixExpection, rotatedClockwiseThirdMatrix);
    }

    public final void testExcentricRotationCenterL() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", "X", "X", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationStartRowCenter() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", "X", " "},
                {" ", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testExcentricRotationStartRow() throws Exception {
        //TODO fix or use symmetry stabilizer
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {"X", " ", "o", " ", " "},
                {"X", " ", " ", " ", " "},
                {"X", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] matrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {"o", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "},
                {"X", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(matrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testExcentricRotationLeftT() throws Exception {
        //TODO fix or use symmetry stabilizer
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " ", " ", " "},
                {"X", "X", "o", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] matrixExpection = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "o", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = MatrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(matrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationWithSymmetryL() throws Exception {
        //"o" is an invisible block to keep the rotation in symmetry
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", "o", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "o", " ", " ", " "},
                {" ", " ", " ", "X", " "},
                {" ", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedCounterclockwiseMatrix = MatrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedCounterclockwiseMatrixExpection, rotatedCounterclockwiseMatrix);
    }

    public final void testExcentricRotationWithSymmetryT() throws Exception {
        final Cell[][] thirdInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", "o", " ", " "},
                {"X", "X", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] thirdMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", "X", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", " ", "o", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] thirdRotatedMatrix = MatrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(thirdMatrixExpection, thirdRotatedMatrix);
    }

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
