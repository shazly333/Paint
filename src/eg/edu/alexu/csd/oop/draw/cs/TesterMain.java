package eg.edu.alexu.csd.oop.draw.cs;


import eg.edu.alexu.csd.oop.draw.cs.drawingengine.MyDrawingEngine;

import java.io.IOException;

public class TesterMain {
    public static void main(final String[] args) throws ClassNotFoundException, IOException {
        MyDrawingEngine myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("/home/ayman/Downloads/paint.txt");
        System.out.println(myDrawingEngine.getShapes().length);
        myDrawingEngine.save("save.xml");
        myDrawingEngine = new MyDrawingEngine();
        myDrawingEngine.load("save.xml");
        System.out.println(myDrawingEngine.getShapes().length);
    }
}
