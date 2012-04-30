package blast.blocks.shared;

import blast.blocks.shared.MatrixRotator.RotationDirection;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.enums.MovementType;
import blast.blocks.shared.enums.RotationType;
import blast.blocks.shared.enums.Shape;
import blast.blocks.shared.exception.RotationImpossibleException;


public class Field {
    private Cell[][][] cells;
    private MatrixRotator matrixRotator = new MatrixRotator();

    public Field(final int depth, final int rows, final int columns) {
        cells = new Cell[depth][rows][columns];
        resetField();
    }

    private void resetField() {
        for (int level = 0; level < getDepth(); level++) {
            for (int row = 0; row < getRows(); row++) {
                for (int column = 0; column < getColumns(); column++) {
                    resetCell(level, row, column);
                }
            }
        }
    }

    public final int getDepth() {
        return cells.length;
    }

    public final int getRows() {
        return cells[0].length;
    }

    public final int getColumns() {
        return cells[0][0].length;
    }

    private Cell getCell(final int level, final int row, final int column) {
        return cells[level][row][column];
    }

    private void setCell(final int level, final int row, final int column, final Cell cell) {
        cells[level][row][column] = cell;
    }

    private void resetCell(final int level, final int row, final int column) {
        cells[level][row][column] = new Cell(BlockType.EMPTY, "");
    }

    public final boolean isLevelFull(final int depth) {
        //TODO make sure everything is fixed before calling this
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                if (!cells[depth][row][column].getBlockType().isFixed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void add(final Shape shape, final int levelOffset, final int rowOffset, final int columnOffset) {
        System.out.println("Adding " + shape);
        resetField();
        if (shape.equals(Shape.Cube)) {
            setCell(levelOffset, rowOffset + 1, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
        } else if (shape.equals(Shape.DualcubeX)) {
            setCell(levelOffset, rowOffset + 1, columnOffset + 0, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 1, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
        } else if (shape.equals(Shape.ITricube)) {
            setCell(levelOffset, rowOffset + 1, columnOffset + 0, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 1, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 1, columnOffset + 2, new Cell(BlockType.BLOCK, ""));
        } else if (shape.equals(Shape.LTetracube)) {
            setCell(levelOffset, rowOffset + 0, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 1, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 2, columnOffset + 1, new Cell(BlockType.BLOCK, ""));
            setCell(levelOffset, rowOffset + 2, columnOffset + 2, new Cell(BlockType.BLOCK, ""));
        }
        MatrixRotator.printMatrix3D(cells);
    }

    public final boolean tryMove(final MovementType movementType) {
        if (movementType.equals(MovementType.PLUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    Cell cell = getCell(level, row, getColumns() - 1); //Right column
                    if (cell.getBlockType().isMovable()) {
                        return false; //movement impossible
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    Cell cell = getCell(level, row, 0); //Left column
                    if (cell.getBlockType().isMovable()) {
                        return false; //movement impossible
                    }
                }
            }
        } else if (movementType.equals(MovementType.PLUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(level, 0, column); //Top row
                    if (cell.getBlockType().isMovable()) {
                        return false; //movement impossible
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(level, getRows() - 1, column); //Bottom row
                    if (cell.getBlockType().isMovable()) {
                        return false; //movement impossible
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_Z)) {
            for (int row = 0; row < getRows(); row++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(getDepth() - 1, row, column);
                    if (cell.getBlockType().isMovable()) {
                        return false; //movement impossible
                    }
                }
            }
        }
        move(movementType);
        return true;
    }

    private void move(final MovementType movementType) {
        if (movementType.equals(MovementType.PLUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    for (int column = getColumns() - 1; column >= 0; column--) { //Omit right column
                        final Cell cell = getCell(level, row, column);
                        if (cell.getBlockType().isMovable()) {
                            resetCell(level, row, column);
                            setCell(level, row, column + 1, cell);
                        }
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    for (int column = 1; column < getColumns(); column++) { //Omit left column
                        final Cell cell = getCell(level, row, column);
                        if (cell.getBlockType().isMovable()) {
                            resetCell(level, row, column);
                            setCell(level, row, column - 1, cell);
                        }
                    }
                }
            }
        } else if (movementType.equals(MovementType.PLUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 1; row < getRows(); row++) { //Omit top row
                    for (int column = 0; column < getColumns(); column++) {
                        final Cell cell = getCell(level, row, column);
                        if (cell.getBlockType().isMovable()) {
                            resetCell(level, row, column);
                            setCell(level, row - 1, column, cell);
                        }
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = getRows() - 1; row >= 0; row--) { //Omit bottom row
                    for (int column = 0; column < getColumns(); column++) {
                        final Cell cell = getCell(level, row, column);
                        if (cell.getBlockType().isMovable()) {
                            resetCell(level, row, column);
                            setCell(level, row + 1, column, cell);
                        }
                    }
                }
            }
        } else if (movementType.equals(MovementType.MINUS_Z)) {
            for (int level = getDepth() - 1; level >= 0; level--) {
                for (int row = 0; row < getRows(); row++) {
                    for (int column = 0; column < getColumns(); column++) {
                        final Cell cell = getCell(level, row, column);
                        if (cell.getBlockType().isMovable()) {
                            resetCell(level, row, column);
                            setCell(level + 1, row, column, cell);
                        }
                    }
                }
            }
        }
    }

    public final boolean tryRotate(final RotationType rotationType) {
        System.out.println(rotationType);
        try {
            if (rotationType.equals(RotationType.PLUS_X)) { //FIXME
                assert true;
            } else if (rotationType.equals(RotationType.MINUS_X)) {
                assert true;
            } else if (rotationType.equals(RotationType.PLUS_Y)) {
                assert true;
            } else if (rotationType.equals(RotationType.MINUS_Y)) {
                assert true;
            } else if (rotationType.equals(RotationType.PLUS_Z)) {
                cells[0] = matrixRotator.rotateExcentric(cells[0], RotationDirection.COUNTERCLOCKWISE);
            } else if (rotationType.equals(RotationType.MINUS_Z)) {
                cells[0] = matrixRotator.rotateExcentric(cells[0], RotationDirection.CLOCKWISE);
            }
        } catch (RotationImpossibleException rie) {
            return false;
        }
        MatrixRotator.printMatrix2D(cells[0]);
        return true;
    }

}
