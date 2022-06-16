#version 330

in vec3 colors;
out vec3 pixelColor;

void main() {
   // pixelColor = vec3(1.0, 1.0, 1.0);
    pixelColor = colors;
    vec2 mainPoint = gl_FragCoord.xy;
}

