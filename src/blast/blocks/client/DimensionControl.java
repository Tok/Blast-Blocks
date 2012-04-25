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
import blast.blocks.client.mesh.Boxes;
import blast.blocks.client.mesh.Grid;
import blast.blocks.shared.enums.Key;
import blast.blocks.shared.enums.RotationType;
import blast.blocks.shared.enums.Shape;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;

public class DimensionControl extends AbstractThreeD {
    private static final double ROTATION_DURATION = 200D;

    private final Matrix3f nMatrix = new Matrix3f();

    private static final float TRANSLATE_Z_BLOCK = -10F;
    private static final float TRANSLATE_Z_GRID = -17.25F;
    private static final boolean IS_BLENDING = true;
    private static final boolean IS_LIGHTING = true;

    private static final float ALPHA = 0.5F;

    private static final Vector3f LIGHTNING_DIRECTION_VECTOR = new Vector3f(-0.25F, -0.25F, -1.00F);
    private static final Vector3f AMBIENT_COLOR_VECTOR = new Vector3f(0.2F, 0.2F, 0.2F);
    private static final Vector3f GRID_COLOR_VECTOR = new Vector3f(1.0F, 1.0F, 1.0F);
    private static final Vector3f BLOCK_COLOR_VECTOR = new Vector3f(0.2F, 0.8F, 0.2F);

    private static final float FOV_Y = 45F;
    private static final float ASPECT = 1F;
    private static final float Z_NEAR = 0.1F;
    private static final float Z_FAR = 100F;

    private Texture2D gridTexture;
    private Texture2D blockTexture;
    private StaticMesh gridMesh;
    private StaticMesh blockMesh;

    private double lastTime = 0.0D;
    private double elapsed = 0.0D;
    private double rotationTime = 0.0D;

    private RotationType rotationType = RotationType.NONE;
    private boolean isRotating = false;

//    private Direction xPointsTo = Direction.Right;
//    private Direction yPointsTo = Direction.Bottom;
//    private Direction zPointsTo = Direction.Front;

    private float xRot = 0.0F;
    private float yRot = 0.0F;
    private float zRot = 0.0F;
    private float rotateFromX = 0.0F;
    private float rotateToX = 0.0F;
    private float rotateFromY = 0.0F;
    private float rotateToY = 0.0F;
    private float rotateFromZ = 0.0F;
    private float rotateToZ = 0.0F;

    private final KeyboardManager keyboardManager = new KeyboardManager();

    protected DimensionControl() {
        super();
    }

    @Override
    public final void dispose() {
        gridMesh.dispose();
        blockMesh.dispose();
        gridTexture.dispose();
        blockTexture.dispose();
        keyboardManager.unmanage();
        getShader().dispose();

        getGl().disable(EnableCap.BLEND);
        getGl().enable(EnableCap.DEPTH_TEST);
    }

    @Override
    public final ClientBundleWithLookup getClientBundle() {
        return Resources.INSTANCE;
    }


    public final void updateShape(final Shape shape) {
        if (blockMesh != null) {
            blockMesh.dispose();
        }
        blockMesh = new StaticMesh(getGl(), Boxes.getMesh(shape));
        blockMesh.setPositionIndex(getShader().getAttributeLocation("aVertexPosition"));
        blockMesh.setNormalIndex(getShader().getAttributeLocation("aVertexNormal"));
        blockMesh.setTexCoordIndex(getShader().getAttributeLocation("aTextureCoord"));
    }

    @Override
    protected final void initImpl() {
        getSurface().setFocus(true);
        initImpl(Shape.LeftScrewTetracube);
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

        keyboardManager.manage(getSurface());
        keyboardManager.setPreventDefault(true);

        Resources.INSTANCE.grid().getTexture(new ResourceCallback<Texture2DResource>() {
            @Override
            public void onSuccess(final Texture2DResource resource) {
                gridTexture = resource.createTexture(getGl());
            }
            @Override
            public void onError(final ResourceException e) {
                Window.alert("Fail loading texture.");
            }
        });
        Resources.INSTANCE.block().getTexture(new ResourceCallback<Texture2DResource>() {
            @Override
            public void onSuccess(final Texture2DResource resource) {
                blockTexture = resource.createTexture(getGl());
            }
            @Override
            public void onError(final ResourceException e) {
                Window.alert("Fail loading texture.");
            }
        });

        getGl().activeTexture(TextureUnit.TEXTURE0);
        getGl().uniform1i(getShader().getUniformLocation("uSampler"), 0);

        PROJECTION.pushIdentity();
        PROJECTION.perspective(FOV_Y, ASPECT, Z_NEAR, Z_FAR);
        getGl().uniformMatrix(getShader().getUniformLocation("uPMatrix"), PROJECTION.get());
        PROJECTION.pop();

        //Grid:
        gridMesh = new StaticMesh(getGl(), Grid.makeGrid());
        gridMesh.setPositionIndex(getShader().getAttributeLocation("aVertexPosition"));
        gridMesh.setNormalIndex(getShader().getAttributeLocation("aVertexNormal"));
        gridMesh.setTexCoordIndex(getShader().getAttributeLocation("aTextureCoord"));

        //Block
        updateShape(shape);

//      MODELVIEW.translate(0F, 0F, TRANSLATE_Z_BLOCK);

        lastTime = System.currentTimeMillis();
    }

    @Override
    public final void update() {
        getGl().clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);

        double currTime = System.currentTimeMillis();
        elapsed = currTime - lastTime;
        lastTime = currTime;

        if (isRotating) {
            rotationTime += elapsed;
            if (rotationTime >= ROTATION_DURATION) {
                xRot = rotateToX % DEGREES_IN_DIRCLE;
                yRot = rotateToY % DEGREES_IN_DIRCLE;
                zRot = rotateToZ % DEGREES_IN_DIRCLE;
                rotationType = RotationType.NONE;
                isRotating = false;
            } else {
                xRot = rotateFromX + Double.valueOf((((rotateToX - rotateFromX) * rotationTime) / ROTATION_DURATION)).floatValue();
                yRot = rotateFromY + Double.valueOf((((rotateToY - rotateFromY) * rotationTime) / ROTATION_DURATION)).floatValue();
                zRot = rotateFromZ + Double.valueOf((((rotateToZ - rotateFromZ) * rotationTime) / ROTATION_DURATION)).floatValue();
            }
        } else {
            handleKeys();
        }

        if (gridTexture != null) {
            gridTexture.bind();
        }
        drawGrid();
        if (blockTexture != null) {
            blockTexture.bind();
        }
        drawBlock();
    }

    private void drawGrid() {
        PROJECTION.push();
        PROJECTION.translate(0, 0, TRANSLATE_Z_GRID);
        setProjectionMatrixUniforms();
        prepareBlenderAndLighting(GRID_COLOR_VECTOR);
        gridMesh.draw(); //XXX
        PROJECTION.pop();
    }

    private void drawBlock() {
//        getStatusLabel().setText(
//                "[X:" + rotateToX + "-->" + xPointsTo
//                + "][Y:" + rotateToY + "-->" + yPointsTo
//                + "][Z:" + rotateToZ + "-->" + zPointsTo
//                + "] Type:" + rotationType);
        getStatusLabel().setText("[X:" + rotateToX + "][Y:" + rotateToY + "][Z:" + rotateToZ + "] Type:" + rotationType);

        MODELVIEW.push();
        MODELVIEW.translate(0F, 0F, TRANSLATE_Z_BLOCK);
        MODELVIEW.rotateZ((float) Math.toRadians(zRot));
        MODELVIEW.rotateY((float) Math.toRadians(yRot));
        MODELVIEW.rotateX((float) Math.toRadians(xRot));
        setModelMatrixUniforms();
        prepareBlenderAndLighting(BLOCK_COLOR_VECTOR);
        blockMesh.draw();
        MODELVIEW.pop();
    }

    private void prepareBlenderAndLighting(final Vector3f colorVector) {
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
            getGl().uniform(getShader().getUniformLocation("uAmbientColor"), AMBIENT_COLOR_VECTOR);
            final Vector3f lightingDirection = LIGHTNING_DIRECTION_VECTOR;
            lightingDirection.normalize();
            getGl().uniform(getShader().getUniformLocation("uLightingDirection"), lightingDirection);
            getGl().uniform(getShader().getUniformLocation("uDirectionalColor"), colorVector);
        } else {
            getGl().uniform1i(getShader().getUniformLocation("uUseLighting"), 0);
        }
    }

    private void setModelMatrixUniforms() {
        getGl().uniformMatrix(getShader().getUniformLocation("uMVMatrix"), MODELVIEW.get());
        MODELVIEW.getInvertTranspose(nMatrix);
        getGl().uniformMatrix(getShader().getUniformLocation("uNMatrix"), nMatrix);
    }

    private void setProjectionMatrixUniforms() {
        getGl().uniformMatrix(getShader().getUniformLocation("uMVMatrix"), PROJECTION.get());
        PROJECTION.getInvertTranspose(nMatrix);
        getGl().uniformMatrix(getShader().getUniformLocation("uNMatrix"), nMatrix);
    }

    private void handleKeys() {
        if (isRotating) {
            return;
        }
        rotateFromX = xRot;
        rotateToX = xRot;
        rotateFromY = yRot;
        rotateToY = yRot;
        rotateFromZ = zRot;
        rotateToZ = zRot;
        if (keyboardManager.isButtonDown(Key.Q.getKeyCode())) {
            rotationType = RotationType.PLUS_X;
            rotateToX = (xRot + RIGHT_ANGLE);
            prepareRotation();
        }
        if (keyboardManager.isButtonDown(Key.W.getKeyCode())) {
            rotationType = RotationType.PLUS_Y;
            rotateToY = (yRot + RIGHT_ANGLE);
            prepareRotation();
        }
        if (keyboardManager.isButtonDown(Key.E.getKeyCode())) {
            rotationType = RotationType.PLUS_Z;
            rotateToZ = (zRot + RIGHT_ANGLE);
            prepareRotation();
        }
        if (keyboardManager.isButtonDown(Key.A.getKeyCode())) {
            rotationType = RotationType.MINUS_X;
            rotateToX = (xRot - RIGHT_ANGLE);
            prepareRotation();
        }
        if (keyboardManager.isButtonDown(Key.S.getKeyCode())) {
            rotationType = RotationType.MINUS_Y;
             rotateToY = (yRot - RIGHT_ANGLE);
            prepareRotation();
        }
        if (keyboardManager.isButtonDown(Key.D.getKeyCode())) {
            rotationType = RotationType.MINUS_Z;
            rotateToZ = (zRot - RIGHT_ANGLE);
            prepareRotation();
        }
    }

    private void prepareRotation() {
        isRotating = true;
        if (rotateToX > DEGREES_IN_DIRCLE) {
            rotateToX = rotateToX % DEGREES_IN_DIRCLE;
        }
        if (rotateToX < 0) {
            rotateFromX += DEGREES_IN_DIRCLE;
            rotateToX = DEGREES_IN_DIRCLE + rotateToX;
        }
        if (rotateToY > DEGREES_IN_DIRCLE) {
            rotateToY = rotateToY % DEGREES_IN_DIRCLE;
        }
        if (rotateToY < 0) {
            rotateFromY += DEGREES_IN_DIRCLE;
            rotateToY = DEGREES_IN_DIRCLE + rotateToY;
        }
        if (rotateToZ > DEGREES_IN_DIRCLE) {
            rotateToZ = rotateToZ % DEGREES_IN_DIRCLE;
        }
        if (rotateToZ < 0) {
            rotateFromZ += DEGREES_IN_DIRCLE;
            rotateToZ = DEGREES_IN_DIRCLE + rotateToZ;
        }
        rotationTime = 0.0D;
    }

    interface Resources extends ClientBundleWithLookup {
        Resources INSTANCE = GWT.create(Resources.class);
        @Source({ "shaders/vertex.vp", "shaders/fragment.fp" })
        ShaderResource shader();
        @Source("DimensionControl.java")
        ExternalTextResource source();
        @Source("images/GridTexture.png")
        @MagFilter(TextureMagFilter.LINEAR)
        @MinFilter(TextureMinFilter.LINEAR)
        ExternalTexture2DResource grid();
        @Source("images/BlockTexture.png")
        @MagFilter(TextureMagFilter.LINEAR)
        @MinFilter(TextureMinFilter.LINEAR)
        ExternalTexture2DResource block();
    }

}
