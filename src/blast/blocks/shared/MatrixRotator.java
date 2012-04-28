package blast.blocks.shared;

import java.util.Arrays;



public final class MatrixRotator {
    public enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE;
    }

    private MatrixRotator() {
    }

    public static Object[][] rotate(final Object[][] matrix, final RotationDirection rd) {
        int rowsToRotate = 0;
        int columnsToRotate = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (!matrix[row][col].equals(" ")) {
                    rowsToRotate = row;
                    columnsToRotate = col;
                }
            }
        }
        System.out.println("rows: " + rowsToRotate + " columns: " + columnsToRotate);
        return rotateConcentric(matrix, rd);
    }

    private static Object[][] rotateConcentric(final Object[][] matrix, final RotationDirection rd) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final Object[][] rotated = new Object[n][m];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rd.equals(RotationDirection.CLOCKWISE)) {
                    rotated[col][m - 1 - row] = matrix[row][col];
                }
                if (rd.equals(RotationDirection.COUNTERCLOCKWISE)) {
                    rotated[m - 1 - col][row] = matrix[row][col];
                }
            }
        }
        return rotated;
    }

    public static void printMatrix(final Object[][] matrix) {
        for (Object[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
