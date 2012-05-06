package blast.blocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;
import blast.blocks.shared.Cell;
import blast.blocks.shared.MatrixRotator;
import blast.blocks.shared.MatrixRotator.RotationDirection;

public abstract class AbstractReflectionTestCase extends TestCase {

    public final Cell[][] invokeRotateConcentric(final Cell[][] matrix, final RotationDirection rd)
                    throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        MatrixRotator matrixRotator = new MatrixRotator();
        Method method = MatrixRotator.class.getDeclaredMethod("rotateConcentric", Cell[][].class, RotationDirection.class);
        method.setAccessible(true);
        Cell[][] result = (Cell[][]) method.invoke(matrixRotator, matrix, rd);
        return result;
    }
}
