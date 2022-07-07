#version 330
//in vec3 color;
in vec3 normal;
in vec2 uvCoord;
in vec3 fragPos;

uniform vec3 lightPos2;
uniform sampler2D sampler2;
//uniform vec3 colorPoints2;


out vec4 FragColor;

void main() {
//    float ambientStrength = 0.1;
//    vec3 ambient = ambientStrength * colorPoints2;
//
//    vec3 normalizedNormal = normalize(normal);
//    vec3 directionToLight = normalize(lightPos2 - fragPos);
//    float angle = max(dot(normalizedNormal, directionToLight), 0.0);
//    vec3 diffuse = angle * colorPoints2;

    vec4 texel = texture(sampler2, uvCoord);

 //   vec4 phong = vec4((ambient + diffuse) * colorPoints2, 1.0f);

    FragColor = texel;
}
