package loadmodel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjLoader {

    public static float[] convertAsPrimitiveArray(List<Float> list) {
        float[] floatArray = new float[list.size()];
        for (int i = 0; i < list.size(); i++) floatArray[i] = list.get(i);
        return floatArray;
    }

    public static float[] loadModel(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Model model = new Model();
        String line;

        List<Float> vertices = new ArrayList<>();
        List<Integer> faces = new ArrayList<>();
        List<Float> actualCoords = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {

            if (line.startsWith("v  ")) {
                // System.out.println(line.split(" ")[1]);

                float xValue = Float.parseFloat(line.split(" ")[2]);
                float yValue = Float.parseFloat(line.split(" ")[3]);
                float zValue = Float.parseFloat(line.split(" ")[4]);

                vertices.add(xValue);
                vertices.add(yValue);
                vertices.add(zValue);

                // model.vertices.add(new Vector3f(xValue, yValue, zValue));
            }
//            else if (line.startsWith("vn ")) {
//                float xValue = Float.parseFloat(line.split(" ")[1]);
//                float yValue = Float.parseFloat(line.split(" ")[2]);
//                float zValue = Float.parseFloat(line.split(" ")[3]);
//
//                model.normals.add(new Vector3f(xValue, yValue, zValue));
//            }
            else if (line.startsWith("f ")) {
                // index nehmen, sehen welche xyz koords passen, in array list  hinzufuegen

                int a = Integer.parseInt(line.split(" ")[1].split("/")[0]) - 1;
                int b = Integer.parseInt(line.split(" ")[2].split("/")[0]) - 1;
                int c = Integer.parseInt(line.split(" ")[3].split("/")[0]) - 1;
                int d = Integer.parseInt(line.split(" ")[4].split("/")[0]) - 1;

                actualCoords.add(vertices.get(a * 3));
                actualCoords.add(vertices.get(a * 3 + 1));
                actualCoords.add(vertices.get(a * 3 + 2));

                actualCoords.add(vertices.get(b * 3));
                actualCoords.add(vertices.get(b * 3 + 1));
                actualCoords.add(vertices.get(b * 3 + 2));

                actualCoords.add(vertices.get(c * 3));
                actualCoords.add(vertices.get(c * 3 + 1));
                actualCoords.add(vertices.get(c * 3 + 2));

                actualCoords.add(vertices.get(a * 3));
                actualCoords.add(vertices.get(a * 3 + 1));
                actualCoords.add(vertices.get(a * 3 + 2));

                actualCoords.add(vertices.get(c * 3));
                actualCoords.add(vertices.get(c * 3 + 1));
                actualCoords.add(vertices.get(c * 3 + 2));

                actualCoords.add(vertices.get(d * 3));
                actualCoords.add(vertices.get(d * 3 + 1));
                actualCoords.add(vertices.get(d * 3 + 2));
            }
        }

        return convertAsPrimitiveArray(actualCoords);
    }
}
