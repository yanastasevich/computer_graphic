#version 330
in vec3 normal;
in vec2 uvCoord;
in vec3 fragPos;

uniform vec3 lightPos1;
uniform vec3 colorPoints1;
uniform sampler2D sampler;

out vec3 pixelColor;
out vec4 FragColor;

void main() {
    float ambientStrength = 0.1;
    vec3 ambient = ambientStrength * colorPoints1;

    vec3 normalizedNormal = normalize(normal);
    vec3 directionToLight = normalize(lightPos1 - fragPos);
    float angle = max(dot(normalizedNormal, directionToLight), 0.0);
    vec3 diffuse = angle * colorPoints1;

    float specularStrength = 1.5;
    vec3 reflectDirect = reflect(-directionToLight, normalizedNormal);
    vec3 directionToCamera = normalize(-fragPos);
    float spec = pow(max(dot(directionToCamera, reflectDirect), 0.0), 32);
    vec3 specular = specularStrength * spec * vec3(1,1,1);

    vec4 phong = vec4((ambient + diffuse + specular) * colorPoints1, 1.0f);
    vec4 texel = texture(sampler, uvCoord);

    FragColor = texel * phong;
}
