package blast.blocks.shared.enums;


public enum Shape {
    Cube(1),
    Dualcube(2),
    ITricube(3),
    LTricube(3),
    ITetracube(4),
    LTetracube(4),
    STetracube(4),
    TTetracube(4),
    SquareTetracube(4),
    LeftScrewTetracube(4),
    RightScrewTetracube(4),
    BranchTetracube(4);

    private int numberOfCubes;

    private Shape(final int numberOfCubes) {
        this.numberOfCubes = numberOfCubes;
    }

    public int getNumberOfCubes() {
        return numberOfCubes;
    }

}
