package blast.blocks.shared;

public class Point2D {
    private static final int ODD_PRIME = 31;
    private static final int BITS = 32;
    private int x;
    private int y;

    public Point2D(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    @Override
    public final String toString() {
        return "XY=" + x + ":" + y;
    }

    @Override
    public final boolean equals(final Object compare) {
        if (compare != null && this.getClass().equals(compare.getClass())) {
            final Point2D point = (Point2D) compare;
            return point.getX() == this.x && point.getY() == this.y;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int hashCode = (int) (x ^ (x >>> BITS));
        hashCode = ODD_PRIME * hashCode + (int) (y ^ (y >>> BITS));
        return hashCode;
    }
}
