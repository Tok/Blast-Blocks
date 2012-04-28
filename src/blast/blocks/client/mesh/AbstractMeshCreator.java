package blast.blocks.client.mesh;

import gwt.g3d.client.primitive.MeshData;
import java.util.ArrayList;
import java.util.List;
import blast.blocks.shared.Dir;
import blast.blocks.shared.enums.Axis;
import blast.blocks.shared.enums.Direction;

public abstract class AbstractMeshCreator {
    protected static final int NORMALS_PER_ROW = 12;
    protected static final int TRIANGLES_PER_ROW = 6;

    public static MeshData mirrorMesh(final MeshData mesh, final Axis... axis) {
        final MeshData mirroredMesh = mesh;
        final float[] mirroredVertices = new float[mesh.getVertices().length];
        final float[] mirroredNormals = new float[mesh.getNormals().length];
        boolean hasX = false;
        boolean hasY = false;
        boolean hasZ = false;
        for (Axis ax : axis) {
            if (ax.equals(Axis.X)) {
                hasX = true;
            } else if (ax.equals(Axis.Y)) {
                hasY = true;
            } else if (ax.equals(Axis.Z)) {
                hasZ = true;
            }
        }
        for (int index = 0; index < mesh.getVertices().length; index++) {
            if (index % 3 == 0) {
                if (hasX) {
                    mirroredVertices[index] = mesh.getVertices()[index] * -1F;
                    mirroredNormals[index] = mesh.getNormals()[index] * -1F;
                } else {
                    mirroredVertices[index] = mesh.getVertices()[index];
                    mirroredNormals[index] = mesh.getNormals()[index];
                }
            }
            if (index % 3 == 1) {
                if (hasY) {
                    mirroredVertices[index] = mesh.getVertices()[index] * -1F;
                    mirroredNormals[index] = mesh.getNormals()[index] * -1F;
                } else {
                    mirroredVertices[index] = mesh.getVertices()[index];
                    mirroredNormals[index] = mesh.getNormals()[index];
                }
            }
            if (index % 3 == 2) {
                if (hasZ) {
                    mirroredVertices[index] = mesh.getVertices()[index] * -1F;
                    mirroredNormals[index] = mesh.getNormals()[index] * -1F;
                } else {
                    mirroredVertices[index] = mesh.getVertices()[index];
                    mirroredNormals[index] = mesh.getNormals()[index];
                }
            }
            index++;
        }
        mirroredMesh.setVertices(mirroredVertices);
        mirroredMesh.setNormals(mirroredNormals);
        return mirroredMesh;
    }

    protected static MeshData makeStretchedBox(final float w, final float h, final float d) {
        final float[] boxVerts = {
        // X   Y   Z   X   Y   Z   X   Y   Z   X   Y   Z
          -w, -h,  d,  w, -h,  d,  w,  h,  d, -w,  h,  d,  // Front face
          -w, -h, -d, -w,  h, -d,  w,  h, -d,  w, -h, -d,  // Back face
          -w,  h, -d, -w,  h,  d,  w,  h,  d,  w,  h, -d,  // Top face
          -w, -h, -d,  w, -h, -d,  w, -h,  d, -w, -h,  d,  // Bottom face
           w, -h, -d,  w,  h, -d,  w,  h,  d,  w, -h,  d,  // Right face
          -w, -h, -d, -w, -h,  d, -w,  h,  d, -w,  h, -d,  // Left face
        };
        final float[] coords = getDualcubeCoords(w, h, d);
        final float[] normals = getCubeNormals();

        return new MeshData(boxVerts, getTriangles(normals.length / NORMALS_PER_ROW), normals, coords);
    }

    protected static float[] getDualcubeCoords(final float w, final float h, final float d) {
        final float[] coords = {
            0, 0, w, 0, w, h, 0, h, // Front face
            w, 0, w, h, 0, h, 0, 0, // Back face
            0, d, 0, 0, w, 0, w, d, // Top face
            w, d, 0, d, 0, 0, w, 0, // Bottom face
            d, 0, d, h, 0, h, 0, 0, // Right face
            0, 0, d, 0, d, h, 0, h, // Left face
        };
        return coords;
    }

    protected static float[] getCubeNormals() {
        final List<Dir> normalsDir = new ArrayList<Dir>();
        normalsDir.add(Dir.valueOf(Direction.Front, 1F));
        normalsDir.add(Dir.valueOf(Direction.Back, 1F));
        normalsDir.add(Dir.valueOf(Direction.Top, 1F));
        normalsDir.add(Dir.valueOf(Direction.Bottom, 1F));
        normalsDir.add(Dir.valueOf(Direction.Right, 1F));
        normalsDir.add(Dir.valueOf(Direction.Left, 1F));
        final float[] normals = getNormalsFor(normalsDir);
        return normals;
    }

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
