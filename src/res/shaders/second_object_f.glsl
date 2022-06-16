#version 330
in vec3 color;
out vec3 pixelColor;

void main() {
    pixelColor = color;
    vec2 mainPoint = gl_FragCoord.xy;
}
