package blast.blocks.shared;

import java.util.Arrays;
import blast.blocks.shared.enums.Axis;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.enums.RotationType;
import blast.blocks.shared.exception.HasNoCenterException;
import blast.blocks.shared.exception.RotationImpossibleException;

public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    public MatrixRotator() {
    }

    private Cell[][] rotateConcentric(final Cell[][] matrix, final RotationDirection rd) {
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

    public Cell[][][] rotate(final Cell[][][] cells, final RotationType rt) throws RotationImpossibleException {
        final Offsets offsets = new Offsets(cells);
        //System.out.println(offsets);

        Cell[][][] rotated = new Cell[cells.length][cells[0].length][cells[0][0].length];

        if (rt.equals(RotationType.PLUS_X)) {
            rotated = rotateX(cells, RotationDirection.CLOCKWISE, offsets);
        } else if (rt.equals(RotationType.MINUS_X)) {
            rotated = rotateX(cells, RotationDirection.COUNTERCLOCKWISE, offsets);
        } else if (rt.equals(RotationType.PLUS_Y)) {
            rotated = rotateY(cells, RotationDirection.CLOCKWISE, offsets);
        } else if (rt.equals(RotationType.MINUS_Y)) {
            rotated = rotateY(cells, RotationDirection.COUNTERCLOCKWISE, offsets);
        } else if (rt.equals(RotationType.PLUS_Z)) {
            rotated = rotateZ(cells, RotationDirection.CLOCKWISE, offsets);
        } else if (rt.equals(RotationType.MINUS_Z)) {
            rotated = rotateZ(cells, RotationDirection.COUNTERCLOCKWISE, offsets);
        } else {
            throw new IllegalArgumentException("RotationType unknown: " + rt);
        }

        return rotated;
    }

    private Cell[][][] rotateZ(final Cell[][][] cells, final RotationDirection rd, final Offsets offsets) throws RotationImpossibleException {
        final Cell[][][] rotated = initCells(cells);
        for (int level = offsets.getFrom(Axis.Z); level <= offsets.getTo(Axis.Z); level++) {
            //isolate parts to rotate
            final Cell[][] matrixPart = new Cell[offsets.getDifference(Axis.Y)][offsets.getDifference(Axis.X)];
            for (int row = offsets.getFrom(Axis.Y); row <= offsets.getTo(Axis.Y); row++) {
                for (int column = offsets.getFrom(Axis.X); column <= offsets.getTo(Axis.X); column++) {
                    matrixPart[row - offsets.getFrom(Axis.Y)][column - offsets.getFrom(Axis.X)] = cells[level][row][column];
                }
            }
            //rotate parts
            final Cell[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);
            //reintegrate rotated parts
            for (int row = 0; row < rotatedMatrixPart.length; row++) {
                for (int column = 0; column < rotatedMatrixPart[0].length; column++) {
                    final Cell cell = rotatedMatrixPart[row][column];
                    if (rd.equals(RotationDirection.CLOCKWISE)) {
                        rotated[level][row + offsets.getFrom(Axis.X)][column + offsets.getReverseTo(Axis.Y)] = cell;
                    } else if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                        rotated[level][row + offsets.getReverseTo(Axis.X)][column + offsets.getFrom(Axis.Y)] = cell;
                    } else {
                        throw new IllegalArgumentException("RotationDirection unknown: " + rd);
                    }
                }
            }
        }
        if (!offsets.hasCenter()) {
            return rotated;
        } else {
            return reArrangeCenter(rotated, offsets);
        }
    }

    private Cell[][][] rotateY(final Cell[][][] cells, final RotationDirection rd, final Offsets offsets) throws RotationImpossibleException {
        final Cell[][][] rotated = initCells(cells);
        for (int column = offsets.getFrom(Axis.X); column <= offsets.getTo(Axis.X); column++) {
            //isolate parts to rotate
            final Cell[][] matrixPart = new Cell[offsets.getDifference(Axis.Y)][offsets.getDifference(Axis.Z)];
            for (int row = offsets.getFrom(Axis.Y); row <= offsets.getTo(Axis.Y); row++) {
                for (int level = offsets.getFrom(Axis.Z); level <= offsets.getTo(Axis.Z); level++) {
                    matrixPart[row - offsets.getFrom(Axis.Y)][level - offsets.getFrom(Axis.Z)] = cells[level][row][column];
                }
            }
            //rotate parts
            final Cell[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);
            //reintegrate rotated parts
            for (int row = 0; row < rotatedMatrixPart.length; row++) {
                for (int level = 0; level < rotatedMatrixPart[0].length; level++) {
                    final Cell cell = rotatedMatrixPart[row][level];
                    if (rd.equals(RotationDirection.CLOCKWISE)) {
                        rotated[level + offsets.getFrom(Axis.Y)][row + offsets.getReverseTo(Axis.Z)][column] = cell;
                    } else if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                        rotated[level + offsets.getReverseTo(Axis.Y)][row + offsets.getFrom(Axis.Z)][column] = cell;
                    } else {
                        throw new IllegalArgumentException("RotationDirection unknown: " + rd);
                    }
                }
            }
        }
        if (!offsets.hasCenter()) {
            return rotated;
        } else {
            return reArrangeCenter(rotated, offsets);
        }
    }

    private Cell[][][] rotateX(final Cell[][][] cells, final RotationDirection rd, final Offsets offsets) throws RotationImpossibleException {
        final Cell[][][] rotated = initCells(cells);
        for (int row = offsets.getFrom(Axis.Y); row <= offsets.getTo(Axis.Y); row++) {
            //isolate parts to rotate
            final Cell[][] matrixPart = new Cell[offsets.getDifference(Axis.Z)][offsets.getDifference(Axis.X)];
            for (int level = offsets.getFrom(Axis.Z); level <= offsets.getTo(Axis.Z); level++) {
                for (int column = offsets.getFrom(Axis.X); column <= offsets.getTo(Axis.X); column++) {
                    matrixPart[level - offsets.getFrom(Axis.Z)][column - offsets.getFrom(Axis.X)] = cells[level][row][column];
                }
            }
            //rotate parts
            final Cell[][] rotatedMatrixPart = rotateConcentric(matrixPart, rd);
            //reintegrate rotated parts
            for (int level = 0; level < rotatedMatrixPart.length; level++) {
                for (int column = 0; column < rotatedMatrixPart[0].length; column++) {
                    final Cell cell = rotatedMatrixPart[level][column];
                    if (rd.equals(RotationDirection.CLOCKWISE)) {
                        rotated[level + offsets.getReverseTo(Axis.X)][row][column + offsets.getFrom(Axis.Z)] = cell;
                    } else if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                        rotated[level + offsets.getFrom(Axis.X)][row][column + offsets.getReverseTo(Axis.Z)] = cell;
                    } else {
                        throw new IllegalArgumentException("RotationDirection unknown: " + rd);
                    }
                }
            }
        }
        if (!offsets.hasCenter()) {
            return rotated;
        } else {
            return reArrangeCenter(rotated, offsets);
        }
    }

    private Cell[][][] initCells(final Cell[][][] cells) {
        final Cell[][][] result = new Cell[cells.length][cells[0].length][cells[0][0].length];
        for (int level = 0; level < cells.length; level++) {
            for (int row = 0; row < cells[0].length; row++) {
                for (int column = 0; column < cells[0][0].length; column++) {
                    result[level][row][column] = new Cell(BlockType.EMPTY, "");
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unused")
    private Point2D getRotatedCenter2D(final Cell[][] rotated) {
        for (int row = 0; row < rotated.length; row++) {
            for (int col = 0; col < rotated[0].length; col++) {
                final BlockType type = rotated[row][col].getBlockType();
                if (type.equals(BlockType.CENTER)) {
                    return new Point2D(row, col);
                }
            }
        }
        throw new HasNoCenterException();
    }

    private Point3D getRotatedCenter3D(final Cell[][][] rotated) {
        for (int level = 0; level < rotated.length; level++) {
            for (int row = 0; row < rotated[0].length; row++) {
                for (int col = 0; col < rotated[0][0].length; col++) {
                    final BlockType type = rotated[level][row][col].getBlockType();
                    if (type.equals(BlockType.CENTER)) {
                        return new Point3D(level, row, col);
                    }
                }
            }
        }
        throw new HasNoCenterException();
    }

    private Cell[][][] reArrangeCenter(final Cell[][][] rotated, final Offsets offsets) throws RotationImpossibleException {
        final Point3D point = getRotatedCenter3D(rotated);
        final Cell[][][] arranged = initCells(rotated);
        try {
            for (int level = 0; level < rotated.length; level++) {
                for (int row = 0; row < rotated[0].length; row++) {
                    for (int col = 0; col < rotated[0][0].length; col++) {
                        final Cell cell = rotated[level][row][col];
                        final int arrangedLevel = level - point.getZ() + offsets.getCenter(Axis.Z);
                        final int arrangedRow = row - point.getY() + offsets.getCenter(Axis.Y);
                        final int arrangedCol = col - point.getX() + offsets.getCenter(Axis.X);
                        if (cell.getBlockType().isMovable()) {
                            arranged[arrangedLevel][arrangedRow][arrangedCol] = cell;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new RotationImpossibleException();
        }
        return arranged;
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
