package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.util.Arrays;
import java.util.LinkedList;

public class RemoveShape implements Command{
    private Shape shape;
    MyDrawingEngine myDrawingEngine;

    public RemoveShape(Shape shape,MyDrawingEngine myDrawingEngine)
    {
        this.shape = shape;
        this.myDrawingEngine = myDrawingEngine;
    }

    @Override
    public void execute() {
        Shape[] shapes = myDrawingEngine.getShapes();
        LinkedList<Shape> shapesll = new LinkedList<>(Arrays.asList(shapes));
        shapesll.remove(shape);
        myDrawingEngine.setShapes(shapesll);
    }

    @Override
    public void reverse() {
        Shape[] shapes = myDrawingEngine.getShapes();
        LinkedList<Shape> shapesll = new LinkedList<>(Arrays.asList(shapes));
        shapesll.add(shape);
        myDrawingEngine.setShapes(shapesll);
    }
}

