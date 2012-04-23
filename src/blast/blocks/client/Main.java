package blast.blocks.client;

import gwt.g2d.client.util.FpsTimer;
import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.WebGLContextAttributes;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.DepthFunction;
import gwt.g3d.client.gl2.enums.EnableCap;
import blast.blocks.shared.enums.Shape;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int DESIRED_FPS = 60;
    private static final DimensionControl CONTROL = new DimensionControl();

    private final Label fpsLabel = new Label();
    private final ListBox shapeSelector = new ListBox();

    private Surface3D surface = new Surface3D(WIDTH, HEIGHT, new WebGLContextAttributes() {
        { setStencilEnable(true); }
    });

    public final void onModuleLoad() {
        WebGLContextAttributes contextAttribs = new WebGLContextAttributes();
        contextAttribs.setStencilEnable(true);
        surface = new Surface3D(WIDTH, HEIGHT, contextAttribs);

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(createWidget());
        RootPanel.get("page").add(mainPanel);

        final GL2 gl = surface.getGL();
        if (gl == null) {
            Window.alert("No WebGL context found. Exiting.");
            return;
        }

        gl.clearColor(0.0F, 0.0F, 0.0F, 1F); //black background
        gl.clearDepth(1);
        gl.viewport(0, 0, surface.getWidth(), surface.getHeight());

        gl.enable(EnableCap.DEPTH_TEST);
        gl.depthFunc(DepthFunction.LEQUAL);
        gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);

        run(gl);
    }

    private Widget createWidget() {
        Panel panel = new FlowPanel();
        panel.add(surface);
        panel.add(fpsLabel);
        for (Shape shape : Shape.values()) {
            shapeSelector.addItem(shape.name());
        }
        shapeSelector.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(final ChangeEvent event) {
                final String selection = shapeSelector.getItemText(shapeSelector.getSelectedIndex());
                final Shape shape = Shape.valueOf(selection);
                CONTROL.updateShape(shape);
            }
        });

        panel.add(shapeSelector);
        return panel;
    }

    private void run(final GL2 gl) {
        CONTROL.setSurface(surface);
        CONTROL.init(gl);

        FpsTimer timer = new FpsTimer(DESIRED_FPS) {
            @Override
            public void update() {
                fpsLabel.setText("FPS: " + getFps());
                CONTROL.update();
            }
        };
        timer.start();
    }

}
