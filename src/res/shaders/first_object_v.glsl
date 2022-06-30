#version 330
layout(location=0) in vec3 eckenAusJava;//gibt coord weiter
layout(location=1) in vec3 farbenAusJava;

uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;
out vec3 fragPos;

out vec3 color;
out vec3 normal;

void main() {
    //color
    color = farbenAusJava;
    gl_Position = projectionMatrix * modelMatrix * vec4(eckenAusJava, 1.0f);
    fragPos = vec3(modelMatrix * vec4(eckenAusJava, 1.0));

    normal = vec3(1, 0, 0);
}
