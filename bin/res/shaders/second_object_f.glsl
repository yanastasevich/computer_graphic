#version 330
in vec3 color;
out vec3 pixelColor;

void main() {
    pixelColor = vec3(1, 0, 0);
    vec2 mainPoint = gl_FragCoord.xy;
}
