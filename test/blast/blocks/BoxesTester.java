package blast.blocks;

import gwt.g3d.client.primitive.MeshData;
import gwt.g3d.client.primitive.PrimitiveFactory;
import junit.framework.TestCase;
import blast.blocks.client.mesh.BlockMesh;
import blast.blocks.shared.enums.Shape;

public class BoxesTester extends TestCase {

    public final void testBox() throws Exception {
        final MeshData comparisionMesh = PrimitiveFactory.makeBox();
        final MeshData myMesh = BlockMesh.getMesh(Shape.Cube);

        int index = 0;
        for (int triangle : comparisionMesh.getTriangles()) {
            assertEquals(triangle, myMesh.getTriangles()[index]);
            index++;
        }

        index = 0;
        for (float normal : comparisionMesh.getNormals()) {
            assertEquals(normal, myMesh.getNormals()[index]);
            index++;
        }

        index = 0;
        for (float coord : comparisionMesh.getTextCoords()) {
            assertEquals(coord, myMesh.getTextCoords()[index]);
            index++;
        }

        index = 0;
        for (float vertex : comparisionMesh.getVertices()) {
            assertEquals(vertex, myMesh.getVertices()[index]);
            index++;
        }
    }

}
