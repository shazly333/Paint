package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class TheShape implements eg.edu.alexu.csd.oop.draw.Shape {
    public static String[] types = {"Circle","Rectangle","Ellipse","Line","Triangle","Square"};

    int  typeInd = -1;
    Point pos = new Point(0,0);
    Map<String, Double> prop = new HashMap<String, Double>();
    public static ArrayList<TheShape> allshapes = new ArrayList<>();
    Color col = new Color(Color.HSBtoRGB(0,0,0));
    javafx.scene.paint.Color colr = javafx.scene.paint.Color.BLACK;
    javafx.scene.paint.Color fil = javafx.scene.paint.Color.WHITE;
    Color fillCol = new Color(Color.HSBtoRGB(255,255,255));

    public int getTypeInd() {
        return typeInd;
    }

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

    public abstract void save(BufferedWriter bfW);
    public abstract void load(BufferedReader bfR);

    public static TheShape shapesFactory(int typeInd)
    {
        return new Circle();
    }
}
