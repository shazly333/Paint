package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class TheShape implements eg.edu.alexu.csd.oop.draw.Shape {


    Point pos = new Point(0,0);
    Map<String, Double> prop = new HashMap<String, Double>();
    public static ArrayList<TheShape> allshapes = new ArrayList<>();
    Color col = new Color(Color.HSBtoRGB(0,0,0));
    Color fillCol = new Color(Color.HSBtoRGB(100,100,100));

    @Override
    public void setPosition(final Point position) {
        pos = position;
    }

    @Override
    public Point getPosition() {
        return pos;
    }

    @Override
    public void setProperties(final Map<String, Double> properties) {
        prop = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return prop;
    }

    @Override
    public void setColor(final Color color) {
        col = color;
    }

    @Override
    public Color getColor() {
        return col;
    }

    @Override
    public void setFillColor(final Color color) {
        fillCol = color;
    }

    @Override
    public Color getFillColor() {
        return fillCol;
    }

    @Override
    public abstract void draw(Graphics canvas);

    @Override
    public abstract Object clone() throws CloneNotSupportedException;

    public abstract void draw(final GraphicsContext canvas);


    public static TheShape shapesFactory(String shapeName) {
        try {
            return (TheShape) Class.forName(shapeName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
