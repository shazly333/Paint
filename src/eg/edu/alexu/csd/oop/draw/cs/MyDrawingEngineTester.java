package eg.edu.alexu.csd.oop.draw.cs;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;


public class MyDrawingEngineTester {

    @Test
    public void tester() {
        MyDrawingEngine myDrawingEngine = MyDrawingEngine.getInstance();
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Point point1 = new Point(0,0);
        Point point2 = new Point(1,1);
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
        myDrawingEngine.load("t.txt");
        assertEquals(2,myDrawingEngine.getShapes().length);
        assertEquals(point1,myDrawingEngine.getShapes()[0].getPosition());
        assertEquals(point2,myDrawingEngine.getShapes()[1].getPosition());

    }
}