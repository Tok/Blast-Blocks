package blast.blocks.shared;

import java.util.Arrays;

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
                        rotateToColumn = row;
                    }
                }
            }
        }
//      printMatrix(matrix);
//      System.out.println("---------------");

        //isolate parts to rotate
        final Object[][] matrixPart = new Object[rotateToRow - rotateFromRow + 1][rotateToColumn - rotateFromColumn + 1];
        for (int row = rotateFromRow; row <= rotateToRow; row++) {
            for (int col = rotateFromColumn; col <= rotateToColumn; col++) {
                matrixPart[row - rotateFromRow][col - rotateFromColumn] = matrix[row][col];
            }
        }

        //rotate parts
        final Object[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);

        //reintegrate parts
        final Object[][] rotatedMatrix = matrix;
        for (int row = rotateFromRow; row <= rotateToRow; row++) {
            for (int col = rotateFromColumn; col <= rotateToColumn; col++) {
                rotatedMatrix[row][col] = rotatedMatrixPart[row - rotateFromRow][col - rotateFromColumn];
            }
        }
//      printMatrix(rotatedMatrix);
//      System.out.println("===============");

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
                    rotated[m - 1 - col][row] = matrix[row][col];
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
