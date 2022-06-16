package projekt;

import static org.lwjgl.opengl.GL30.*;


import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Projekt extends AbstractOpenGLBase {
    private Matrix4 cubeMatrix1 = new Matrix4();
    private Matrix4 cubeMatrix2 = new Matrix4();
    private Matrix4 projectionMatrix1;
    private Matrix4 projectionMatrix2;
    private ShaderProgram shaderProgram1;
    private ShaderProgram shaderProgram2;
    private float angle;
    private float[] cube1;
    private float[] cube2;
    private float[] normals;
    private float[] colors;
    private int vaoId1;
    private int vaoId2;


    public static void main(String[] args) {
        new Projekt().start("CG Projekt", 700, 700);
    }

    @Override
    protected void init() {
        shaderProgram1 = new ShaderProgram("first_object");
        glUseProgram(shaderProgram1.getId());
        createArrays1();
        cube1Init();

        projectionMatrix1 = new Matrix4(1.0f, 100f);
        int camloc1 = glGetUniformLocation(shaderProgram1.getId(), "projectionMatrix");
        glUniformMatrix4fv(camloc1, false, projectionMatrix1.getValuesAsArray());

        shaderProgram2 = new ShaderProgram("second_object");
        glUseProgram(shaderProgram2.getId());
        createArrays2();
        cube2Init();

        int camloc2 = glGetUniformLocation(shaderProgram2.getId(), "projectionMatrix");
        glUniformMatrix4fv(camloc2, false, projectionMatrix1.getValuesAsArray());

        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
    }

    private void attachVBO(float[] values, int index, int size) {
        glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
        glBufferData(GL_ARRAY_BUFFER,
                values, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT,
                false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    private void cube1Init() {
        //Vertices
        vaoId1 = glGenVertexArrays();
        glBindVertexArray(vaoId1);

        attachVBO(cube1, 0, 3);
        //Colors zum VAO hinzufügen
        attachVBO(colors, 1, 3);
        // attachVBO(normals, 2, 3);
    }

    private void cube2Init() {
        //Vertices
        vaoId2 = glGenVertexArrays();
        glBindVertexArray(vaoId2);

        attachVBO(cube2, 0, 3);
        //Colors zum VAO hinzufügen
        attachVBO(colors, 1, 3);
        attachVBO(normals, 2, 3);
    }

    private void createArrays1() {
        // Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

        // cube and colors should be equal

        // cube has 6 faces, each face needs two triangles. triangle has 3 vertices. each vertex has 3 coordinates.

        cube1 = new float[]
                {
                        // front
                        -1.0f, 1.0f, -1.0f, //0
                        1.0f, 1.0f, -1.0f,//1
                        -1.0f, -1.0f, -1.0f,//2
                        -1.0f, -1.0f, -1.0f,//2
                        1.0f, 1.0f, -1.0f,//1
                        1.0f, -1.0f, -1.0f,//3

                        // back
                        1.0f, 1.0f, 1.0f,//5
                        -1.0f, 1.0f, 1.0f, //4
                        1.0f, -1.0f, 1.0f,//7
                        1.0f, -1.0f, 1.0f,//7
                        -1.0f, 1.0f, 1.0f, //4
                        -1.0f, -1.0f, 1.0f,//6

                        // right face
                        1.0f, 1.0f, -1.0f,//1
                        1.0f, 1.0f, 1.0f,//5
                        1.0f, -1.0f, -1.0f,//3
                        1.0f, -1.0f, -1.0f,//3
                        1.0f, 1.0f, 1.0f,//5
                        1.0f, -1.0f, 1.0f,//7

                        //left face
                        -1.0f, 1.0f, 1.0f, //4
                        -1.0f, 1.0f, -1.0f, //0
                        -1.0f, -1.0f, 1.0f,//6
                        -1.0f, -1.0f, 1.0f,//6
                        -1.0f, 1.0f, -1.0f, //0
                        -1.0f, -1.0f, -1.0f,//2

                        // top face
                        -1.0f, 1.0f, 1.0f, //4
                        1.0f, 1.0f, 1.0f,//5
                        -1.0f, 1.0f, -1.0f, //0
                        -1.0f, 1.0f, -1.0f, //0
                        1.0f, 1.0f, 1.0f,//5
                        1.0f, 1.0f, -1.0f,//1

                        // bottom face
                        -1.0f, -1.0f, -1.0f,//2
                        1.0f, -1.0f, -1.0f,//3
                        -1.0f, -1.0f, 1.0f,//6
                        -1.0f, -1.0f, 1.0f,//6
                        1.0f, -1.0f, -1.0f,//3
                        1.0f, -1.0f, 1.0f//7
                };


        colors = new float[]{
                // red
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

                //green
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,

                // blue
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                // yellow
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,

                // lila
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                //cyan
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f
        };
    }

    private void createArrays2() {
        // Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

        // cube and colors should be equal

        // cube has 6 faces, each face needs two triangles. triangle has 3 vertices. each vertex has 3 coordinates.
//int
        normals = new float[]
                {
                        // front face
                        0.0f, 0.0f, 1.0f, //0
                        0.0f, 0.0f, 1.0f, //0
                        0.0f, 0.0f, 1.0f, //0
                        0.0f, 0.0f, 1.0f, //0
                        0.0f, 0.0f, 1.0f, //0
                        0.0f, 0.0f, 1.0f, //0

                        // back face
                        0.0f, 0.0f, -1.0f, //0
                        0.0f, 0.0f, -1.0f, //0
                        0.0f, 0.0f, -1.0f, //0
                        0.0f, 0.0f, -1.0f, //0
                        0.0f, 0.0f, -1.0f, //0
                        0.0f, 0.0f, -1.0f, //0

                        // right face
                        1.0f, 0.0f, 0.0f,//1
                        1.0f, 0.0f, 0.0f,//1
                        1.0f, 0.0f, 0.0f,//1
                        1.0f, 0.0f, 0.0f,//1
                        1.0f, 0.0f, 0.0f,//1
                        1.0f, 0.0f, 0.0f,//1

                        // left face
                        -1.0f, 0.0f, 0.0f,//1
                        -1.0f, 0.0f, 0.0f,//1
                        -1.0f, 0.0f, 0.0f,//1
                        -1.0f, 0.0f, 0.0f,//1
                        -1.0f, 0.0f, 0.0f,//1
                        -1.0f, 0.0f, 0.0f,//1

                        // top face
                        0.0f, 1.0f, 0.0f,//1
                        0.0f, 1.0f, 0.0f,//1
                        0.0f, 1.0f, 0.0f,//1
                        0.0f, 1.0f, 0.0f,//1
                        0.0f, 1.0f, 0.0f,//1
                        0.0f, 1.0f, 0.0f,//1

                        // bottom face
                        0.0f, -1.0f, 0.0f,//1
                        0.0f, -1.0f, 0.0f,//1
                        0.0f, -1.0f, 0.0f,//1
                        0.0f, -1.0f, 0.0f,//1
                        0.0f, -1.0f, 0.0f,//1
                        0.0f, -1.0f, 0.0f,//1

                };

        cube2 = new float[]
                {
                        // front
                        -0.5f, 0.5f, -0.5f, //0
                        0.5f, 0.5f, -0.5f,//1
                        -0.5f, -0.5f, -0.5f,//2
                        -0.5f, -0.5f, -0.5f,//2
                        0.5f, 0.5f, -0.5f,//1
                        0.5f, -0.5f, -0.5f,//3

                        // back
                        0.5f, 0.5f, 0.5f,//5
                        -0.5f, 0.5f, 0.5f, //4
                        0.5f, -0.5f, 0.5f,//7
                        0.5f, -0.5f, 0.5f,//7
                        -0.5f, 0.5f, 0.5f, //4
                        -0.5f, -0.5f, 0.5f,//6

                        // right face
                        0.5f, 0.5f, -0.5f,//1
                        0.5f, 0.5f, 0.5f,//5
                        0.5f, -0.5f, -0.5f,//3
                        0.5f, -0.5f, -0.5f,//3
                        0.5f, 0.5f, 0.5f,//5
                        0.5f, -0.5f, 0.5f,//7

                        //left face
                        -0.5f, 0.5f, 0.5f, //4
                        -0.5f, 0.5f, -0.5f, //0
                        -0.5f, -0.5f, 0.5f,//6
                        -0.5f, -0.5f, 0.5f,//6
                        -0.5f, 0.5f, -0.5f, //0
                        -0.5f, -0.5f, -0.5f,//2

                        // top face
                        -0.5f, 0.5f, 0.5f, //4
                        0.5f, 0.5f, 0.5f,//5
                        -0.5f, 0.5f, -0.5f, //0
                        -0.5f, 0.5f, -0.5f, //0
                        0.5f, 0.5f, 0.5f,//5
                        0.5f, 0.5f, -0.5f,//1

                        // bottom face
                        -0.5f, -0.5f, -0.5f,//2
                        0.5f, -0.5f, -0.5f,//3
                        -0.5f, -0.5f, 0.5f,//6
                        -0.5f, -0.5f, 0.5f,//6
                        0.5f, -0.5f, -0.5f,//3
                        0.5f, -0.5f, 0.5f//7
                };


        colors = new float[]{
                // red
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

                //green
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,

                // blue
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                // yellow
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,

                // lila
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                //cyan
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f
        };
    }


    @Override
    public void update() {
        cubeMatrix1 = new Matrix4();
        cubeMatrix1.rotateY(0.8f);
        cubeMatrix1.rotateX(0.4f);

        //  cubeMatrix.scale(0.1f);
        cubeMatrix1.translate(0, 0, -7f);

        cubeMatrix2 = new Matrix4();

        cubeMatrix2.rotateY(angle);
        cubeMatrix2.rotateX(angle);

        cubeMatrix2.translate(2, 0, -10f);


        angle += 0.02f;
    }

    @Override
    protected void render() {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //changes background color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Matrix an Shader übertragen

        //cube
        glUseProgram(shaderProgram1.getId());

        int loc1 = glGetUniformLocation(shaderProgram1.getId(), "modelMatrix");
        glUniformMatrix4fv(loc1, false, cubeMatrix1.getValuesAsArray());
        glBindVertexArray(vaoId1);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        glUseProgram(shaderProgram2.getId());

        int loc2 = glGetUniformLocation(shaderProgram2.getId(), "modelMatrix");
        glUniformMatrix4fv(loc2, false, cubeMatrix2.getValuesAsArray());
        glBindVertexArray(vaoId2);
        glDrawArrays(GL_TRIANGLES, 0, 36);
    }

    @Override
    public void destroy() {
    }
}
