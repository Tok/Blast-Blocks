package blast.blocks.shared.enums;


public enum Key {
    Q(81),
    W(87),
    E(69),
    A(65),
    S(83),
    D(68);

    private final int keyCode;

    private Key(final int keyCode) {
        this.keyCode = keyCode;
    }

    public final int getKeyCode() {
        return keyCode;
    }
}
