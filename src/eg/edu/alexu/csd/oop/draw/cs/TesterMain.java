package eg.edu.alexu.csd.oop.draw.cs;


import java.awt.*;
import java.io.IOException;

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
        myDrawingEngine.save("sss.xml");
    }
}
