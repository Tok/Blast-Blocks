package blast.blocks.client;

import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.GLDisposable;
import gwt.g3d.client.shader.AbstractShader;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractThreeD implements GLDisposable {
    private GL2 gl;
    private Surface3D surface;
    private AbstractShader shader;

    protected AbstractThreeD() {
    }

    public final GL2 getGl() {
        return gl;
    }

    public final void setSurface(final Surface3D surface) {
        this.surface = surface;
    }

    public final Surface3D getSurface() {
        return surface;
    }

    public final AbstractShader getShader() {
        return shader;
    }

    public final void setShader(final AbstractShader shader) {
        this.shader = shader;
    }

    public final void init(final GL2 gl) {
        this.gl = gl;
        initImpl();
    }

    protected abstract void initImpl();

    public void update() {
    }

    public final Widget getExtraWidget() {
        return null;
    }

    public abstract ClientBundleWithLookup getClientBundle();
}
