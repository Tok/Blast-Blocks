package blast.blocks.shared;

import java.util.Arrays;
import blast.blocks.shared.exception.RotationImpossibleException;

public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    private MatrixRotator() {
    }

    public static Object[][] rotateExcentric(final Object[][] matrix, final RotationDirection rd) {
        //determine parts to rotate
        int rotateFromRow = matrix.length;
        int rotateToRow = 0;
        int rotateFromColumn = matrix[0].length;
        int rotateToColumn = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (!matrix[row][col].equals(" ")) { //has Element
                    if (row < rotateFromRow) {
                        rotateFromRow = row;
                    }
                    if (col < rotateFromColumn) {
                        rotateFromColumn = col;
                    }
                    if (row > rotateToRow) {
                        rotateToRow = row;
                    }
                    if (col > rotateToColumn) {
                        rotateToColumn = col;
                    }
                }
            }
        }
        final boolean isSymmetric = ((rotateToRow - rotateFromRow) == (rotateToColumn - rotateFromColumn));

        //isolate parts to rotate
        final Object[][] matrixPart = new Object[rotateToRow - rotateFromRow + 1][rotateToColumn - rotateFromColumn + 1];
        for (int row = rotateFromRow; row <= rotateToRow; row++) {
            for (int col = rotateFromColumn; col <= rotateToColumn; col++) {
                matrixPart[row - rotateFromRow][col - rotateFromColumn] = matrix[row][col];
            }
        }

        //rotate parts
        final Object[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);

        //init new matrix
        final Object[][] rotatedMatrix = new Object[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                rotatedMatrix[row][col] = " "; //TODO change to Cell
            }
        }

        //reintegrate parts
        for (int row = 0; row < rotatedMatrixPart.length; row++) {
            for (int col = 0; col < rotatedMatrixPart[0].length; col++) {
                try {
                    if (isSymmetric) {
                        rotatedMatrix[row + rotateFromRow][col + rotateFromColumn] = rotatedMatrixPart[row][col];
                    } else {
                        //row and column offset switched!
                        rotatedMatrix[row + rotateFromColumn][col + rotateFromRow] = rotatedMatrixPart[row][col];
                    }
                } catch (final ArrayIndexOutOfBoundsException aioobe) {
                    throw new RotationImpossibleException();
                }
            }
        }

        //return result
        return rotatedMatrix;
    }

    private static Object[][] rotateConcentric(final Object[][] matrix, final RotationDirection rd) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final Object[][] rotated = new Object[n][m];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rd.equals(RotationDirection.CLOCKWISE)) {
                    rotated[col][m - 1 - row] = matrix[row][col];
                }
                if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                    rotated[n - 1 - col][row] = matrix[row][col];
                }
            }
        }
        return rotated;
    }

    public static void printMatrix(final Object[][] matrix) {
        for (Object[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
