package loadmodel;

import org.joml.Vector3f;

public class Face {
    public Vector3f vertice = new Vector3f();
    public Vector3f normal = new Vector3f();

    public Face(Vector3f vertice) {
        this.vertice = vertice;
    }
}
