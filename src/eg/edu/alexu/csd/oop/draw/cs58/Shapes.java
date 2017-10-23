package eg.edu.alexu.csd.oop.draw.cs58;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Shapes implements Shape {

    public Point position;
    Map< String,Double> properties = new HashMap< String,Double>();
    @Override
    public void setPosition(final Point position) {
        // TODO Auto-generated method stub
        this.position = position;
    }

    @Override
    public Point getPosition() {
        // TODO Auto-generated method stub
        return this.position;
    }

    @Override
    public void setProperties(final Map<String, Double> properties) {
        // TODO Auto-generated method stub
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setColor(final Color color) {
        // TODO Auto-generated method stub

    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFillColor(final Color color) {
        // TODO Auto-generated method stub

    }

    @Override
    public Color getFillColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub

    }

}
