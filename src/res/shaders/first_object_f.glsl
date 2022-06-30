#version 330
in vec3 color;
in vec3 normal;
out vec3 pixelColor;
uniform vec3 lightPos;
in vec3 fragPos;
out vec4 fragColor;

void main() {
    pixelColor = vec3(1, 0, 0);
    vec2 mainPoint = gl_FragCoord.xy;

    float ambientStrength = 0.1;
    vec3 ambient = ambientStrength * color;

    vec3 normalizedNormal = normalize(normal);
    vec3 lightDirection = normalize(lightPos - fragPos);
    float difference = max(dot(normalizedNormal, lightDirection), 0.0);
    vec3 diffuse = difference * color;

//    vec3 result = (ambient + diffuse) * pixelColor;
//    fragColor = vec4(result, 1.0);

}
