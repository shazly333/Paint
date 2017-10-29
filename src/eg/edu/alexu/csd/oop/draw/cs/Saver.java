package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Saver {


    public static void save(String path, LinkedList<Shape> shapes) {
        BufferedWriter outstream = null;
        try {
            outstream = new BufferedWriter(new FileWriter(path));
            writeInt(outstream,shapes.size());
            for (Shape shape : shapes) {
                writeTheShape(outstream,(TheShape)shape);
            }
        } catch (Exception e) {}
        finally {
            if (outstream != null) {
                try {
                    outstream.close();
                } catch (IOException e) {}
            }
        }
    }

    public static LinkedList<Shape> load(String path) {
        LinkedList<Shape> shapes = new LinkedList<>();
        BufferedReader instream = null;
        try {
            instream = new BufferedReader(new FileReader(path));
            int size = readInt(instream);
            for(int i = 0;i < size;i++)
            {
                shapes.add(readTheShape(instream));
            }
        }
        catch (Exception e) {}
        finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {}
            }
        }
        return shapes;
    }
    private static void writeTheShape(BufferedWriter outstream, TheShape shape) throws IOException {
        writeInt(outstream,shape.getTypeInd());
        writePoint(outstream,shape.getPosition());
        writeColor(outstream,shape.getColor());
        writeColor(outstream,shape.getFillColor());
        writeDoubleMap(outstream,shape.getProperties());
    }
    private static void writePoint(BufferedWriter outstream,Point point) throws IOException {
        writeInt(outstream,point.x);
        writeInt(outstream,point.y);
    }
    private static void writeColor(BufferedWriter outstream,Color color) throws IOException {
        writeInt(outstream,color.getRGB());
    }
    private static void writeInt(BufferedWriter outstream, int n) throws IOException {
        outstream.write(Integer.toString(n)+"\n");
    }
    private static void writeDouble(BufferedWriter outstream,double n) throws IOException {
        outstream.write(Double.toString(n)+"\n");
    }
    private static void writeDoubleMap(BufferedWriter outstream, Map<String, Double> map) throws IOException {
        writeInt(outstream,map.size());
        for(Map.Entry entry : map.entrySet())
        {
            outstream.write(entry.getKey()+"\n");
            writeDouble(outstream,(Double) entry.getValue());
        }
    }
    private static int readInt(BufferedReader instream) throws IOException {
        return Integer.parseInt(instream.readLine());
    }
    private static double readDouble(BufferedReader instream) throws IOException {
        return Double.parseDouble(instream.readLine());
    }
    private static Map<String, Double> readDoubleMap(BufferedReader instream) throws IOException {
        HashMap<String, Double> map = new HashMap<>();
        int size = readInt(instream);
        for (int i = 0;i < size;i++)
        {
            String key = instream.readLine();
            Double value = readDouble(instream);
            map.put(key,value);
        }
        return map;
    }
    private static Point readPoint(BufferedReader instream) throws IOException {
        Point point = new Point();
        point.x = readInt(instream);
        point.y = readInt(instream);
        return point;
    }
    private static Color readColor(BufferedReader instream) throws IOException {
        return new Color(readInt(instream));
    }
    private static TheShape readTheShape(BufferedReader instream) throws IOException {
        TheShape theShape = TheShape.shapesFactory(readInt(instream));
        theShape.setPosition(readPoint(instream));
        theShape.setColor(readColor(instream));
        theShape.setFillColor(readColor(instream));
        theShape.setProperties(readDoubleMap(instream));
        return theShape;
    }
}
