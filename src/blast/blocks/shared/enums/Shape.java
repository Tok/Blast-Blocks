package blast.blocks.shared.enums;


public enum Shape {
    Cube(1, true),
    Dualcube(2, true),
    DualcubeX(2, false),
//  DualcubeY(2, false),
//  DualcubeZ(2, false),
    ITricube(3, true),
    LTricube(3, true),
    ITetracube(4, true),
    LTetracube(4, true),
    STetracube(4, true),
    TTetracube(4, true),
    SquareTetracube(4, true),
    LeftScrewTetracube(4, true),
    RightScrewTetracube(4, true),
    BranchTetracube(4, true);

    private int numberOfCubes;
    private boolean isOriginal;

    private Shape(final int numberOfCubes, final boolean isOriginal) {
        this.numberOfCubes = numberOfCubes;
        this.isOriginal = isOriginal;
    }

    public int getNumberOfCubes() {
        return numberOfCubes;
    }

    public boolean isOriginal() {
        return isOriginal;
    }
}
