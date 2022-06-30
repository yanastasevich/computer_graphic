#version 330
in vec3 color;
in vec3 normal;
out vec3 pixelColor;
uniform vec3 lightPos;
in vec3 fragPos;

void main() {
    vec2 mainPoint = gl_FragCoord.xy;

    float ambientStrength = 0.1;
    vec3 ambient = ambientStrength * color;

    vec3 normalizedNormal = normalize(normal);
    vec3 directionToLight = normalize(lightPos - fragPos);
    float angle = max(dot(normalizedNormal, directionToLight), 0.0);
    vec3 diffuse = angle * color;

    float specularStrength = 1.5;
    vec3 reflectDirect = reflect(-directionToLight, normalizedNormal);
    vec3 directionToCamera = normalize(-fragPos);
    float spec = pow(max(dot(directionToCamera, reflectDirect), 0.0), 32);
    vec3 specular = specularStrength * spec * vec3(1,1,1);

    vec3 result = (ambient + diffuse) * pixelColor;
    pixelColor = diffuse + ambient + specular;
}
