package eg.edu.alexu.csd.oop.draw.cs.fileManager;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.json.*;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JsonSaver {
    private static BufferedWriter outstream = null;

    public static synchronized void save(String path, LinkedList<Shape> shapes) {
        try {
            outstream = new BufferedWriter(new FileWriter(path));
            Value shapesValue = shapesToJsonValue(shapes);
            shapesValue.write(0,outstream);
        } catch (Exception e) {}
        finally {
            if (outstream != null) {
                try {
                    outstream.close();
                } catch (IOException e) {}
            }
        }
        outstream = null;
    }
    private static Value shapesToJsonValue(LinkedList<Shape> shapes) {
        HashMap<String,Value> shapesProb = new HashMap<>();
        Value[] shapesArray = new Value[shapes.size()];
        for (int i = 0;i < shapes.size();i++) {
            shapesArray[i] = shapeToJsonValue(shapes.get(i));
        }
        ArrayValue shapesArrayValue = new ArrayValue(shapesArray);
        shapesProb.put("shapes",shapesArrayValue);
        ObjectValue shapesValue = new ObjectValue(shapesProb);
        return  shapesValue;
    }

    private static Value shapeToJsonValue(Shape shape) {
        if(shape == null)
            return new BooleanValue(null);
        HashMap<String,Value> shapeProb = new HashMap<>();
        shapeProb.put("shapeName",new StringValue(shape.getClass().getName()));
        shapeProb.put("color",colorToJsonValue(shape.getColor()));
        shapeProb.put("fillColor",colorToJsonValue(shape.getFillColor()));
        shapeProb.put("position",pointToJsonValue(shape.getPosition()));
        shapeProb.put("properties",doubleMapToJsonValue(shape.getProperties()));
        ObjectValue shapeValue = new ObjectValue(shapeProb);
        return  shapeValue;
    }
    private static Value colorToJsonValue(Color color) {
        if(color == null)
            return new BooleanValue(null);
        HashMap<String,Double> colorHash = new HashMap<>();
        colorHash.put("Red",color.getRed());
        colorHash.put("Green",color.getGreen());
        colorHash.put("Blue",color.getBlue());
        colorHash.put("Opacity",color.getOpacity());
        return doubleMapToJsonValue(colorHash);
    }
    private static Value pointToJsonValue(Point point) {
        if(point == null)
            return new BooleanValue(null);
        HashMap<String,Value> pointProb = new HashMap<>();
        pointProb.put("x",new NumberValue(point.x));
        pointProb.put("y",new NumberValue(point.y));
        ObjectValue pointValue = new ObjectValue(pointProb);
        return  pointValue;
    }
    private static Value doubleMapToJsonValue(Map<String, Double> map) {
        if(map == null)
            return new BooleanValue(null);
        HashMap<String,Value> MapProb = new HashMap<>();
        for (Map.Entry entry : map.entrySet()) {
            MapProb.put((String) entry.getKey(),new StringValue(String.valueOf((Double)entry.getValue())));
        }
        ObjectValue MapValue = new ObjectValue(MapProb);
        return  MapValue;
    }
}
