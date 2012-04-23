package blast.blocks.client;

import static gwt.g3d.client.math.MatrixStack.MODELVIEW;
import static gwt.g3d.client.math.MatrixStack.PROJECTION;
import gwt.g2d.client.input.KeyboardManager;
import gwt.g3d.client.gl2.enums.BlendingFactorDest;
import gwt.g3d.client.gl2.enums.BlendingFactorSrc;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import blast.blocks.shared.enums.Shape;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;

public class DimensionControl extends AbstractThreeD {
    private final Matrix3f nMatrix = new Matrix3f();
    private Texture2D texture;

    private static final float MILLISECONDS_PER_SECOND = 1000.0F;

    private static final float Z_CHANGE = 0.15F;
    private static final float INITIAL_Z_SPEED = -15F;
    private static final float INITIAL_X_SPEED = 15F;
    private static final float INITIAL_Y_SPEED = -15F;

    private static float xSpeed = INITIAL_Z_SPEED;
    private static float ySpeed = INITIAL_Z_SPEED;
    private static float translateZ = INITIAL_Z_SPEED;

    private static final boolean IS_BLENDING = true;
    private static final boolean IS_LIGHTING = true;

    private static final float ALPHA = 0.5F;

    private static final float AMBIENT_R = 0.2F;
    private static final float AMBIENT_G = 0.2F;
    private static final float AMBIENT_B = 0.2F;
    private static final Vector3f AMBIENT_VECTOR = new Vector3f(AMBIENT_R, AMBIENT_G, AMBIENT_B);

    private static final float DIRECTION_X = -0.25F;
    private static final float DIRECTION_Y = -0.25F;
    private static final float DIRECTION_Z = -1.00F;
    private static final Vector3f DIRECTION_VECTOR = new Vector3f(DIRECTION_X, DIRECTION_Y, DIRECTION_Z);

    private static final float COLOR_R = 0.2F;
    private static final float COLOR_G = 0.8F;
    private static final float COLOR_B = 0.2F;
    private static final Vector3f COLOR_VECTOR = new Vector3f(COLOR_R, COLOR_G, COLOR_B);

    private static final float FOV_Y = 45F;
    private static final float ASPECT = 1F;
    private static final float Z_NEAR = 0.1F;
    private static final float Z_FAR = 100F;

    private StaticMesh mesh;
    private double lastTime;
    private float xRot;
    private float yRot;

    private final KeyboardManager keyboardManager = new KeyboardManager();

    protected DimensionControl() {
        super();
    }

    @Override
    public final void dispose() {
        mesh.dispose();
        texture.dispose();
        keyboardManager.unmanage();
        getShader().dispose();

        getGl().disable(EnableCap.BLEND);
        getGl().enable(EnableCap.DEPTH_TEST);
    }

    @Override
    public final ClientBundleWithLookup getClientBundle() {
        return Resources.INSTANCE;
    }

    @Override
    public final void update() {
        getGl().clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);

        handleKeys();
        drawCube();

        double currTime = System.currentTimeMillis();
        double elapsed = currTime - lastTime;
        xRot += (xSpeed * elapsed) / MILLISECONDS_PER_SECOND;
        yRot += (ySpeed * elapsed) / MILLISECONDS_PER_SECOND;
        lastTime = currTime;
    }

    public final void updateShape(final Shape shape) {
        mesh.dispose();
        mesh = new StaticMesh(getGl(), Boxes.getMesh(shape));
        mesh.setPositionIndex(getShader().getAttributeLocation("aVertexPosition"));
        mesh.setNormalIndex(getShader().getAttributeLocation("aVertexNormal"));
        mesh.setTexCoordIndex(getShader().getAttributeLocation("aTextureCoord"));
    }

    @Override
    protected final void initImpl() {
        initImpl(Shape.BranchTetracube);
//      initImpl(Shape.Cube);
    }

    private void initImpl(final Shape shape) {
        try {
            ShaderResource shaderResource = ((ShaderResource) getClientBundle().getResource("shader"));
            setShader(shaderResource.createShader(getGl()));
            getShader().bind();
        } catch (ShaderException e) {
            Window.alert(e.getMessage());
            return;
        }

        xSpeed = INITIAL_X_SPEED;
        ySpeed = INITIAL_Y_SPEED;
        xRot = 0;
        yRot = 0;
        translateZ = INITIAL_Z_SPEED;

        keyboardManager.manage(getSurface());
        keyboardManager.setPreventDefault(true);

        PROJECTION.pushIdentity();
        PROJECTION.perspective(FOV_Y, ASPECT, Z_NEAR, Z_FAR);
        getGl().uniformMatrix(getShader().getUniformLocation("uPMatrix"), PROJECTION.get());
        PROJECTION.pop();

        mesh = new StaticMesh(getGl(), Boxes.getMesh(shape));
        mesh.setPositionIndex(getShader().getAttributeLocation("aVertexPosition"));
        mesh.setNormalIndex(getShader().getAttributeLocation("aVertexNormal"));
        mesh.setTexCoordIndex(getShader().getAttributeLocation("aTextureCoord"));

        getGl().activeTexture(TextureUnit.TEXTURE0);
        getGl().uniform1i(getShader().getUniformLocation("uSampler"), 0);

        Resources.INSTANCE.glass().getTexture(new ResourceCallback<Texture2DResource>() {
            @Override
            public void onSuccess(final Texture2DResource resource) {
                texture = resource.createTexture(getGl());
            }
            @Override
            public void onError(final ResourceException e) {
                Window.alert("Fail loading texture.");
            }
        });
        lastTime = System.currentTimeMillis();
    }

    private void drawCube() {
        MODELVIEW.push();
        MODELVIEW.translate(0, 0, translateZ);
        MODELVIEW.rotateX((float) Math.toRadians(xRot));
        MODELVIEW.rotateY((float) Math.toRadians(yRot));
        setMatrixUniforms();
        MODELVIEW.pop();
        if (IS_BLENDING) {
            getGl().blendFunc(BlendingFactorSrc.SRC_ALPHA, BlendingFactorDest.ONE);
            getGl().enable(EnableCap.BLEND);
            getGl().disable(EnableCap.DEPTH_TEST);
            getGl().uniform1f(getShader().getUniformLocation("uAlpha"), ALPHA);
        } else {
            getGl().disable(EnableCap.BLEND);
            getGl().enable(EnableCap.DEPTH_TEST);
        }
        if (IS_LIGHTING) {
            getGl().uniform1i(getShader().getUniformLocation("uUseLighting"), 1);
            getGl().uniform(getShader().getUniformLocation("uAmbientColor"), AMBIENT_VECTOR);
            final Vector3f lightingDirection = DIRECTION_VECTOR;
            lightingDirection.normalize();
//          lightingDirection.scale(-1); //might flicker
            getGl().uniform(getShader().getUniformLocation("uLightingDirection"), lightingDirection);
            getGl().uniform(getShader().getUniformLocation("uDirectionalColor"), COLOR_VECTOR);
        } else {
            getGl().uniform1i(getShader().getUniformLocation("uUseLighting"), 0);
        }
        mesh.draw();
    }

    private void setMatrixUniforms() {
        getGl().uniformMatrix(getShader().getUniformLocation("uMVMatrix"), MODELVIEW.get());
        MODELVIEW.getInvertTranspose(nMatrix);
        getGl().uniformMatrix(getShader().getUniformLocation("uNMatrix"), nMatrix);
    }

    private void handleKeys() {
        if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEUP)) {
            translateZ += Z_CHANGE;
        }
        if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEDOWN)) {
            translateZ -= Z_CHANGE;
        }
        if (keyboardManager.isButtonDown(KeyCodes.KEY_LEFT)) {
            ySpeed--;
        }
        if (keyboardManager.isButtonDown(KeyCodes.KEY_RIGHT)) {
            ySpeed++;
        }
        if (keyboardManager.isButtonDown(KeyCodes.KEY_UP)) {
            xSpeed--;
        }
        if (keyboardManager.isButtonDown(KeyCodes.KEY_DOWN)) {
            xSpeed++;
        }
    }

    interface Resources extends ClientBundleWithLookup {
        Resources INSTANCE = GWT.create(Resources.class);
        @Source({ "shaders/vertex.vp", "shaders/fragment.fp" })
        ShaderResource shader();
        @Source("DimensionControl.java")
        ExternalTextResource source();
        @Source("images/lightGray.gif")
        @MagFilter(TextureMagFilter.LINEAR)
        @MinFilter(TextureMinFilter.LINEAR)
        ExternalTexture2DResource glass();
    }
}