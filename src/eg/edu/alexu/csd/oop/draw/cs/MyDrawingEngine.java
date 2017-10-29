package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MyDrawingEngine implements DrawingEngine {

    private static MyDrawingEngine drawingEngine = null;

    private LinkedList<Shape> shapes = new LinkedList<>();
    private LinkedList<Command> commands = new LinkedList<>();
    private Canvas canvas = new Canvas();
    private MyDrawingEngine() {};
    private Stack<Command> done = new Stack<>();
    private Stack<Command> undone = new Stack<>();

    public synchronized MyDrawingEngine getInstance()
    {
        if(drawingEngine == null)
        {
            drawingEngine = new MyDrawingEngine();
        }
        return drawingEngine;
    }

    public void setShapes(LinkedList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        undone.empty();
        Command addShape = new AddShape(shape);
        addShape.execute();
        done.push(addShape);
    }

    @Override
    public void removeShape(Shape shape) {
        undone.empty();
        Command removeShape = new RemoveShape(shape);
        removeShape.execute();
        done.push(removeShape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        undone.empty();
        Command updateShape = new UpdateShape(oldShape,newShape);
        updateShape.execute();
        done.push(updateShape);
    }

    @Override
    public Shape[] getShapes() {
        return (Shape[]) shapes.toArray();
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return null;
    }

    @Override
    public void undo() {
        if(done.size() > 0)
        {
            Command command = done.pop();
            command.reverse();
            undone.push(command);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void redo() {
        if(undone.size() > 0)
        {
            Command command = undone.pop();
            command.execute();
            done.push(command);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void save(String path) {
        Saver.save(path,shapes);
    }

    @Override
    public void load(String path) {
        shapes = Saver.load(path);
    }
}