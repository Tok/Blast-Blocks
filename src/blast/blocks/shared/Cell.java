package blast.blocks.shared;

import blast.blocks.shared.enums.BlockType;

public class Cell {
    private static final int ODD_PRIME = 31;
    private BlockType type;
    private String color;

    public Cell(final String typeString, final String color) {
        this.type = BlockType.returnBlock(typeString);
        this.color = color;
    }

    public Cell(final BlockType type, final String color) {
        this.type = type;
        this.color = color;
    }

    public final BlockType getBlockType() {
        return type;
    }

    public final void setBlockType(final BlockType type) {
        this.type = type;
    }

    public final String getColor() {
        return color;
    }

    public final void setColor(final String color) {
        this.color = color;
    }

    public static Cell[][] createCellMatrix2D(final String[][] types) {
        final Cell[][] cells = new Cell[types.length][types[0].length];
        for (int col = 0; col < types.length; col++) {
            for (int row = 0; row < types[0].length; row++) {
                cells[row][col] = new Cell(types[row][col], "");
            }
        }
        return cells;
    }

    public static Cell[][][] createCellMatrix3D(final String[][][] types) {
        final Cell[][][] cells = new Cell[types.length][types[0].length][types[0][0].length];
        for (int col = 0; col < types.length; col++) {
            for (int row = 0; row < types[0].length; row++) {
                for (int level = 0; level < types[0][0].length; level++) {
                    cells[row][col][level] = new Cell(types[row][col][level], "");
                }
            }
        }
        return cells;
    }

    @Override
    public final String toString() {
        return type.toString();
    }

    @Override
    public final boolean equals(final Object compare) {
        if (compare != null && this.getClass().equals(compare.getClass())) {
            //TODO compare color?
            return (type.name() == ((Cell) compare).getBlockType().name());
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        int hash = 1;
        hash = (hash * ODD_PRIME) + getBlockType().name().hashCode();
        return hash;
    }
}
