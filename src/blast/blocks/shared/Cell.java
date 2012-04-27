package blast.blocks.shared;

public class Cell {
    private boolean isOccupied;
    private boolean isFixed;
    private String color;

    public Cell(final boolean isOccupied, final boolean isFixed, final String color) {
        this.isOccupied = isOccupied;
        this.isFixed = isFixed;
        this.color = color;
    }

    public final boolean isOccupied() {
        return isOccupied;
    }

    public final void setOccupied(final boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public final boolean isFixed() {
        return isFixed;
    }

    public final void setFixed(final boolean isFixed) {
        this.isFixed = isFixed;
    }

    public final String getColor() {
        return color;
    }

    public final void setColor(final String color) {
        this.color = color;
    }

}
