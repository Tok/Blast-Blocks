package blast.blocks.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import blast.blocks.shared.enums.Shape;

public class ShapeProvider {
//  private final EnumSet<Shape> allShapes = EnumSet.allOf(Shape.class);
    private final List<Shape> triCubes = new ArrayList<Shape>();
    private final List<Shape> tetraCubes = new ArrayList<Shape>();
    private final Random random = new Random();

    public ShapeProvider() {
        for (Shape shape : Shape.values()) {
            if (shape.getNumberOfCubes() == 4) {
                tetraCubes.add(shape);
            } else if (shape.getNumberOfCubes() == 3) {
                triCubes.add(shape);
            }
        }
    }

    public final Shape getRandomTricube() {
        return triCubes.get(random.nextInt(triCubes.size()));
    }

    public final Shape getRandomTetracube() {
        return tetraCubes.get(random.nextInt(tetraCubes.size()));
    }
}
