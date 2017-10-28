package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyDrawingEngine implements DrawingEngine {

    private static MyDrawingEngine drawingEngine = null;

    private LinkedList<Shape> shapes = new LinkedList<>();
    private LinkedList<Command> commands = new LinkedList<>();
    private Canvas canvas = new Canvas();
    private MyDrawingEngine() {};

    public synchronized MyDrawingEngine getInstance()
    {
        if(drawingEngine == null)
        {
            drawingEngine = new MyDrawingEngine();
        }
        return drawingEngine;
    }

    public void setShapes(LinkedList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        shapes.remove(oldShape);
        shapes.add(newShape);
    }

    @Override
    public Shape[] getShapes() {
        return (Shape[]) shapes.toArray();
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void save(String path) {
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

    @Override
    public void load(String path) {
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
    }
    private void writeTheShape(BufferedWriter outstream,TheShape shape) throws IOException {
        writeInt(outstream,shape.getTypeInd());
        writePoint(outstream,shape.getPosition());
        writeColor(outstream,shape.getColor());
        writeColor(outstream,shape.getFillColor());
        writeDoubleMap(outstream,shape.getProperties());
    }
    private  void writePoint(BufferedWriter outstream,Point point) throws IOException {
        writeInt(outstream,point.x);
        writeInt(outstream,point.y);
    }
    private void writeColor(BufferedWriter outstream,Color color) throws IOException {
        writeInt(outstream,color.getRGB());
    }
    private void writeInt(BufferedWriter outstream,int n) throws IOException {
        outstream.write(Integer.toString(n)+"\n");
    }
    private void writeDouble(BufferedWriter outstream,double n) throws IOException {
        outstream.write(Double.toString(n)+"\n");
    }
    private void writeDoubleMap(BufferedWriter outstream, Map<String, Double> map) throws IOException {
        writeInt(outstream,map.size());
        for(Map.Entry entry : map.entrySet())
        {
            outstream.write(entry.getKey()+"\n");
            writeDouble(outstream,(Double) entry.getValue());
        }
    }
    private int readInt(BufferedReader instream) throws IOException {
        return Integer.parseInt(instream.readLine());
    }
    private double readDouble(BufferedReader instream) throws IOException {
        return Double.parseDouble(instream.readLine());
    }
    private Map<String, Double> readDoubleMap(BufferedReader instream) throws IOException {
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
    private Point readPoint(BufferedReader instream) throws IOException {
        Point point = new Point();
        point.x = readInt(instream);
        point.y = readInt(instream);
        return point;
    }
    private Color readColor(BufferedReader instream) throws IOException {
        return new Color(readInt(instream));
    }
    private TheShape readTheShape(BufferedReader instream) throws IOException {
        TheShape theShape = TheShape.shapesFactory(readInt(instream));
        theShape.setPosition(readPoint(instream));
        theShape.setColor(readColor(instream));
        theShape.setFillColor(readColor(instream));
        theShape.setProperties(readDoubleMap(instream));
        return theShape;
    }
}
