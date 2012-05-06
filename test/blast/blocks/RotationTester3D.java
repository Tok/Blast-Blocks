package blast.blocks;

import static org.junit.Assert.assertArrayEquals;
import blast.blocks.shared.Cell;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.enums.RotationType;
import blast.blocks.shared.exception.RotationImpossibleException;

public class RotationTester3D extends AbstractReflectionTestCase {
    private final MatrixRotator matrixRotator = new MatrixRotator();

    public final void testDualcubePlusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "+", "X"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_Z);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testDualcubeMinusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {"X", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_Z);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testDualcubePlusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "X", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testDualcubeMinusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", "X", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testDualcubePlusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testDualcubeMinusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testTricubeRightPlusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        try {
            matrixRotator.rotate(initialMatrix, RotationType.PLUS_Z);
            assertTrue(false); // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testTricubeRightMinusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        try {
            matrixRotator.rotate(initialMatrix, RotationType.MINUS_Z);
            assertTrue(false); // never reached.
        } catch (final RotationImpossibleException rie) {
            assertNotNull(rie);
        }
    }

    public final void testTricubeRightPlusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", "X"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", "+"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", "X"},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testTricubeRightMinusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", "X"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", "+"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", "X"},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testTricubeRightPlusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testTricubeRightMinusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {" ", " ", "+"},
                 {" ", " ", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubePlusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {"X", "+", "X"},
                 {"X", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_Z);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubeMinusZ() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", " ", "X"},
                 {"X", "+", "X"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_Z);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubePlusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", "X", "X"},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "X", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubeMinusY() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", "X", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "+", " "},
                 {" ", " ", " "}},
                {{" ", " ", " "},
                 {" ", "X", "X"},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_Y);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubePlusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", "X", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.PLUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

    public final void testLTetracubeMinusX() throws Exception {
        final Cell[][][] initialMatrix = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", "X"}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] matrixExpectation = Cell.createCellMatrix3D(new String[][][] {
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", "X", " "}},
                {{" ", "X", " "},
                 {" ", "+", " "},
                 {" ", "X", " "}},
                {{" ", " ", " "},
                 {" ", " ", " "},
                 {" ", " ", " "}}
        });
        final Cell[][][] rotatedMatrix = matrixRotator.rotate(initialMatrix, RotationType.MINUS_X);
        assertArrayEquals(matrixExpectation, rotatedMatrix);
    }

}
