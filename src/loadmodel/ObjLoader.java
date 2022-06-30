package loadmodel;


import projekt.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjLoader {

    public static float[] convertAsPrimitiveArray(List<Float> list) {
        float[] floatArray = new float[list.size()];
        for (int i = 0; i < list.size(); i++) floatArray[i] = list.get(i);
        return floatArray;
    }

    public static Model loadModel(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        List<Float> vertices = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Float> verticesCoords = new ArrayList<>();
        List<Float> normalsCoords = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {

            if (line.startsWith("v  ")) {
                float xValue = Float.parseFloat(line.split(" ")[2]);
                float yValue = Float.parseFloat(line.split(" ")[3]);
                float zValue = Float.parseFloat(line.split(" ")[4]);

                vertices.add(xValue);
                vertices.add(yValue);
                vertices.add(zValue);

            } else if (line.startsWith("vn ")) {
                float xValue = Float.parseFloat(line.split(" ")[1]);
                float yValue = Float.parseFloat(line.split(" ")[2]);
                float zValue = Float.parseFloat(line.split(" ")[3]);

                normals.add(xValue);
                normals.add(yValue);
                normals.add(zValue);

            } else if (line.startsWith("f ")) {
                parseCoords(line, vertices, verticesCoords, 0);
                parseCoords(line, normals, normalsCoords, 2);
            }
        }
        float[] verticesArr = convertAsPrimitiveArray(verticesCoords);
        float[] normalsArr = convertAsPrimitiveArray(normalsCoords);

        return new Model(verticesArr, normalsArr);
    }

    private static void parseCoords(String line, List<Float> actualData, List<Float> coords, int indexPos) {
        int a = Integer.parseInt(line.split(" ")[1].split("/")[indexPos]) - 1;
        int b = Integer.parseInt(line.split(" ")[2].split("/")[indexPos]) - 1;
        int c = Integer.parseInt(line.split(" ")[3].split("/")[indexPos]) - 1;
        int d = Integer.parseInt(line.split(" ")[4].split("/")[indexPos]) - 1;

        coords.add(actualData.get(a * 3));
        coords.add(actualData.get(a * 3 + 1));
        coords.add(actualData.get(a * 3 + 2));

        coords.add(actualData.get(b * 3));
        coords.add(actualData.get(b * 3 + 1));
        coords.add(actualData.get(b * 3 + 2));

        coords.add(actualData.get(c * 3));
        coords.add(actualData.get(c * 3 + 1));
        coords.add(actualData.get(c * 3 + 2));

        coords.add(actualData.get(a * 3));
        coords.add(actualData.get(a * 3 + 1));
        coords.add(actualData.get(a * 3 + 2));

        coords.add(actualData.get(c * 3));
        coords.add(actualData.get(c * 3 + 1));
        coords.add(actualData.get(c * 3 + 2));

        coords.add(actualData.get(d * 3));
        coords.add(actualData.get(d * 3 + 1));
        coords.add(actualData.get(d * 3 + 2));
    }
}
