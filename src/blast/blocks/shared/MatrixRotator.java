package blast.blocks.shared;

import java.util.Arrays;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.exception.RotationImpossibleException;

public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    private MatrixRotator() {
    }

    public static Cell[][] rotateExcentric(final Cell[][] matrix, final RotationDirection rd) {
        //determine parts to rotate
        int rotateFromRow = matrix.length;
        int rotateToRow = 0;
        int rotateFromColumn = matrix[0].length;
        int rotateToColumn = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].getBlockType().isMovable()) { //has Element
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
        final Cell[][] matrixPart = new Cell[rotateToRow - rotateFromRow + 1][rotateToColumn - rotateFromColumn + 1];
        for (int row = rotateFromRow; row <= rotateToRow; row++) {
            for (int col = rotateFromColumn; col <= rotateToColumn; col++) {
                matrixPart[row - rotateFromRow][col - rotateFromColumn] = matrix[row][col];
            }
        }

        //rotate parts
        final Cell[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);

        //init new matrix
        final Cell[][] rotatedMatrix = new Cell[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                rotatedMatrix[row][col] = new Cell(BlockType.EMPTY, "");
            }
        }

//        System.out.println("+++++++++++++++");
//        MatrixRotator.printMatrix(matrix);
//        System.out.println(rotateFromRow + ":" + rotateFromColumn + "  " + rotateToRow + ":" + rotateToColumn);
//        MatrixRotator.printMatrix(rotatedMatrixPart);
//        System.out.println("---------------");

        //reintegrate parts
        for (int row = 0; row < rotatedMatrixPart.length; row++) {
            for (int col = 0; col < rotatedMatrixPart[0].length; col++) {
                try {
                    if (isSymmetric) {
                        rotatedMatrix[row + rotateFromRow][col + rotateFromColumn] = rotatedMatrixPart[row][col];
                    } else {
                        if (rd.equals(RotationDirection.CLOCKWISE)) {
                            final int rotRow = row + rotateFromColumn;
                            final int rotCol = col + (matrix.length - rotateToRow - 1);
                            rotatedMatrix[rotRow][rotCol] = rotatedMatrixPart[row][col];
                        } else if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                            final int rotRow = row + (matrix[0].length - rotateToColumn - 1);
                            final int rotCol = col + rotateFromRow;
                            rotatedMatrix[rotRow][rotCol] = rotatedMatrixPart[row][col];
                        } else {
                            throw new IllegalArgumentException("Direction unknown: " + rd);
                        }
                    }
                } catch (final ArrayIndexOutOfBoundsException aioobe) {
                    throw new RotationImpossibleException();
                }
            }
        }

//        MatrixRotator.printMatrix(rotatedMatrix);
//        System.out.println("===============");

        //return result
        return rotatedMatrix;
    }

    private static Cell[][] rotateConcentric(final Cell[][] matrix, final RotationDirection rd) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final Cell[][] rotated = new Cell[n][m];
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

    public static void printMatrix(final Cell[][] matrix) {
        for (Cell[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
