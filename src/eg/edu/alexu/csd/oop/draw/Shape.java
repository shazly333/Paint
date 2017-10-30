package eg.edu.alexu.csd.oop.draw;

public interface Shape {
    public void setPosition(java.awt.Point position);
    public java.awt.Point getPosition();
    public void setProperties(java.util.Map<String, Double> properties);
    public java.util.Map<String, Double> getProperties();



    /* update shape specific properties (e.g., radius) */



    public void setColor(java.awt.Color color);
    public java.awt.Color getColor();
    public void setFillColor(java.awt.Color color);
    public java.awt.Color getFillColor();
    /* redraw the shape on the canvas */
    public void draw(java.awt.Graphics canvas);
    /* create graphicsContext deep clone of the shape */


    public Object clone() throws CloneNotSupportedException;
}

