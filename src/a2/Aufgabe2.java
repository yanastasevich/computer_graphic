package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

    public static void main(String[] args) {
        new Aufgabe2().start("CG Aufgabe 2", 700, 700);
    }

    @Override
    protected void init() {
        // folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
        ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
        glUseProgram(shaderProgram.getId());

        float[] vertices = {
                -0.5f, -0.5f,
                0.5f, -0.5f,
                0.0f, 0.5f
        };

        float[] colors = {1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f};


        glBindVertexArray(glGenVertexArrays());

        createVBO(vertices, 0, 2);
        createVBO(colors, 1, 3);
    }

    private void createVBO(float[] values, int index, int size) {
        glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
        glBufferData(GL_ARRAY_BUFFER,
                values, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT,
                false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    @Override
    public void update() {
    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

        // hier vorher erzeugte VAOs zeichnen

        // count is number of vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    public void destroy() {
    }
}
