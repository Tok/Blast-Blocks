package blast.blocks.shared;

import blast.blocks.shared.enums.MovementType;
import blast.blocks.shared.enums.Shape;


public class Field {
    private Cell[][][] cells;

    public Field(final int depth, final int rows, final int columns) {
        cells = new Cell[depth][rows][columns];
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
        cells[level][row][column] = new Cell(false, false, "");
    }

    public final boolean isLevelFull(final int depth) {
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; row < getColumns(); column++) {
                if (!cells[depth][row][column].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void add(final Shape shape) {
        if (shape.equals(Shape.Cube)) {
            setCell(0, 1, 1, new Cell(true, false, ""));
            System.out.println("Cube added.");
        }
    }

    public final boolean tryMove(final MovementType movementType) {
        if (movementType.equals(MovementType.PLUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    Cell cell = getCell(level, row, getColumns() - 1); //right column
                    if (cell.isOccupied() && !cell.isFixed()) {
                        return false; //movement impossible
                    }
                }
            }
            move(movementType);
            return true;
        } else if (movementType.equals(MovementType.MINUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    Cell cell = getCell(level, row, 0); //left column
                    if (cell.isOccupied() && !cell.isFixed()) {
                        return false; //movement impossible
                    }
                }
            }
            move(movementType);
            return true;
        } else if (movementType.equals(MovementType.PLUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(level, 0, column); //Top row
                    if (cell.isOccupied() && !cell.isFixed()) {
                        return false; //movement impossible
                    }
                }
            }
            move(movementType);
            return true;
        } else if (movementType.equals(MovementType.MINUS_Y)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(level, getRows() - 1, column); //Bottom row
                    if (cell.isOccupied() && !cell.isFixed()) {
                        return false; //movement impossible
                    }
                }
            }
            move(movementType);
            return true;
        } else if (movementType.equals(MovementType.MINUS_Z)) {
            for (int row = 0; row < getRows(); row++) {
                for (int column = 0; column < getColumns(); column++) {
                    Cell cell = getCell(getDepth() - 1, row, column);
                    if (cell.isOccupied() && !cell.isFixed()) {
                        return false; //movement impossible
                    }
                }
            }
            move(movementType);
            return true;
        }
        return false;
    }

    private void move(final MovementType movementType) {
        if (movementType.equals(MovementType.PLUS_X)) {
            for (int level = 0; level < getDepth(); level++) {
                for (int row = 0; row < getRows(); row++) {
                    for (int column = getColumns() - 1; column >= 0; column--) { //Omit right column
                        final Cell cell = getCell(level, row, column);
                        if (cell.isOccupied() && !cell.isFixed()) {
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
                        if (cell.isOccupied() && !cell.isFixed()) {
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
                        if (cell.isOccupied() && !cell.isFixed()) {
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
                        if (cell.isOccupied() && !cell.isFixed()) {
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
                        if (cell.isOccupied() && !cell.isFixed()) {
                            resetCell(level, row, column);
                            setCell(level + 1, row, column, cell);
                        }
                    }
                }
            }
        }
    }
}
