package eg.edu.alexu.csd.oop.draw.cs.fileManager;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.shapes.TheShape;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.Point;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class XmlLoader {
    private static Document document =  null;
    public static synchronized LinkedList<Shape> load(String path) {
        LinkedList<Shape> shapes = new LinkedList<>();
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("shape");
            int size = nList.getLength();
            for (int i = 0; i < size; i++) {
                shapes.add(readTheShape((Element)nList.item(i)));
            }
        } catch (Exception e) {
        }
        document = null;
        return shapes;
    }

    private static Map<String, Double> readDoubleMap(Element mapElement) {
        if(!isExist(mapElement))
            return null;
        HashMap<String, Double> map = new HashMap<>();
        NodeList nList = mapElement.getElementsByTagName("field");
        int size = nList.getLength();
        for (int i = 0; i < size; i++) {
            Element fieldElement = (Element)nList.item(i);
            Double value = Double.parseDouble(fieldElement.getAttribute("value"));
            String key = fieldElement.getAttribute("key");
            map.put(key,value);
        }
        return map;
    }
    private static Point readPoint(Element pointElement){
        if(!isExist(pointElement))
            return null;
        int x = Integer.parseInt(pointElement.getAttribute("x"));
        int y = Integer.parseInt(pointElement.getAttribute("y"));
        return new Point(x,y);
    }
    private static Color readColor(Element colorElement){
        if(!isExist(colorElement))
            return null;
        Double red = Double.parseDouble(colorElement.getAttribute("red"));
        Double green = Double.parseDouble(colorElement.getAttribute("green"));
        Double blue = Double.parseDouble(colorElement.getAttribute("blue"));
        Double opacity = Double.parseDouble(colorElement.getAttribute("opacity"));
        return Color.color(red,blue,green,opacity);
    }
    private static TheShape readTheShape(Element shapeElement) {
        if(!isExist(shapeElement))
            return null;
        String shapeClass = shapeElement.getAttribute("class");
        System.out.println(shapeClass);
        TheShape theShape = TheShape.shapesFactory(shapeClass);
        Point position = readPoint((Element) shapeElement.getElementsByTagName("point").item(0));
        if(position != null)
            theShape.setPosition(position);
        NodeList colorNodeList = shapeElement.getElementsByTagName("color");
        int size = colorNodeList.getLength();
        for (int i = 0; i < size; i++) {
            Element fieldElement = (Element) colorNodeList.item(i);
            String filedName = fieldElement.getAttribute("name");
            if(filedName.equals("color")) {
                Color color = readColor(fieldElement);
                if(color != null)
                    theShape.setColor(color);
            }
            else if (filedName.equals("fillcolor")) {
                Color fillColor = readColor(fieldElement);
                if(fillColor != null)
                    theShape.setFillColor(fillColor);
            }
        }
        Map<String, Double> properties = readDoubleMap((Element) shapeElement.getElementsByTagName("doublemap").item(0));
        if(properties != null)
            theShape.setProperties(properties);
        return theShape;
    }
    private static boolean isExist(Element element) {
        return element.getAttribute("exist").equals("yes");
    }
}
