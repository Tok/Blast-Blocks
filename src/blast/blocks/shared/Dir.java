package blast.blocks.shared;

import blast.blocks.shared.enums.Direction;

public final class Dir {
    private final Direction direction;
    private final float value;

    private Dir(final Direction direction, final float value) {
        this.direction = direction;
        this.value = value;
    }

    public static Dir valueOf(final Direction direction, final float value) {
        return new Dir(direction, value);
    }

    public Direction getDirection() {
        return direction;
    }

    public float getValue() {
        return value;
    }
}
