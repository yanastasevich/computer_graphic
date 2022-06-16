package projekt;

import java.util.Arrays;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {
    private final int rows = 4;
    private final int columns = 4;
    private float[][] matrix = new float[4][4];

    public Matrix4() {
        matrix = new float[][]{
                {1, 0, 0, 0},    //Spalte1
                {0, 1, 0, 0},    //Spalte2
                {0, 0, 1, 0},    //Spalte3
                {0, 0, 0, 1}    //Spalte4
        };
    }

    public Matrix4(Matrix4 copy) {
        Arrays.copyOfRange(copy.getValuesAsArray(), 0, 16);
    }

    public Matrix4(float near, float far) {

        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = (-far - near) / (far - near);
        matrix[3][2] = -1;
        matrix[2][3] = (-2 * far * near) / (far - near);
        matrix[3][3] = 0;
    }

    public Matrix4 multiply(Matrix4 other) {
        float[][] newArray = new float[4][4];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                for (int i = 0; i < rows; i++) {
                    newArray[row][col] += other.matrix[row][i] * this.matrix[i][col];
                }
            }
        }
        this.matrix = newArray;
        return this;
    }

    public Matrix4 translate(float x, float y, float z) {
        Matrix4 matrix4 = new Matrix4();
        matrix4.matrix[0][0] = 1;
        matrix4.matrix[1][1] = 1;
        matrix4.matrix[2][2] = 1;
        matrix4.matrix[0][3] = x;
        matrix4.matrix[1][3] = y;
        matrix4.matrix[2][3] = z;
        matrix4.matrix[3][3] = 1;
        return this.multiply(matrix4);
    }

    public Matrix4 scale(float uniformFactor) {
        this.matrix[0][0] = uniformFactor;
        this.matrix[1][1] = uniformFactor;
        this.matrix[2][2] = uniformFactor;
        this.matrix[3][3] = 1;
        return this;
    }

    public Matrix4 scale(float sx, float sy, float sz) {
        Matrix4 matrix4 = new Matrix4();

        matrix4.matrix[0][0] = sx;
        matrix4.matrix[1][1] = sy;
        matrix4.matrix[2][2] = sz;
        matrix4.matrix[3][3] = 1;
        return this.multiply(matrix4);
    }

    public Matrix4 rotateX(float angle) {
        Matrix4 matrix4 = new Matrix4();
        matrix4.matrix[0][0] = 1;
        matrix4.matrix[1][1] = (float) Math.cos(angle);
        matrix4.matrix[1][2] = -(float) Math.sin(angle);
        matrix4.matrix[2][1] = (float) Math.sin(angle);
        matrix4.matrix[2][2] = (float) Math.cos(angle);
        matrix4.matrix[3][3] = 1;
        return this.multiply(matrix4);
    }

    public Matrix4 rotateY(float angle) {
        Matrix4 matrix4 = new Matrix4();

        matrix4.matrix[0][0] = (float) Math.cos(angle);
        matrix4.matrix[2][0] = (float) Math.sin(angle);
        matrix4.matrix[1][1] = 1;
        matrix4.matrix[0][2] = -(float) Math.sin(angle);
        matrix4.matrix[2][2] = (float) Math.cos(angle);
        matrix4.matrix[3][3] = 1;
        return this.multiply(matrix4);
    }

    public Matrix4 rotateZ(float angle) {
        Matrix4 matrix4 = new Matrix4();

        matrix4.matrix[0][0] = (float) Math.cos(angle);
        matrix4.matrix[1][0] = (float) Math.sin(angle);
        matrix4.matrix[0][1] = -(float) Math.sin(angle);
        matrix4.matrix[1][1] = (float) Math.cos(angle);
        matrix4.matrix[2][2] = 1;
        matrix4.matrix[3][3] = 1;
        return this.multiply(matrix4);
    }

    public float[] getValuesAsArray() {
        float[] newMatrix = new float[16];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                newMatrix[col * 4 + row] = this.matrix[row][col];
            }
        }
        return newMatrix;
    }
}

