package eg.edu.alexu.csd.oop.draw.cs.drawingengine;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.commands.AddShape;
import eg.edu.alexu.csd.oop.draw.cs.commands.Command;
import eg.edu.alexu.csd.oop.draw.cs.commands.RemoveShape;
import eg.edu.alexu.csd.oop.draw.cs.commands.UpdateShape;
import eg.edu.alexu.csd.oop.draw.cs.fileManager.Saver;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyDrawingEngine implements DrawingEngine {

    final int maxSize = 20;
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
    public static List<Class<? extends Shape>> supportedShapes = new LinkedList();

    private final Canvas canvas = new Canvas();
    private final LinkedList<Command> done = new LinkedList();
    private final Stack<Command> undone = new Stack<>();

    public void addSupportedShapes(final Class<? extends Shape> newshape) {
        this.supportedShapes.add(supportedShapes.size(),newshape);
    }
    public void setShapes(final LinkedList<Shape> newShapes) {
        this.shapes = newShapes;
    }


    @Override
    public void refresh(final GraphicsContext graphicsContext) {
        for (final Shape shape : shapes) {
            shape.draw(graphicsContext);
        }
    }


    @Override
    public void addShape(final Shape shape) {
        Command addShape;
        addShape = new AddShape(shape,this);
        addShape.execute();
        addNewAction(addShape);
    }

    @Override
    public void removeShape(final Shape shape) {
        final Command removeShape = new RemoveShape(shape,this);
        removeShape.execute();
        addNewAction(removeShape);
    }


    @Override
    public void updateShape(final Shape oldShape, final Shape newShape) {
        final Command updateShape = new UpdateShape(oldShape,newShape,this);
        updateShape.execute();
        addNewAction(updateShape);
    }

    public void permatlyAddShape(final Shape shape) {
        shapes.add(shape);
    }

    public void permatlyRemoveShape() {
        shapes.removeLast();
    }

    public void permatlyUpdateShape(final Shape oldShape, final Shape newShape, final MyDrawingEngine drawengine) {
        undone.clear();
        final Command updateShape = new UpdateShape(oldShape,newShape,drawengine);
        updateShape.execute();
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[shapes.size()]);
    }


    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return supportedShapes;
    }


    @Override
    public void undo() {
        if(done.size() > 0)
        {
            final Command command = done.getLast();
            done.removeLast();
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
            final Command command = undone.pop();
            command.execute();
            done.addLast(command);
        }
        else
        {
            throw new RuntimeException();
        }
    }


    @Override
    public void save(final String path) {
        Saver.save(path,shapes);
    }

    public void removeallshape() {
        shapes.clear();
    }

    @Override
    public void load(final String path) {
        try {
            shapes = Saver.load(path);
        } catch (final Exception e) {}
    }

    private void addNewAction(final Command command) {
        undone.clear();
        done.addLast(command);
        if(done.size() > maxSize)
            done.removeFirst();
    }
}