package blast.blocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;

public abstract class AbstractReflectionTestCase extends TestCase {

    public final Object[][] invokeRotateConcentric(final Object[][] matrix, final RotationDirection rd)
                    throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = MatrixRotator.class.getDeclaredMethod("rotateConcentric", Object[][].class, RotationDirection.class);
        method.setAccessible(true);
        Object[][] result = (Object[][]) method.invoke(null, matrix, rd);
        return result;
    }
}
