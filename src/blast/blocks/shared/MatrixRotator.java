package blast.blocks.shared;

import java.util.Arrays;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.exception.RotationImpossibleException;

public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    private int rotateFromRow = 0;
    private int rotateFromColumn = 0;

    public MatrixRotator() {
    }

    private Cell[][] balanceMatrix(final Cell[][] matrix, final RotationDirection rd) throws RotationImpossibleException {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int initSquare = Math.max(rows, cols);
        int firstRowCount = 0;
        int lastRowCount = 0;
        int firstColCount = 0;
        int lastColCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].getBlockType().isMovable()) {
                    if (row == 0) {
                        firstRowCount++;
                    } else if (row == matrix.length - 1) {
                        lastRowCount++;
                    }
                    if (col == 0) {
                        firstColCount++;
                    } else if (col == matrix[0].length - 1) {
                        lastColCount++;
                    }
                }
            }
        }

        final Cell[][] balanced = new Cell[initSquare][initSquare];
        try {
            //TODO remove redundancies!
            if (initSquare == rows + 2) { //append one row before and one row after
                for (int row = 1; row < initSquare - 1; row++) {
                    rotateFromRow--;
                    for (int col = 0; col < initSquare; col++) {
                        balanced[0][col] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][col] = matrix[row - 1][col];
                        balanced[initSquare - 1][col] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            } else if (initSquare == cols + 2) { //append one col before and one after
                for (int col = 1; col < initSquare - 1; col++) {
                    rotateFromColumn--;
                    for (int row = 0; row < initSquare; row++) {
                        balanced[row][0] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][col] = matrix[row][col - 1];
                        balanced[row][initSquare - 1] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            } else if (initSquare == rows + 3) { //append two rows before and one row after or vice versa
                for (int row = 2; row < initSquare - 1; row++) {
                    rotateFromRow -= 2;
                    for (int col = 0; col < initSquare; col++) {
                        balanced[0][col] = new Cell(BlockType.STABILIZER, "");
                        if (rd.equals(RotationDirection.CLOCKWISE)) {
                            balanced[1][col] = new Cell(BlockType.STABILIZER, "");
                            balanced[row][col] = matrix[row - 2][col];
                        } else {
                            balanced[1][col] = matrix[row - 2][col];
                            balanced[row][col] = new Cell(BlockType.STABILIZER, "");
                        }
                        balanced[initSquare - 1][col] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            } else if (initSquare == cols + 3) { //append two cols before and one after or vice versa
                for (int col = 2; col < initSquare - 1; col++) {
                    rotateFromColumn -= 2;
                    for (int row = 0; row < initSquare; row++) {
                        balanced[row][0] = new Cell(BlockType.STABILIZER, "");
                        if (rd.equals(RotationDirection.CLOCKWISE)) {
                            balanced[row][1] = new Cell(BlockType.STABILIZER, "");
                            balanced[row][col] = matrix[row][col - 2];
                        } else {
                            balanced[row][1] = matrix[row][col - 2];
                            balanced[row][col] = new Cell(BlockType.STABILIZER, "");
                        }
                        balanced[row][initSquare - 1] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            } else if (initSquare == rows + 4) { //append two rows before and two after
                for (int row = 2; row < initSquare - 2; row++) {
                    rotateFromRow -= 2;
                    for (int col = 0; col < initSquare; col++) {
                        balanced[0][col] = new Cell(BlockType.STABILIZER, "");
                        balanced[1][col] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][col] = matrix[row - 2][col];
                        balanced[initSquare - 2][col] = new Cell(BlockType.STABILIZER, "");
                        balanced[initSquare - 1][col] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            }
            if (initSquare == cols + 4) { //append two cols before and two after
                for (int col = 2; col < initSquare - 2; col++) {
                    rotateFromColumn -= 2;
                    for (int row = 0; row < initSquare; row++) {
                        balanced[row][0] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][1] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][col] = matrix[row][col - 2];
                        balanced[row][initSquare - 2] = new Cell(BlockType.STABILIZER, "");
                        balanced[row][initSquare - 1] = new Cell(BlockType.STABILIZER, "");
                    }
                }
                return balanced;
            }
            if (rows < cols) {
                if (firstRowCount > lastRowCount) { // pre append row
                    rotateFromRow--;
                    for (int col = 0; col < initSquare; col++) {
                        balanced[0][col] = new Cell(BlockType.STABILIZER, "");
                        for (int row = 1; row < initSquare; row++) {
                            balanced[row][col] = matrix[row - 1][col];
                        }
                    }
                } else { // post append row
                    for (int col = 0; col < initSquare; col++) {
                        for (int row = 0; row < initSquare - 1; row++) {
                            balanced[row][col] = matrix[row][col];
                        }
                        balanced[initSquare - 1][col] = new Cell(BlockType.STABILIZER, "");
                    }
                }
            } else { //cols >= rows
                if (firstColCount > lastColCount) { // pre append col
                    rotateFromColumn--;
                    for (int row = 0; row < initSquare; row++) {
                        balanced[row][0] = new Cell(BlockType.STABILIZER, "");
                        for (int col = 1; col < initSquare; col++) {
                            balanced[row][col] = matrix[row][col - 1];
                        }
                    }
                } else { // post append col
                    for (int row = 0; row < initSquare; row++) {
                        for (int col = 0; col < initSquare - 1; col++) {
                            balanced[row][col] = matrix[row][col];
                        }
                        balanced[row][initSquare - 1] = new Cell(BlockType.STABILIZER, "");
                    }
                }
            }
        } catch (final ArrayIndexOutOfBoundsException aioobe) {
            throw new RotationImpossibleException();
        }
        return balanced;
    }

    public Cell[][] rotateExcentric(final Cell[][] matrix, final RotationDirection rd) throws RotationImpossibleException {
        //determine parts to rotate
        rotateFromRow = matrix.length;
        int rotateToRow = 0;
        rotateFromColumn = matrix[0].length;
        int rotateToColumn = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].getBlockType().isMovable()) { //has Element
                    rotateFromRow = Math.min(row, rotateFromRow);
                    rotateFromColumn = Math.min(col, rotateFromColumn);
                    rotateToRow = Math.max(row, rotateToRow);
                    rotateToColumn = Math.max(col, rotateToColumn);
                }
            }
        }
        final boolean isSymmetric = ((rotateToRow - rotateFromRow) == (rotateToColumn - rotateFromColumn));

        //isolate parts to rotate
        int rotationRows = rotateToRow - rotateFromRow + 1;
        int rotationColumns = rotateToColumn - rotateFromColumn + 1;
        final Cell[][] matrixPart = new Cell[rotationRows][rotationColumns];
        for (int row = rotateFromRow; row <= rotateToRow; row++) {
            for (int col = rotateFromColumn; col <= rotateToColumn; col++) {
                matrixPart[row - rotateFromRow][col - rotateFromColumn] = matrix[row][col];
            }
        }

        //keep symmetry if required
        final Cell[][] balancedMatrix;
        if (!isSymmetric) {
            balancedMatrix = balanceMatrix(matrixPart, rd);
        } else {
            balancedMatrix = matrixPart;
        }

        //rotate parts
        final Cell[][] rotatedMatrixPart = rotateConcentric(balancedMatrix, rd);

        //init new matrix
        final Cell[][] rotatedMatrix = new Cell[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                rotatedMatrix[row][col] = new Cell(BlockType.EMPTY, "");
            }
        }

        //reintegrate parts and replace stabilizers
        try {
            for (int row = 0; row < rotatedMatrixPart.length; row++) {
                for (int col = 0; col < rotatedMatrixPart[0].length; col++) {
                    rotatedMatrix[row + rotateFromRow][col + rotateFromColumn] = rotatedMatrixPart[row][col];
                    if (rotatedMatrix[row + rotateFromRow][col + rotateFromColumn].getBlockType().equals(BlockType.STABILIZER)) {
                        rotatedMatrix[row + rotateFromRow][col + rotateFromColumn] = new Cell(BlockType.EMPTY, "");
                    }
                }
            }
        } catch (final ArrayIndexOutOfBoundsException aioobe) {
            throw new RotationImpossibleException();
        }

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

    public static void printMatrix2D(final Cell[][] matrix) {
        for (final Cell[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void printMatrix3D(final Cell[][][] matrix) {
        for (final Cell[][] row : matrix) {
            System.out.println("---------");
            for (final Cell[] col : row) {
                System.out.println(Arrays.toString(col));
            }
        }
        System.out.println("=========");
    }

}
