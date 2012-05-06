package blast.blocks.shared;

import java.util.HashMap;
import java.util.Map;
import blast.blocks.shared.enums.Axis;
import blast.blocks.shared.enums.BlockType;
import blast.blocks.shared.exception.HasNoCenterException;

public class Offsets {
    private Map<Axis, Integer> totals = new HashMap<Axis, Integer>(Axis.values().length);
    private Map<Axis, Integer> froms = new HashMap<Axis, Integer>(Axis.values().length);
    private Map<Axis, Integer> tos = new HashMap<Axis, Integer>(Axis.values().length);
    private boolean hasCenter = false;
    private Map<Axis, Integer> centers = new HashMap<Axis, Integer>(Axis.values().length);

    public Offsets(final Cell[][][] cells) {
        totals.put(Axis.Z, cells.length);
        totals.put(Axis.Y, cells[0].length);
        totals.put(Axis.X, cells[0][0].length);
        for (Axis axis : Axis.values()) {
            froms.put(axis, totals.get(axis));
            tos.put(axis, Integer.valueOf(0));
        }
        for (int z = 0; z < cells.length; z++) {
            for (int y = 0; y < cells[0].length; y++) {
                for (int x = 0; x < cells[0][0].length; x++) {
                    BlockType type = cells[z][y][x].getBlockType();
                    if (type.isMovable()) { //has Element
                        froms.put(Axis.Z, Math.min(z, froms.get(Axis.Z)));
                        froms.put(Axis.Y, Math.min(y, froms.get(Axis.Y)));
                        froms.put(Axis.X, Math.min(x, froms.get(Axis.X)));
                        tos.put(Axis.Z, Math.max(z, tos.get(Axis.Z)));
                        tos.put(Axis.Y, Math.max(y, tos.get(Axis.Y)));
                        tos.put(Axis.X, Math.max(x, tos.get(Axis.X)));
                        if (type.equals(BlockType.CENTER)) {
                            hasCenter = true;
                            centers.put(Axis.Z, z);
                            centers.put(Axis.Y, y);
                            centers.put(Axis.X, x);
                        }
                    }
                }
            }
        }
    }

    public final int getTotal(final Axis axis) {
        return totals.get(axis);
    }

    public final int getFrom(final Axis axis) {
        return froms.get(axis);
    }

    public final int getTo(final Axis axis) {
        return tos.get(axis);
    }

    public final int getDifference(final Axis axis) {
        return tos.get(axis) - froms.get(axis) + 1;
    }

    public final int getReverseTo(final Axis axis) {
        return totals.get(axis) - tos.get(axis) - 1;
    }

    public final int getCenter(final Axis axis) {
        if (hasCenter) {
            return centers.get(axis);
        } else {
            throw new HasNoCenterException();
        }
    }

    public final boolean hasCenter() {
        return hasCenter;
    }

    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();
        for (Axis axis : Axis.values()) {
            builder.append(axis.name());
            builder.append(":[");
            builder.append(getFrom(axis));
            builder.append(":");
            builder.append(getTo(axis));
            builder.append("(");
            builder.append(getDifference(axis));
            builder.append(") ");
            builder.append(getTotal(axis));
            builder.append("] ");
        }
        if (hasCenter) {
            builder.append("Center: Z");
            builder.append(getCenter(Axis.Z));
            builder.append(":Y");
            builder.append(getCenter(Axis.Y));
            builder.append(":X");
            builder.append(getCenter(Axis.X));
        }
        return builder.toString();
    }
}
