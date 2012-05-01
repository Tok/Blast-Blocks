package blast.blocks.shared;

import java.util.Arrays;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.exception.RotationImpossibleException;

public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    private int rotateFromRowFixed = 0;
    private int rotateFromColumnFixed = 0;
    private int rotateToRowFixed = 0;
    private int rotateToColumnFixed = 0;

    public MatrixRotator() {
    }

    private Cell[][] appendOneRowBeforeAndOneAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare) {
        for (int row = 1; row < initSquare - 1; row++) {
            rotateFromRowFixed--;
            rotateToRowFixed++;
            for (int col = 0; col < initSquare; col++) {
                balanced[0][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][col] = matrix[row - 1][col];
                balanced[initSquare - 1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] appendOneColBeforeAndOneAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare) {
        for (int col = 1; col < initSquare - 1; col++) {
            rotateFromColumnFixed--;
            rotateToColumnFixed++;
            for (int row = 0; row < initSquare; row++) {
                balanced[row][0] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][col] = matrix[row][col - 1];
                balanced[row][initSquare - 1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] appendOneOrTwoRowsBeforeAndOneOrTwoAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare, final RotationDirection rd) {
        for (int row = 2; row < initSquare - 1; row++) {
            rotateFromRowFixed--;
            rotateToRowFixed++;
            for (int col = 0; col < initSquare; col++) {
                balanced[0][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                if (rd.equals(RotationDirection.CLOCKWISE)) {
                    if (col == 0) {
                        rotateFromRowFixed--;
                    }
                    balanced[1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                    balanced[row][col] = matrix[row - 2][col];
                } else {
                    balanced[1][col] = matrix[row - 2][col];
                    balanced[row][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                    if (col == 0) {
                        rotateToRowFixed++;
                    }
                }
                balanced[initSquare - 1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] appendOneOrTwoColsBeforeAndOneOrTwoAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare, final RotationDirection rd) {
        for (int col = 2; col < initSquare - 1; col++) {
            rotateFromColumnFixed--;
            rotateToColumnFixed++;
            for (int row = 0; row < initSquare; row++) {
                balanced[row][0] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                if (rd.equals(RotationDirection.CLOCKWISE)) {
                    if (row == 0) {
                        rotateFromColumnFixed--;
                    }
                    balanced[row][1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                    balanced[row][col] = matrix[row][col - 2];
                } else {
                    if (row == 0) {
                        rotateToColumnFixed++;
                    }
                    balanced[row][1] = matrix[row][col - 2];
                    balanced[row][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                }
                balanced[row][initSquare - 1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] appendTwoRowsBeforeAndAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare) {
        for (int row = 2; row < initSquare - 2; row++) {
            rotateFromRowFixed -= 2;
            rotateToRowFixed += 2;
            for (int col = 0; col < initSquare; col++) {
                balanced[0][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][col] = matrix[row - 2][col];
                balanced[initSquare - 2][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[initSquare - 1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] appendTwoColsBeforeAndAfter(final Cell[][] matrix, final Cell[][] balanced, final int initSquare) {
        for (int col = 2; col < initSquare - 2; col++) {
            rotateFromColumnFixed -= 2;
            rotateToColumnFixed += 2;
            for (int row = 0; row < initSquare; row++) {
                balanced[row][0] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][col] = matrix[row][col - 2];
                balanced[row][initSquare - 2] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                balanced[row][initSquare - 1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
            }
        }
        return balanced;
    }

    private Cell[][] balanceMatrix(final Cell[][] matrix, final RotationDirection rd) throws RotationImpossibleException {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int initSquare = Math.max(rows, cols);
        int firstRowCount = 0;
        int lastRowCount = 0;
        int firstColCount = 0;
        int lastColCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].getBlockType().equals(BlockType.BLOCK)) {
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
            if (initSquare == rows + 2) {
                return appendOneRowBeforeAndOneAfter(matrix, balanced, initSquare);
            } else if (initSquare == cols + 2) {
                return appendOneColBeforeAndOneAfter(matrix, balanced, initSquare);
            } else if (initSquare == rows + 3) {
                return appendOneOrTwoRowsBeforeAndOneOrTwoAfter(matrix, balanced, initSquare, rd);
            } else if (initSquare == cols + 3) {
                return appendOneOrTwoColsBeforeAndOneOrTwoAfter(matrix, balanced, initSquare, rd);
            } else if (initSquare == rows + 4) {
                return appendTwoRowsBeforeAndAfter(matrix, balanced, initSquare);
            } else if (initSquare == cols + 4) { //append two cols before and two after
                return appendTwoColsBeforeAndAfter(matrix, balanced, initSquare);
            } else if (rows < cols) {
                if (firstRowCount > lastRowCount) { // pre append row
                    rotateFromRowFixed--;
                    for (int col = 0; col < initSquare; col++) {
                        balanced[0][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                        for (int row = 1; row < initSquare; row++) {
                            balanced[row][col] = matrix[row - 1][col];
                        }
                    }
                } else { // post append row
                    rotateToRowFixed++;
                    for (int col = 0; col < initSquare; col++) {
                        for (int row = 0; row < initSquare - 1; row++) {
                            balanced[row][col] = matrix[row][col];
                        }
                        balanced[initSquare - 1][col] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                    }
                }
            } else { //cols >= rows
                if (firstColCount > lastColCount) { // pre append col
                    rotateFromColumnFixed--;
                    for (int row = 0; row < initSquare; row++) {
                        balanced[row][0] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
                        for (int col = 1; col < initSquare; col++) {
                            balanced[row][col] = matrix[row][col - 1];
                        }
                    }
                } else { // post append col
                    rotateToColumnFixed++;
                    for (int row = 0; row < initSquare; row++) {
                        for (int col = 0; col < initSquare - 1; col++) {
                            balanced[row][col] = matrix[row][col];
                        }
                        balanced[row][initSquare - 1] = new Cell(BlockType.DYNAMIC_STABILIZER, "");
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
        int rotateFromRow = matrix.length;
        int rotateToRow = 0;
        int rotateFromColumn = matrix[0].length;
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
        rotateFromRowFixed = rotateFromRow;
        rotateFromColumnFixed = rotateFromColumn;
        rotateToRowFixed = rotateToRow;
        rotateToColumnFixed = rotateToColumn;
        final Cell[][] balancedMatrix;
        if (!isSymmetric) {
            balancedMatrix = balanceMatrix(matrixPart, rd);
        } else {
            balancedMatrix = matrixPart;
        }

        //rotate parts
        final Cell[][] rotatedMatrixPart = rotateConcentric(balancedMatrix, rd);
        boolean hasStaticStabilizers = false;
        for (int row = 0; row < rotatedMatrixPart.length; row++) {
            for (int column = 0; column < rotatedMatrixPart[0].length; column++) {
                if (rotatedMatrixPart[row][column].getBlockType().equals(BlockType.STATIC_STABILIZER)) {
                    hasStaticStabilizers = true;
                }
            }
        }

        //init new matrix
        final Cell[][] rotatedMatrix = new Cell[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                rotatedMatrix[row][col] = new Cell(BlockType.EMPTY, "");
            }
        }

        //reintegrate parts and replace dynamic stabilizers
        try {
            for (int row = 0; row < rotatedMatrixPart.length; row++) {
                for (int col = 0; col < rotatedMatrixPart[0].length; col++) {
                    if (isSymmetric || !hasStaticStabilizers) {
                        rotatedMatrix[row + rotateFromRowFixed][col + rotateFromColumnFixed] = rotatedMatrixPart[row][col];
                        if (rotatedMatrix[row + rotateFromRowFixed][col + rotateFromColumnFixed].getBlockType().equals(BlockType.DYNAMIC_STABILIZER)) {
                            rotatedMatrix[row + rotateFromRowFixed][col + rotateFromColumnFixed] = new Cell(BlockType.EMPTY, "");
                        }
                    } else {
                        if (rd.equals(RotationDirection.CLOCKWISE)) {
                            rotatedMatrix[row + rotateFromColumnFixed][col + (rotatedMatrix.length - rotateToRowFixed - 1)] = rotatedMatrixPart[row][col];
                            if (rotatedMatrix[row + rotateFromColumnFixed][col + (rotatedMatrix.length - rotateToRowFixed - 1)].getBlockType().equals(BlockType.DYNAMIC_STABILIZER)) {
                                rotatedMatrix[row + rotateFromColumnFixed][col + (rotatedMatrix.length - rotateToRowFixed - 1)] = new Cell(BlockType.EMPTY, "");
                            }
                        } else if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                            rotatedMatrix[row + (rotatedMatrix[0].length - rotateToColumnFixed - 1)][col + rotateFromRowFixed] = rotatedMatrixPart[row][col];
                            if (rotatedMatrix[row + (rotatedMatrix[0].length - rotateToColumnFixed - 1)][col + rotateFromRowFixed].getBlockType().equals(BlockType.DYNAMIC_STABILIZER)) {
                                rotatedMatrix[row + (rotatedMatrix[0].length - rotateToColumnFixed - 1)][col + rotateFromRowFixed] = new Cell(BlockType.EMPTY, "");
                            }
                        } else {
                            throw new IllegalArgumentException("RotationDirection unknown: " + rd);
                        }
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
        System.out.println("=========");
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
