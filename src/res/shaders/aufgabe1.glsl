#version 330

out vec3 pixelColor;//Name beliebig

bool istImKreis(
vec2 distancePoint,
vec2 middlePoint,
in float radius
){
    //    if ((pow(xCoord2 - xCoord1, 2.0) + pow(yCoord2 - yCoord1, 2.0) <= radius * radius)){
    return distance(distancePoint, middlePoint) <= radius;
}

vec3 uebung1(vec2 rectanglePoint, int minX, int maxX, int minY, int maxY){
    if (rectanglePoint.x > minX && rectanglePoint.x < maxX && rectanglePoint.y > minY && rectanglePoint.y < maxY) {
        pixelColor = vec3(0.0, 1.0, 0.0);
    }
    return pixelColor;
}

vec3 uebung2(vec2 circleEdgePoint, vec2 circleMiddlePoint){
    float radius = 100.0;
    if (istImKreis(circleEdgePoint, circleMiddlePoint, radius)){
        pixelColor = vec3(1.0, 0.0, 0.0);
    }
    return pixelColor;
}

vec3 uebung2(vec2 circleEdgePoint, vec2 circleMiddlePoint){
    float radius = 100.0;
    if (istImKreis(circleEdgePoint, circleMiddlePoint, radius)){
        pixelColor = vec3(1.0, 0.0, 0.0);
    }
    return pixelColor;
}

vec3 uebung3(vec2 rectanglePoint, int minX, int maxX, int minY, int maxY, float angle){
    mat4 rotateMatrix = (cos(angle), sin(angle), -sin(angle), cos(angle));
    vec4 rectangleCoords = (minX, minY, maxY, maxX);
    vec4 rotateRectangleCoords = rotateMatrix * rectangleCoords;
    if (rectanglePoint.x > rotateRectangleCoords.x
    && rectanglePoint.x < rotateRectangleCoords.x
    && rectanglePoint.y > rotateRectangleCoords.y
    && rectanglePoint.y < rotateRectangleCoords.y) {
        pixelColor = vec3(0.0, 1.0, 0.0);
    }
    return pixelColor;
}


void main() {
    pixelColor = vec3(1.0, 1.0, 1.0);
    vec2 mainPoint = gl_FragCoord.xy;
    vec2 middlePoint = vec2(200.0, 200.0);

    // uebung1(mainPoint, 100, 600, 300, 500);
    uebung2(mainPoint, middlePoint);
    uebung3(mainPoint, 400, 800, 400, 700);
}
