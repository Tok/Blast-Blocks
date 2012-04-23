package blast.blocks.client.mesh;

import java.util.List;
import blast.blocks.shared.Dir;
import blast.blocks.shared.enums.Direction;

public abstract class AbstractMeshCreator {
    private static final int NORMALS_PER_ROW = 12;
    private static final int TRIANGLES_PER_ROW = 6;

    protected static float[] getNormalsFor(final List<Dir> dirs) {
        final float[] normals = new float[dirs.size() * NORMALS_PER_ROW];
        int index = 0;
        for (Dir dir : dirs) {
            float[] normalRow = getNormals(dir.getDirection(), dir.getValue());
            for (float value : normalRow) {
                normals[index] = value;
                index++;
            }
        }
        return normals;
    }

    protected static float[] getNormals(final Direction direction, final float value) {
        if (direction.equals(Direction.Front)) {
            return getFrontNormals(value);
        } else if (direction.equals(Direction.Back)) {
            return getBackNormals(value);
        } else if (direction.equals(Direction.Left)) {
            return getLeftNormals(value);
        } else if (direction.equals(Direction.Right)) {
            return getRightNormals(value);
        } else if (direction.equals(Direction.Top)) {
            return getTopNormals(value);
        } else if (direction.equals(Direction.Bottom)) {
            return getBottomNormals(value);
        } else {
            final float[] normals = {0F,  0F, 0F,  0F,  0F, 0F,  0F,  0F, 0F,  0F,  0F, 0F};
            return normals;
        }
    }

    private static float[] getFrontNormals(final float value) {
        final float[] normals = {0F,  0F, value,  0F,  0F, value,  0F,  0F, value,  0F,  0F, value};
        return normals;
    }

    private static float[] getBackNormals(final float value) {
        final float[] normals = {0F,  0F, -value,  0F,  0F, -value,  0F,  0F, -value,  0F,  0F, -value};
        return normals;
    }

    private static float[] getTopNormals(final float value) {
        final float[] normals = {0F, value,  0F,  0F, value,  0F,  0F, value,  0F,  0F, value,  0F};
        return normals;
    }

    private static float[] getBottomNormals(final float value) {
        final float[] normals = {0F, -value,  0F,  0F, -value,  0F,  0F, -value,  0F,  0F, -value,  0F};
        return normals;
    }

    private static float[] getRightNormals(final float value) {
        final float[] normals = {value,  0F,  0F, value,  0F,  0F, value,  0F,  0F, value,  0F,  0F};
        return normals;
    }

    private static float[] getLeftNormals(final float value) {
        final float[] normals = {-value,  0F,  0F, -value,  0F,  0F, -value,  0F,  0F, -value,  0F,  0F};
        return normals;
    }

    protected static int[] getTriangles(final int rows) {
        final int[] triangles = new int[rows * TRIANGLES_PER_ROW];
        for (int i = 0, value = 0; i < (rows * TRIANGLES_PER_ROW); i = i + TRIANGLES_PER_ROW) {
            triangles[i] = value;
            triangles[i + 1] = value + 1;
            triangles[i + 2] = value + 2;
            triangles[i + 3] = value;
            triangles[i + 4] = value + 2;
            triangles[i + 5] = value + 3;
            value = value + 4;
        }
        return triangles;
    }

}
