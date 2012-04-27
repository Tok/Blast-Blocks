package blast.blocks.shared.enums;


public enum Key {
    Q(81),
    W(87),
    E(69),
    A(65),
    S(83),
    D(68),
    UP(38),
    DOWN(40),
    LEFT(37),
    RIGHT(39),
    SPACE(32);

    private final int keyCode;

    private Key(final int keyCode) {
        this.keyCode = keyCode;
    }

    public final int getKeyCode() {
        return keyCode;
    }
}
