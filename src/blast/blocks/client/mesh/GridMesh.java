package blast.blocks.client.mesh;

import gwt.g3d.client.primitive.MeshData;
import java.util.ArrayList;
import java.util.List;
import blast.blocks.shared.Dir;
import blast.blocks.shared.Field;
import blast.blocks.shared.enums.Direction;

public final class GridMesh extends AbstractMeshCreator {

    private GridMesh() { }

    public static MeshData makeGrid(final Field field) {
        float w = field.getColumns();
        float h = field.getRows();
        float d = field.getDepth();
        final float[] boxVerts = {
        // X   Y   Z   X   Y   Z   X   Y   Z   X   Y   Z
          -w, -h, -d, -w,  h, -d,  w,  h, -d,  w, -h, -d,  // Back face
          -w,  h, -d, -w,  h,  d,  w,  h,  d,  w,  h, -d,  // Top face
          -w, -h, -d,  w, -h, -d,  w, -h,  d, -w, -h,  d,  // Bottom face
           w, -h, -d,  w,  h, -d,  w,  h,  d,  w, -h,  d,  // Right face
          -w, -h, -d, -w, -h,  d, -w,  h,  d, -w,  h, -d,  // Left face
        };
        final float[] coords = {
            w, 0, w, h, 0, h, 0, 0,  // Back face
            0, d, 0, 0, w, 0, w, d,  // Top face
            w, d, 0, d, 0, 0, w, 0,  // Bottom face
            d, 0, d, h, 0, h, 0, 0,  // Right face
            0, 0, d, 0, d, h, 0, h,  // Left face
        };

        final List<Dir> normalsDir = new ArrayList<Dir>();
        normalsDir.add(Dir.valueOf(Direction.Back, 1F));
        normalsDir.add(Dir.valueOf(Direction.Top, 1F));
        normalsDir.add(Dir.valueOf(Direction.Bottom, 1F));
        normalsDir.add(Dir.valueOf(Direction.Right, 1F));
        normalsDir.add(Dir.valueOf(Direction.Left, 1F));
        final float[] normals = getNormalsFor(normalsDir);

        return new MeshData(boxVerts, getTriangles(normalsDir.size()), normals, coords);
    }
}
