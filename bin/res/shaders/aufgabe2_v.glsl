#version 330

layout(location=0) in vec2 javaVertices;
layout(location=1) in vec3 javaColors;
out vec3 colors;

void main() {
    mat2 rotationMatrix = (mat2(cos(0.1f), -sin(0.1f), sin(0.1), cos(0.1f)));
    gl_Position = vec4(rotationMatrix * javaVertices, 0.0, 1.0);

    colors = javaColors;
}
