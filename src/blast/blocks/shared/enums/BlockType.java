package blast.blocks.shared.enums;

public enum BlockType {
    EMPTY(' '),
    BLOCK('X'),
    FIXED('#'),
    STABILIZER('o'),
    FIXED_STABILIZER('O');

    private char character;

    private BlockType(final char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
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
