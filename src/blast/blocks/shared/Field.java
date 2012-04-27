package blast.blocks.shared;


public class Field {
    private Cell[][][] cell;

    public Field(final int depth, final int rows, final int columns) {
        cell = new Cell[depth][rows][columns];
        for (int level = 0; level < getDepth(); level++) {
            for (int row = 0; row < getRows(); row++) {
                for (int column = 0; column < getColumns(); column++) {
                    cell[level][row][column] = new Cell(false, false, "");
                }
            }
        }
    }

    public final int getDepth() {
        return cell.length;
    }

    public final int getRows() {
        return cell[0].length;
    }

    public final int getColumns() {
        return cell[0][0].length;
    }

    public final Cell getCell(final int depth, final int row, final int column) {
        return cell[depth][row][column];
    }

    public final boolean isLevelFull(final int depth) {
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; row < getColumns(); column++) {
                if (!cell[depth][row][column].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }
}
