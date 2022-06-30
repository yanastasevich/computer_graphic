package projekt;

public class Model {
    float[] vertices;
    float[] normals;
    float[] textures;

    public Model(float[] vertices, float[] normals, float[] textures) {
        this.vertices = vertices;
        this.normals = normals;
        this.textures = textures;
    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextures() {
        return textures;
    }
}
