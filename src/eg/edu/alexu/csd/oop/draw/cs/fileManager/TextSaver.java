package eg.edu.alexu.csd.oop.draw.cs.fileManager;

import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class TextSaver {
    private static BufferedWriter outstream = null;
    public static synchronized void save(String path, LinkedList<Shape> shapes) {
        try {
            outstream = new BufferedWriter(new FileWriter(path));
            int shpaescount = shapes.size();
            writeInt(shpaescount);
            for (int i = 0;i < shpaescount;i++) {
                Shape shape = shapes.get(i);
                writeTheShape(shape);
            }
        } catch (Exception e) {
        }
        finally {
            if (outstream != null) {
                try {
                    outstream.close();
                } catch (IOException e) {}
            }
        }
        outstream = null;
    }
    private static void writeTheShape(Shape shape) throws IOException {
        if(shape == null)
        {
            writeString("NULL");
            return;
        }
        writeString("Shape");
        writeString(shape.getClass().getName());
        writePoint(shape.getPosition());
        writeColor(shape.getColor());
        writeColor(shape.getFillColor());
        writeDoubleMap(shape.getProperties());
    }
    private static void writePoint(Point point) throws IOException {
        if(point ==null) {
            writeString("NULL");
            return;
        }
        writeString("Point");
        writeInt(point.x);
        writeInt(point.y);
    }
    private static void writeColor(Color color) throws IOException {
        if(color == null) {
            writeString("NULL");
            return;
        }
        writeString("Color");
        writeDouble(color.getRed());
        writeDouble(color.getGreen());
        writeDouble(color.getBlue());
        writeDouble(color.getOpacity());
    }
    private static void writeInt(int n) throws IOException {
        outstream.write(Integer.toString(n)+"\n");
    }
    private static void writeString(String str) throws IOException {
        outstream.write(str+"\n");
    }
    private static void writeDouble(double n) throws IOException {
        outstream.write(Double.toString(n)+"\n");
    }
    private static void writeDoubleMap(Map<String, Double> map) throws IOException {
        if(map == null) {
            writeString("NULL");
            return;
        }
        writeString("Double Map");
        writeInt(map.size());
        for (Map.Entry<String,Double> entry : map.entrySet()) {
            writeString((String) entry.getKey());
            writeDouble((Double) entry.getValue());
        }
    }
}
