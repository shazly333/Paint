package eg.edu.alexu.csd.oop.draw.cs;


import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.drawingengine.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs.shapes.Square;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;


public class MyDrawingEngineTester {


    public void tester() {
        final MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Circle circle2 = new Circle();
        final Point point1 = new Point(0,0);
        final Point point2 = new Point(1,1);
        circle1.setPosition(point1);
        circle2.setPosition(point2);
        // Add Shape
        myDrawingEngine.addShape(circle1);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(0,myDrawingEngine.getShapes().length);
        // Redo
        myDrawingEngine.redo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Update Shape
        myDrawingEngine.updateShape(circle1,circle2);
        assertEquals(circle2,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point2,myDrawingEngine.getShapes()[0].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Redo
        myDrawingEngine.redo();
        assertEquals(circle2,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point2,myDrawingEngine.getShapes()[0].getPosition());
        // Remove Shape
        myDrawingEngine.removeShape(circle2);
        assertEquals(0,myDrawingEngine.getShapes().length);
        // Add Shape
        myDrawingEngine.addShape(circle1);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        myDrawingEngine.addShape(circle2);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(circle2,myDrawingEngine.getShapes()[1]);
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());
        //Save
        myDrawingEngine.save("t.txt");
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(circle2,myDrawingEngine.getShapes()[1]);
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Remove Shape
        myDrawingEngine.removeShape(circle1);
        assertEquals(0,myDrawingEngine.getShapes().length);
        //Load
        try {
            myDrawingEngine.load("t.txt");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());

    }
    @Test
    public void test1() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        circle1.setProperties(null);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("ss1.txt");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss1.txt");
        assertEquals(1, myDrawingEngine.getShapes().length);
    }

    @Test
    public void test2() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("ss2.txt");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss2.txt");
        assertEquals(1, myDrawingEngine.getShapes().length);
    }
    @Test
    public void test3() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        final Shape circle2 = new Square();
        final Point point2 = new Point(2, -2);
        final Color color2 = new Color(.5,.5,.5,.5);
        circle2.setPosition(point2);
        myDrawingEngine.addShape(circle2);
        final Shape circle3 = new eg.edu.alexu.csd.oop.draw.cs.shapes.Rectangle();
        final Point point3 = new Point(3, -3);
        final Color color3 = Color.color(.5,.5,.5,.5);
        circle3.setPosition(point3);
        myDrawingEngine.addShape(circle3);
        final Shape circle4 = new Ellipse();
        final Point point4 = new Point(4, -4);
        final Color color4 = Color.color(.5,.5,.5,.5);
        circle4.setPosition(point4);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.updateShape(circle1, circle4);
        myDrawingEngine.save("ss.txt");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss.txt");
        assertEquals(4, myDrawingEngine.getShapes().length);
    }
    @Test
    public void test4() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.addShape(null);
        myDrawingEngine.save("ss.txt");
        assertEquals(2, myDrawingEngine.getShapes().length);
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss.txt");
        assertEquals(2, myDrawingEngine.getShapes().length);
    }
    public void testerXml() {
        final MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Circle circle2 = new Circle();
        final Point point1 = new Point(0,0);
        final Point point2 = new Point(1,1);
        circle1.setPosition(point1);
        circle2.setPosition(point2);
        // Add Shape
        myDrawingEngine.addShape(circle1);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(0,myDrawingEngine.getShapes().length);
        // Redo
        myDrawingEngine.redo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Update Shape
        myDrawingEngine.updateShape(circle1,circle2);
        assertEquals(circle2,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point2,myDrawingEngine.getShapes()[0].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Redo
        myDrawingEngine.redo();
        assertEquals(circle2,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point2,myDrawingEngine.getShapes()[0].getPosition());
        // Remove Shape
        myDrawingEngine.removeShape(circle2);
        assertEquals(0,myDrawingEngine.getShapes().length);
        // Add Shape
        myDrawingEngine.addShape(circle1);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        myDrawingEngine.addShape(circle2);
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(circle2,myDrawingEngine.getShapes()[1]);
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());
        //Save
        myDrawingEngine.save("t.Xml");
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(circle2,myDrawingEngine.getShapes()[1]);
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());
        // Undo
        myDrawingEngine.undo();
        assertEquals(circle1,myDrawingEngine.getShapes()[0]);
        assertEquals(1,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        // Remove Shape
        myDrawingEngine.removeShape(circle1);
        assertEquals(0,myDrawingEngine.getShapes().length);
        //Load
        try {
            myDrawingEngine.load("t.Xml");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());

    }
    @Test
    public void test1Xml() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        circle1.setProperties(null);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("ss1.Xml");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss1.Xml");
        assertEquals(1, myDrawingEngine.getShapes().length);
    }

    @Test
    public void test2Xml() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("ss2.Xml");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss2.Xml");
        assertEquals(1, myDrawingEngine.getShapes().length);
    }
    @Test
    public void test3Xml() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        final Shape circle2 = new Square();
        final Point point2 = new Point(2, -2);
        final Color color2 = Color.color(.5,.5,.5,.5);
        circle2.setPosition(point2);
        myDrawingEngine.addShape(circle2);
        final Shape circle3 = new Rectangle();
        final Point point3 = new Point(3, -3);
        final Color color3 = Color.color(.5,.5,.5,.5);
        circle3.setPosition(point3);
        myDrawingEngine.addShape(circle3);
        final Shape circle4 = new Ellipse();
        final Point point4 = new Point(4, -4);
        final Color color4 = Color.color(.5,.5,.5,.5);
        circle4.setPosition(point4);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.updateShape(circle1, circle4);
        myDrawingEngine.save("ss.Xml");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss.Xml");
        assertEquals(4, myDrawingEngine.getShapes().length);
    }
    @Test
    public void test4Xml() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        circle1.setPosition(point1);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.addShape(null);
        myDrawingEngine.save("ss.xml");
        assertEquals(2, myDrawingEngine.getShapes().length);
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("ss.xml");
        assertEquals(2, myDrawingEngine.getShapes().length);
    }

    @Test
    public void ColorXmlTest() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        final Color color2 = Color.color(.1,.1,.1,.1);
        circle1.setPosition(point1);
        circle1.setColor(color1);
        circle1.setFillColor(color2);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("color.xml");
        assertEquals(1, myDrawingEngine.getShapes().length);
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("color.xml");
        assertEquals(1, myDrawingEngine.getShapes().length);
        Color color = myDrawingEngine.getShapes()[0].getColor();
        Color fillColor = myDrawingEngine.getShapes()[0].getFillColor();
        assertEquals(.5,color.getBlue(),.00001);
        assertEquals(.5,color.getGreen(),.00001);
        assertEquals(.5,color.getRed(),.00001);
        assertEquals(.5,color.getOpacity(),.00001);
        assertEquals(.1,fillColor.getBlue(),.00001);
        assertEquals(.1,fillColor.getGreen(),.00001);
        assertEquals(.1,fillColor.getRed(),.00001);
        assertEquals(.1,fillColor.getOpacity(),.00001);
    }
    @Test
    public void ColorJsonTest() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        final Color color2 = Color.color(.1,.1,.1,.1);
        circle1.setPosition(point1);
        circle1.setColor(color1);
        circle1.setFillColor(color2);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("color.json");
        assertEquals(1, myDrawingEngine.getShapes().length);
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("color.json");
        assertEquals(1, myDrawingEngine.getShapes().length);
        Color color = myDrawingEngine.getShapes()[0].getColor();
        Color fillColor = myDrawingEngine.getShapes()[0].getFillColor();
        assertEquals(.5,color.getBlue(),.00001);
        assertEquals(.5,color.getGreen(),.00001);
        assertEquals(.5,color.getRed(),.00001);
        assertEquals(.5,color.getOpacity(),.00001);
        assertEquals(.1,fillColor.getBlue(),.00001);
        assertEquals(.1,fillColor.getGreen(),.00001);
        assertEquals(.1,fillColor.getRed(),.00001);
        assertEquals(.1,fillColor.getOpacity(),.00001);
    }

    @Test
    public void ColorTextTest() {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        final Circle circle1 = new Circle();
        final Point point1 = new Point(1, -1);
        final Color color1 = Color.color(.5,.5,.5,.5);
        final Color color2 = Color.color(.1,.1,.1,.1);
        circle1.setPosition(point1);
        circle1.setColor(color1);
        circle1.setFillColor(color2);
        myDrawingEngine.addShape(circle1);
        myDrawingEngine.save("color.txt");
        assertEquals(1, myDrawingEngine.getShapes().length);
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("color.txt");
        assertEquals(1, myDrawingEngine.getShapes().length);
        Color color = myDrawingEngine.getShapes()[0].getColor();
        Color fillColor = myDrawingEngine.getShapes()[0].getFillColor();
        assertEquals(.5,color.getBlue(),.00001);
        assertEquals(.5,color.getGreen(),.00001);
        assertEquals(.5,color.getRed(),.00001);
        assertEquals(.5,color.getOpacity(),.00001);
        assertEquals(.1,fillColor.getBlue(),.00001);
        assertEquals(.1,fillColor.getGreen(),.00001);
        assertEquals(.1,fillColor.getRed(),.00001);
        assertEquals(.1,fillColor.getOpacity(),.00001);
    }
}