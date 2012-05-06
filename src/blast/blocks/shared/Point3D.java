package blast.blocks.shared;

public class Point3D {
    private static final int ODD_PRIME = 31;
    private static final int BITS = 32;
    private int z;
    private int y;
    private int x;

    public Point3D(final int z, final int y, final int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }

    public final int getZ() {
        return z;
    }

    public final int getY() {
        return y;
    }

    public final int getX() {
        return x;
    }

    @Override
    public final String toString() {
        return "ZYX=" + z + ":" + y + ":" + x;
    }

    @Override
    public final boolean equals(final Object compare) {
        if (compare != null && this.getClass().equals(compare.getClass())) {
            final Point3D point = (Point3D) compare;
            return point.getZ() == this.z && point.getY() == this.y && point.getX() == this.x;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int hashCode = (int) (z ^ (z >>> BITS));
        hashCode = ODD_PRIME * hashCode + (int) (y ^ (y >>> BITS));
        hashCode = ODD_PRIME * hashCode + (int) (x ^ (x >>> BITS));
        return hashCode;
    }
}
