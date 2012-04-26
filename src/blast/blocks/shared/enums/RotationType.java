package blast.blocks.shared.enums;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public enum RotationType {
    NONE(new Vector3f(0F, 0F, 0F)),
    MINUS_X(new Vector3f(-1F, 0F, 0F)),
    PLUS_X(new Vector3f(1F, 0F, 0F)),
    MINUS_Y(new Vector3f(0F, -1F, 0F)),
    PLUS_Y(new Vector3f(0F, 1F, 0F)),
    MINUS_Z(new Vector3f(0F, 0F, -1F)),
    PLUS_Z(new Vector3f(0F, 0F, 1F));

    private Vector3f vector3f;

    private RotationType(final Vector3f vector3f) {
        this.vector3f = vector3f;
    }

    public Vector3f getVector3f() {
        return vector3f;
    }

    public Vector3d getVector3d() {
        return new Vector3d(vector3f);
    }
}
