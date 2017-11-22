package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TextLoader {
    private static BufferedReader instream = null;
    public static synchronized LinkedList<Shape> load(String path) {
        LinkedList<Shape> shapes = new LinkedList<>();
        try {
            instream = new BufferedReader(new FileReader(path));
            int size = readInt();
            for (int i = 0; i < size; i++) {
                shapes.add(readTheShape());
            }
        } catch (Exception e) {
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                }
            }
        }
        instream = null;
        return shapes;
    }
    private static int readInt() throws IOException {
        return Integer.parseInt(instream.readLine());
    }
    private static String readString() throws IOException {
        return instream.readLine();
    }
    private static double readDouble() throws IOException {
        return Double.parseDouble(instream.readLine());
    }
    private static Map<String, Double> readDoubleMap() throws IOException {
        String state = readString();
        if(state.equals("NULL"))
            return null;
        HashMap<String, Double> map = new HashMap<>();
        int size = readInt();
        for (int i = 0;i < size;i++)
        {
            String key = readString();
            Double value = readDouble();
            map.put(key,value);
        }
        return map;
    }
    private static Point readPoint() throws IOException {
        String state = readString();
        if(state.equals("NULL"))
            return null;
        Point point = new Point();
        point.x = readInt();
        point.y = readInt();
        return point;
    }
    private static Color readColor() throws IOException {
        String state = readString();
        if(state.equals("NULL"))
            return null;
        double colorRed = readDouble();
        double colorGreen = readDouble();
        double colorBlue = readDouble();
        double colorOptacity = readDouble();
        Color color = Color.color(colorRed,colorGreen,colorBlue,colorOptacity);
        return color;
    }
    private static TheShape readTheShape() throws IOException {
        String state = readString();
        if(state.equals("NULL"))
            return null;
        String shapeName = readString();
        TheShape theShape = TheShape.shapesFactory(shapeName);
        Point position = readPoint();
        if(position != null)
            theShape.setPosition(position);
        Color color = readColor();
        if(color != null)
            theShape.setColor(color);
        Color fillColor = readColor();
        if(fillColor != null)
            theShape.setFillColor(fillColor);
        Map<String, Double> properties = readDoubleMap();
        if(properties != null)
            theShape.setProperties(properties);
        return theShape;
    }
}
