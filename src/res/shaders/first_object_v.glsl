#version 330
layout(location=0) in vec3 eckenAusJava;//gibt coord weiter
layout(location=1) in vec3 normalsFromJava;

uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;
out vec3 fragPos;

out vec3 color;
out vec3 normal;

void main() {
    color = vec3(1, 0, 0);
    gl_Position = projectionMatrix * modelMatrix * vec4(eckenAusJava, 1.0f);
    fragPos = vec3(modelMatrix * vec4(eckenAusJava, 1.0));

    normal = inverse(transpose(mat3(modelMatrix))) * normalsFromJava;
}
