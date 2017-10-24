package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;


public abstract class TheShape implements eg.edu.alexu.csd.oop.draw.Shape {
    public static String[] types = {"Circle","Rectangle","Ellipse","Line","Triangle","Square"};
    private int  typeInd = -1;
    private Point pos = new Point(0,0);
    private Map<String, Double> prop = new HashMap<String, Double>();
    Color col = new Color(Color.HSBtoRGB(0,0,0));
    Color fillCol = new Color(Color.HSBtoRGB(255,255,255));
    @Override
    public void setPosition(Point position) {
        pos = position;
    }

    @Override
    public Point getPosition() {
        return pos;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        prop = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return prop;
    }

    @Override
    public void setColor(Color color) {
        col = color;
    }

    @Override
    public Color getColor() {
        return col;
    }

    @Override
    public void setFillColor(Color color) {
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

    public abstract void save(BufferedWriter bfW);
    public abstract void load(BufferedReader bfR);
}
