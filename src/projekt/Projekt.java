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
    private Matrix4 cubeMatrix1 = new Matrix4();
    private Matrix4 cubeMatrix2 = new Matrix4();
    private Matrix4 projectionMatrix;
    private ShaderProgram shaderProgram1;
    private ShaderProgram shaderProgram2;
    private ShaderProgram shaderProgram3;
    private float angle;
    private float[] dogVertices;
    private float[] dogNormals;
    private float[] dogTextures;
    private float[] cubeVertices1;
    private float[] cubeVertices2;
    private float[] normalsCube1;
    private float[] normalsCube2;
    private float[] uvCube1;
    private float[] uvCube2;
    private int vaoId1;
    private int vaoId2;
    private int vaoId3;
    Texture dogTexture;
    Texture cubeTexture1;
    Texture cubeTexture2;


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
        cubeInit1();

        int camloc2 = glGetUniformLocation(shaderProgram2.getId(), "projectionMatrix");
        int lightPos2 = glGetUniformLocation(shaderProgram1.getId(), "lightPos2");

        glUniformMatrix4fv(camloc2, false, projectionMatrix.getValuesAsArray());
        glUniform3f(lightPos2, -10, 20, 10);

        cubeTexture1 = new Texture("/res/model/maxi_texture.jpeg");


        shaderProgram3 = new ShaderProgram("third_object");
        glUseProgram(shaderProgram3.getId());
        cubeInit2();

        int camloc3 = glGetUniformLocation(shaderProgram2.getId(), "projectionMatrix");

        glUniformMatrix4fv(camloc3, false, projectionMatrix.getValuesAsArray());

        cubeTexture2 = new Texture("/res/model/mini_texture.jpg");


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

    private void cubeInit1() {
        vaoId2 = glGenVertexArrays();
        glBindVertexArray(vaoId2);

        attachVBO(cubeVertices1, 0, 3);
        attachVBO(normalsCube1, 1, 3);
        attachVBO(uvCube1, 2, 2);
    }

    private void cubeInit2() {
        vaoId3 = glGenVertexArrays();
        glBindVertexArray(vaoId3);

        attachVBO(cubeVertices2, 0, 3);
        attachVBO(normalsCube2, 1, 3);
        attachVBO(uvCube2, 2, 2);
    }

    @SneakyThrows
    private void createArrays() {
        // Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

        // cube and colors should be equal

        // cube has 6 faces, each face needs two triangles. triangle has 3 vertices. each vertex has 3 coordinates.

        dogVertices = loadDogModel().getVertices();
        dogNormals = loadDogModel().getNormals();
        dogTextures = loadDogModel().getTextures();

        normalsCube1 = new float[]
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

        uvCube1 = new float[]
                {
                        0f, 0f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        1f, 1f,

                        1f, 0f,
                        0f, 0f,
                        1f, 1f,
                        1f, 1f,
                        0f, 0f,
                        0f, 1f,

                        1f, 0f,
                        1f, 1f,
                        0f, 0f,
                        0f, 0f,
                        1f, 1f,
                        0f, 1f,

                        1f, 1f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        0f, 0f,

                        0f, 1f,
                        1f, 1f,
                        0f, 0f,
                        0f, 0f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        1f, 0f,
                        0f, 1f,
                        0f, 1f,
                        1f, 0f,
                        1f, 1f
                };

        cubeVertices1 = new float[]
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
        normalsCube2 = normalsCube1;
        cubeVertices2 = cubeVertices1;
        uvCube2 = uvCube1;
    }

    @Override
    public void update() {
        cubeMatrix1 = new Matrix4();
        cubeMatrix1.rotateX(angle);
        cubeMatrix1.translate(0, 0, -5f);

        dogMatrix = new Matrix4();
        dogMatrix.rotateY(angle);
        dogMatrix.translate(0, 0, -2f);
        dogMatrix.scale(0.5f);

        dogMatrix.multiply(cubeMatrix1);

        cubeMatrix2 = new Matrix4();
        cubeMatrix2.rotateX(angle);
        cubeMatrix2.translate(-2.5f, 0, -5f);

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
        glUniformMatrix4fv(loc2, false, cubeMatrix1.getValuesAsArray());
        glBindVertexArray(vaoId2);
        glBindTexture(GL_TEXTURE_2D, cubeTexture1.getId());

        glDrawArrays(GL_TRIANGLES, 0, 36);

        glUseProgram(shaderProgram3.getId());


        int loc3 = glGetUniformLocation(shaderProgram3.getId(), "modelMatrix");
        glUniformMatrix4fv(loc3, false, cubeMatrix2.getValuesAsArray());
        glBindVertexArray(vaoId3);
        glBindTexture(GL_TEXTURE_2D, cubeTexture2.getId());

        glDrawArrays(GL_TRIANGLES, 0, 36);
    }

    @Override
    public void destroy() {
    }
}
