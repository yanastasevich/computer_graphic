#version 330
layout(location=0) in vec3 eckenAusJava;//gibt coord weiter
layout(location=1) in vec3 farbenAusJava;
layout(location=2) in vec3 normalsAusJava;

uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;



out vec3 color;
out vec3 normal;




void main() {
    //color
    mat3 normalMatrix = transpose(inverse(mat3(modelMatrix)));

    color = farbenAusJava;
    normal = normalMatrix * normalsAusJava;
    gl_Position = projectionMatrix *modelMatrix * vec4(eckenAusJava, 1.0f);

}
