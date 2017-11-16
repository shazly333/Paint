package eg.edu.alexu.csd.oop.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape {
    public void setPosition(java.awt.Point position);
    public java.awt.Point getPosition();
    public void setProperties(java.util.Map<String, Double> properties);
    public java.util.Map<String, Double> getProperties();



    /* update shape specific properties (e.g., radius) */



    public void setColor(Color color);
    public Color getColor();
    public void setFillColor(Color color);
    public Color getFillColor();
    /* redraw the shape on the canvas */
    public void draw(GraphicsContext canvas);
    /* create graphicsContext deep clone of the shape */


    public Object clone() throws CloneNotSupportedException;
}

