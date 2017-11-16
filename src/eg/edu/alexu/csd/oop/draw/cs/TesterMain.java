package eg.edu.alexu.csd.oop.draw.cs;


import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.json.Value;
import eg.edu.alexu.csd.oop.draw.cs.json.ValueReader;

import java.awt.*;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class TesterMain {
    public static void main(final String[] args) throws ClassNotFoundException, IOException {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Point point1 = new Point(0,0);
        Point point2 = new Point(1,1);
        circle1.setPosition(point1);
        circle2.setPosition(point2);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.addShape(circle2);
        Shape[] shapes = myDrawingEngine.getShapes();
        LinkedList<Shape> shapes1 = new LinkedList<>();
        Collections.addAll(shapes1, shapes);
        JsonSaver.save("",shapes1);
        BufferedReader instream = new BufferedReader(new FileReader("Save.json"));
        Value value = ValueReader.read(instream);
        BufferedWriter outstream = new BufferedWriter(new FileWriter("Save2.json"));
        value.write(0,outstream);
        outstream.close();
    }
}
