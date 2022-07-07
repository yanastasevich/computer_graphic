package projekt;

import static org.lwjgl.opengl.GL30.*;


import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;
import loadmodel.ObjLoader;
import lombok.SneakyThrows;

import java.io.IOException;

public class Projekt extends AbstractOpenGLBase {
    private Matrix4 dogMatrix = new Matrix4();
    private Matrix4 cubeMatrix = new Matrix4();
    private Matrix4 projectionMatrix;
    private ShaderProgram shaderProgram1;
    private ShaderProgram shaderProgram2;
    private float angle;
    private float[] dogVertices;
    private float[] dogNormals;
    private float[] dogTextures;
    private float[] cubeVertices;
    private float[] normalsCube;
    private float[] uvCube;
    private float[] colorsCube;
    private int vaoId1;
    private int vaoId2;
    Texture dogTexture;
    Texture cubeTexture;


    public static void main(String[] args) {
        new Projekt().start("CG Projekt", 700, 700);
    }

    @SneakyThrows
    @Override
    protected void init() {
        createArrays();

        shaderProgram1 = new ShaderProgram("first_object");
        glUseProgram(shaderProgram1.getId());
        dogInit();

        projectionMatrix = new Matrix4(1.0f, 100f);
        int camloc1 = glGetUniformLocation(shaderProgram1.getId(), "projectionMatrix");
        int lightPos1 = glGetUniformLocation(shaderProgram1.getId(), "lightPos1");
        int colorPoints1 = glGetUniformLocation(shaderProgram1.getId(), "colorPoints1");

        glUniformMatrix4fv(camloc1, false, projectionMatrix.getValuesAsArray());
        glUniform3f(lightPos1, -10, 20, 10);
        glUniform3f(colorPoints1, 1.0f, 1.0f, 1.0f);

        dogTexture = new Texture("/res/model/Australian_Cattle_Dog_dif.jpg", 5, true);



        shaderProgram2 = new ShaderProgram("second_object");
        glUseProgram(shaderProgram2.getId());
        cubeInit();

        int camloc2 = glGetUniformLocation(shaderProgram2.getId(), "projectionMatrix");
        int lightPos2 = glGetUniformLocation(shaderProgram1.getId(), "lightPos2");
      //  int colorPoints2 = glGetUniformLocation(shaderProgram1.getId(), "colorPoints2");


        glUniformMatrix4fv(camloc2, false, projectionMatrix.getValuesAsArray());
        glUniform3f(lightPos2, -10, 20, 10);
       // glUniform3f(colorPoints2, 0.25f, 0.25f, 0.25f);


        cubeTexture = new Texture("/res/model/wooden_texture.jpeg", 5, true);



        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
    }

    @SneakyThrows
    private Model loadDogModel() {
        Model model;
        try {
            model = ObjLoader.loadModel(getClass().getResourceAsStream("/res/model/australian_dog.obj"));
        } catch (RuntimeException e) {
            throw new IOException(e);
        }
        return model;
    }

    private void attachVBO(float[] values, int index, int size) {
        glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
        glBufferData(GL_ARRAY_BUFFER,
                values, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT,
                false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    private void dogInit() {
        vaoId1 = glGenVertexArrays();
        glBindVertexArray(vaoId1);

        attachVBO(dogVertices, 0, 3);
        attachVBO(dogNormals, 1, 3);
        attachVBO(dogTextures, 2, 3);
    }

    private void cubeInit() {
        vaoId2 = glGenVertexArrays();
        glBindVertexArray(vaoId2);

        attachVBO(cubeVertices, 0, 3);
      //  attachVBO(colorsCube, 1, 3);
        attachVBO(normalsCube, 1, 3);
        attachVBO(uvCube, 2, 2);
    }

    @SneakyThrows
    private void createArrays() {
        // Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

        // cube and colors should be equal

        // cube has 6 faces, each face needs two triangles. triangle has 3 vertices. each vertex has 3 coordinates.

        dogVertices = loadDogModel().getVertices();
        dogNormals = loadDogModel().getNormals();
        dogTextures = loadDogModel().getTextures();

        colorsCube = new float[]{
                // front
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

                // back
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,

                // right face
                0.5f, 0.5f, 1.0f,
                0.5f, 0.5f, 1.0f,
                0.5f, 0.5f, 1.0f,
                0.5f, 0.5f, 1.0f,
                0.5f, 0.5f, 1.0f,
                0.5f, 0.5f, 1.0f,

                //left face
                1.0f, 0.5f, 0.5f,
                1.0f, 0.5f, 0.5f,
                1.0f, 0.5f, 0.5f,
                1.0f, 0.5f, 0.5f,
                1.0f, 0.5f, 0.5f,
                1.0f, 0.5f, 0.5f,

                // top face
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                // bottom face
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
        };

        normalsCube = new float[]
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

        uvCube = new float[]
                {
                        // front
//                        -0.5f, 0.5f, //0
//                        0.5f, 0.5f,//1
//                        -0.5f, -0.5f,//2
//                        -0.5f, -0.5f,//2
//                        0.5f, 0.5f,//1
//                        0.5f, -0.5f,//3

                        0f, 0f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        1f, 1f,

                        // back
//                        0.5f, 0.5f,//5
//                        -0.5f, 0.5f, //4
//                        0.5f, -0.5f,//7
//                        0.5f, -0.5f,//7
//                        -0.5f, 0.5f, //4
//                        -0.5f, -0.5f,//6

                        1f, 0f,
                        0f, 0f,
                        1f, 1f,
                        1f, 1f,
                        0f, 0f,
                        0f, 1f,

                        // right face
//                        0.5f, -0.5f,//1
//                        0.5f, 0.5f,//5
//                        -0.5f, -0.5f,//3
//                        -0.5f, -0.5f,//3
//                        0.5f, 0.5f,//5
//                        -0.5f, 0.5f,//7

                        1f, 0f,
                        1f, 1f,
                        0f, 0f,
                        0f, 0f,
                        1f, 1f,
                        0f, 1f,

                        //left face
//                        0.5f, 0.5f, //4
//                        0.5f, -0.5f, //0
//                        -0.5f, 0.5f,//6
//                        -0.5f, 0.5f,//6
//                        0.5f, -0.5f, //0
//                        -0.5f, -0.5f,//2

                        1f, 1f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        0f, 0f,

                        // top face
//                        -0.5f, 0.5f, //4
//                        0.5f, 0.5f,//5
//                        -0.5f, -0.5f, //0
//                        -0.5f, -0.5f, //0
//                        0.5f, 0.5f,//5
//                        0.5f, -0.5f,//1

                        0f, 1f,
                        1f, 1f,
                        0f, 0f,
                        0f, 0f,
                        1f, 1f,
                        1f, 0f,

                        // bottom face
//                        -0.5f, -0.5f,//2
//                        0.5f, -0.5f,//3
//                        -0.5f, 0.5f,//6
//                        -0.5f, 0.5f,//6
//                        0.5f, -0.5f,//3
//                        0.5f, 0.5f//7

                        0f, 0f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        1f, 1f
                };

        cubeVertices = new float[]
                {
                        // back
                        -0.5f, 0.5f, -0.5f, //0
                        0.5f, 0.5f, -0.5f,//1
                        -0.5f, -0.5f, -0.5f,//2
                        -0.5f, -0.5f, -0.5f,//2
                        0.5f, 0.5f, -0.5f,//1
                        0.5f, -0.5f, -0.5f,//3

                        // front
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
    }

    @Override
    public void update() {
        dogMatrix = new Matrix4();
        float pivotX = 0;
        float pivotY = 0;
        dogMatrix.translate(pivotX, pivotY, -40);
        dogMatrix.rotateX(angle);
        dogMatrix.translate(-pivotX, -pivotY, -40f);
        dogMatrix.rotateZ(angle);

        // dogMatrix.rotateZ(angle);

        cubeMatrix = new Matrix4();
      //  cubeMatrix.multiply(dogMatrix);

       // cubeMatrix.rotateX(angle);
       // cubeMatrix.rotateY(angle);
        cubeMatrix.translate(2, 0, -7f);



        angle += 0.5f;
    }

    @Override
    protected void render() {
        glClearColor(1.0f, 1.0f, 1.0f, 0.5f); //changes background color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // matrix an shader uebertragen
        glUseProgram(shaderProgram1.getId());

        int loc1 = glGetUniformLocation(shaderProgram1.getId(), "modelMatrix");
        glUniformMatrix4fv(loc1, false, dogMatrix.getValuesAsArray());
        glBindVertexArray(vaoId1);
        glBindTexture(GL_TEXTURE_2D, dogTexture.getId());

        glDrawArrays(GL_TRIANGLES, 0, loadDogModel().getVertices().length / 3);

        glUseProgram(shaderProgram2.getId());

        int loc2 = glGetUniformLocation(shaderProgram2.getId(), "modelMatrix");
        glUniformMatrix4fv(loc2, false, cubeMatrix.getValuesAsArray());
        glBindVertexArray(vaoId2);
        glBindTexture(GL_TEXTURE_2D, cubeTexture.getId());

        glDrawArrays(GL_TRIANGLES, 0, 36);
    }

    @Override
    public void destroy() {
    }
}
