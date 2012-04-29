package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.Cell;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;
import blast.blocks.shared.exception.RotationImpossibleException;

public class RotationTester extends AbstractReflectionTestCase {
    private final MatrixRotator matrixRotator = new MatrixRotator();

    public final void testSimpleConcentricRotationHorizontal() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", "X"},
                {" ", " ", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationVertical() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", "X", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", "X"},
                {" ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationVerticalTwoClock() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", " ", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {"X", "X", " "},
                {" ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationVerticalTwoCounterClock() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", " "},
                {" ", "X", " "},
                {" ", " ", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationVerticalTwoCounterClockRight() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X"},
                {" ", " ", "X"},
                {" ", " ", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", "X", "X"},
                {" ", " ", " "},
                {" ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationVerticalTwoClockRight() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X"},
                {" ", " ", "X"},
                {" ", " ", " "}
        });
        final Cell[][] matrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {" ", "X", "X"},
                {" ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
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
        final Cell[][] secondRotatedInitialMatrix = matrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
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
        final Cell[][] secondRotatedInitialMatrix = matrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(secondInitialMatrixExpectation, secondRotatedInitialMatrix);
    }

    public final void testSimpleConcentricRotationBigT() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", "X"},
                {" ", "X", " "},
                {" ", "X", " "}
        });
        final Cell[][] secondInitialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X"},
                {"X", "X", "X"},
                {" ", " ", "X"},
        });
        final Cell[][] secondRotatedInitialMatrix = matrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
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
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
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
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedMatrix);
    }

    public final void testSimpleConcentricRotationBigL() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", "X", "X"},
        });
        final Cell[][] initialMatrixExpectation = Cell.createCellMatrix2D(new String[][] {
                {"X", "X", "X"},
                {"X", " ", " "},
                {"X", " ", " "},
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(initialMatrixExpectation, rotatedMatrix);
    }

    public final void testExcentricRotationFailLeft() throws Exception {
        final Cell[][] firstInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " "},
                {"X", " ", " "},
                {"X", " ", " "}
        });
        try {
            matrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
            assert false; // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testExcentricRotationFailBottom() throws Exception {
        final Cell[][] secondInitialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " "},
                {" ", " ", " "},
                {"X", "X", "X"}
        });
        try {
            matrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.CLOCKWISE);
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
        final Cell[][] rotatedClockwiseMatrix = matrixRotator.rotateExcentric(firstInitialMatrix, RotationDirection.CLOCKWISE);
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
        final Cell[][] rotatedCounterclockwiseMatrix = matrixRotator.rotateExcentric(secondInitialMatrix, RotationDirection.COUNTERCLOCKWISE);
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
        final Cell[][] rotatedClockwiseThirdMatrix = matrixRotator.rotateExcentric(thirdInitialMatrix, RotationDirection.CLOCKWISE);
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
                {" ", " ", " ", " ", " "},
                {"X", "X", "X", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationStartRowCenter() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "},
                {"X", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationStartRow() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {"X", "X", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        try {
            matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
            assert false; // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testExcentricRotationLeftT() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {"X", " ", " ", " ", " "},
                {"X", "X", " ", " ", " "},
                {"X", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        try {
            matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
            assert false; // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testExcentricRotationWithSymmetryL() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", " ", " ", " "},
                {" ", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", "X", " ", " "},
                {"X", "X", "X", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationFiveI() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {"X", "X", "X", "X", "X"},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationClockwiseFourI() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {"X", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.CLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
    }

    public final void testExcentricRotationCounterClockwiseFourI() throws Exception {
        final Cell[][] initialMatrix = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", "X", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrixExpection = Cell.createCellMatrix2D(new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {"X", "X", "X", "X", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        });
        final Cell[][] rotatedMatrix = matrixRotator.rotateExcentric(initialMatrix, RotationDirection.COUNTERCLOCKWISE);
        assertArrayEquals(rotatedMatrixExpection, rotatedMatrix);
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
