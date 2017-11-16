package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.json.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class JsonLoader {
    private static BufferedReader instream = null;
    public static synchronized LinkedList<Shape> load(String path) throws Exception {
        LinkedList<Shape> shapes = new LinkedList<>();
        try {
            instream = new BufferedReader(new FileReader(path));
            ObjectValue fileObject = (ObjectValue) ValueReader.read(instream);
            shapes = jsonObjectValueToShapes(fileObject);
        } catch (Exception e) {
            throw e;
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        instream = null;
        return shapes;
    }
    private static LinkedList<Shape> jsonObjectValueToShapes(ObjectValue fileObject) throws Exception {
        LinkedList<Shape> shapes = new LinkedList<>();
        Map<String, Value> fileMap = fileObject.getProp();
        ArrayValue shapesArrayValue = (ArrayValue) fileMap.get("shapes");
        Value[] shapesValues = (Value[]) shapesArrayValue.getArray();
        for(Value shapeObjectValue : shapesValues) {
            shapes.add(jsonObjectValueToShape(shapeObjectValue));
        }
        return shapes;
    }

    private static Shape jsonObjectValueToShape(Value shapeValue) throws Exception{
        Shape shape = null;
        try {
            if(shapeValue instanceof BooleanValue)
                return null;
            ObjectValue shapeObjectValue = (ObjectValue)shapeValue;
            Map<String, Value> shapeMap = shapeObjectValue.getProp();
            StringValue shapeNameStringValue = (StringValue)(shapeMap.get("shapeName"));
            String shapeName = shapeNameStringValue.getString();
            shape = (Shape) Class.forName(shapeName).newInstance();
            Value colorNumberValue = shapeMap.get("color");
            Color color = jsonObjectValueToColor(colorNumberValue);
            if(color != null)
                shape.setColor(color);
            Value fillColorNumberValue = shapeMap.get("fillColor");
            Color fillColor = jsonObjectValueToColor(fillColorNumberValue);
            if(fillColor != null)
                shape.setFillColor(fillColor);
            Value pointObjectValue = shapeMap.get("position");
            Point position = jsonObjectToPoint(pointObjectValue);
            if(position != null)
                shape.setPosition(position);
            Value doubleMapObjectValue = shapeMap.get("properties");
            Map<String, Double> properties = jsonObjectValueToDoubleMap(doubleMapObjectValue);
            if(properties != null)
                shape.setProperties(properties);
        } catch (Exception e) {
            throw e;
        }
        return shape;
    }
    private static Color jsonObjectValueToColor(Value colorValue) {
        if(colorValue instanceof BooleanValue)
            return null;
        Map<String, Double> colorMap = jsonObjectValueToDoubleMap(colorValue);
        Color color = Color.color(colorMap.get("Red"),colorMap.get("Green"),colorMap.get("Blue"));
        return color;
    }
    private static Point jsonObjectToPoint(Value pointValue){
        if(pointValue instanceof BooleanValue)
            return null;
        ObjectValue pointObjectValue = (ObjectValue) pointValue;
        Map<String, Value> pointMap = pointObjectValue.getProp();
        Point point = new Point();
        NumberValue xNumberValue = (NumberValue)(pointMap.get("x"));
        point.x = new Integer(String.valueOf(xNumberValue.getNumber()));
        NumberValue yNumberValue = (NumberValue)(pointMap.get("y"));
        point.y = new Integer(String.valueOf(yNumberValue.getNumber()));
        return point;
    }
    private static Map<String,Double> jsonObjectValueToDoubleMap(Value doubleMapValue) {
        if(doubleMapValue instanceof BooleanValue)
            return null;
        ObjectValue doubleMapObjectValue = (ObjectValue)doubleMapValue;
        Map<String, Double> map = new HashMap<>();
        Map<String, Value> doubleMapProb = doubleMapObjectValue.getProp();
        for (Map.Entry entry : doubleMapProb.entrySet()) {
            DoubleNumberValue value = (DoubleNumberValue)entry.getValue();
            map.put((String) entry.getKey(),value.getNumber());
        }
        return map;
    }

}
