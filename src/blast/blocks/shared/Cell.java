package blast.blocks.shared;

import blast.blocks.shared.enums.BlockType;

public class Cell {
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

    @Override
    public final String toString() {
        return type.toString();
    }
}
