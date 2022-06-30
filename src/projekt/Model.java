package projekt;

public class Model {
    float[] vertices;
    float[] normals;

    public Model(float[] vertices, float[] normals) {
        this.vertices = vertices;
        this.normals = normals;
    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }
}
