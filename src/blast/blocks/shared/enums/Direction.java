package blast.blocks.shared.enums;

public enum Direction {
    Front("+Z"),
    Back("-Z"),
    Left("-X"),
    Right("+X"),
    Top("-Y"),
    Bottom("+Y");

    private final String coordinate;

    private Direction(final String coordinate) {
        this.coordinate = coordinate;
    }

    public final String getCoordinate() {
        return coordinate;
    }
}
