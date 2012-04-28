package blast.blocks.shared.exception;

public class RotationImpossibleException extends RuntimeException {
    private static final long serialVersionUID = 3005526281776039829L;

    public RotationImpossibleException() {
    }

    public RotationImpossibleException(final String string) {
        super(string);
    }
}
