package eg.edu.alexu.csd.oop.draw.cs.fileManager;

import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.Point;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;

public class XmlSaver {
    private static Document document = null;
    public static synchronized void save(String path, LinkedList<Shape> shapes) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.newDocument();
            writeShapes(shapes);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document = null;
    }
    private static void writeShapes(LinkedList<Shape> shapes) {
        Element rootElement = document.createElement("shapes");
        document.appendChild(rootElement);
        for(Shape shape : shapes) {
            writeShape(shape,rootElement);
        }
    }
    private static void writeShape(Shape shape,Element rootElement) {
        Element shapeElement = document.createElement("shape");
        if(shape == null) {
            shapeElement.setAttribute("exist","no");
        }
        else {
            shapeElement.setAttribute("exist","yes");
            shapeElement.setAttribute("class", shape.getClass().getName());
            writePoint(shape.getPosition(),shapeElement,"position");
            writeColor(shape.getColor(),shapeElement,"color");
            writeColor(shape.getFillColor(),shapeElement,"fillcolor");
            writeDoubleMap(shape.getProperties(),shapeElement,"properties");
        }
        rootElement.appendChild(shapeElement);
    }
    private static void writePoint(Point point,Element rootElement,String name)  {
        Element pointElement = document.createElement("point");
        pointElement.setAttribute("name",name);
        if(point == null) {
            pointElement.setAttribute("exist","no");
        }
        else {
            pointElement.setAttribute("exist", "yes");
            pointElement.setAttribute("x", String.valueOf(point.x));
            pointElement.setAttribute("y", String.valueOf(point.y));
        }
        rootElement.appendChild(pointElement);
    }
    private static void writeColor(Color color,Element rootElement,String name)  {
        Element colorElement = document.createElement("color");
        colorElement.setAttribute("name",name);
        if(color == null) {
            colorElement.setAttribute("exist","no");
        }
        else {
            colorElement.setAttribute("exist","yes");
            colorElement.setAttribute("red", String.valueOf(color.getRed()));
            colorElement.setAttribute("green", String.valueOf(color.getGreen()));
            colorElement.setAttribute("blue", String.valueOf(color.getBlue()));
            colorElement.setAttribute("opacity", String.valueOf(color.getOpacity()));
        }
        rootElement.appendChild(colorElement);
    }
    private static void writeDoubleMap(Map<String, Double> map,Element rootElement,String name)  {
        Element doubleElement = document.createElement("doublemap");
        doubleElement.setAttribute("name",name);
        if(map == null) {
            doubleElement.setAttribute("exist","no");
        }
        else {
            doubleElement.setAttribute("exist","yes");
            for (Map.Entry<String,Double> entry : map.entrySet()) {
                Element fieldElement = document.createElement("field");
                fieldElement.setAttribute("key",(String) entry.getKey());
                fieldElement.setAttribute("value",String.valueOf((Double) entry.getValue()));
                doubleElement.appendChild(fieldElement);
            }
        }
        rootElement.appendChild(doubleElement);
    }
}
