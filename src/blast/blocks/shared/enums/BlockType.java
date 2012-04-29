package blast.blocks.shared.enums;

public enum BlockType {
    EMPTY(' ', false, false, false),
    BLOCK('X', true, true, false),
//  ROTATION_CENTER('+', true, true, false),
    FIXED('#', true, false, true),
    STABILIZER('o', false, true, false),
    FIXED_STABILIZER('O', false, false, true);

    private char character;
    private boolean isBlock;
    private boolean isMovable;
    private boolean isFixed;

    private BlockType(final char character, final boolean isBlock, final boolean isMovable, final boolean isFixed) {
        this.character = character;
        this.isBlock = isBlock;
        this.isMovable = isMovable;
        this.isFixed = isFixed;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public String getCharacterString() {
        return String.valueOf(character);
    }

    public static BlockType returnBlock(final String typeString) {
        for (BlockType type: BlockType.values()) {
            if (type.getCharacterString().equals(typeString)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Illegal type: " + typeString);
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
