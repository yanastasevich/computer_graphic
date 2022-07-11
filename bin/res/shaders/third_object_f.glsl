#version 330
//in vec3 color;
in vec3 normal;
in vec2 uvCoord;
in vec3 fragPos;

uniform sampler2D sampler3;
//uniform vec3 colorPoints2;


out vec4 FragColor;

void main() {
    vec4 texel = texture(sampler3, uvCoord);

    FragColor = texel;
}
