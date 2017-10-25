package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Circle extends TheShape {
    final String radius1 = "radius";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Circle() {
        this.prop.put(radius1, 5.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
    }
    public Circle(final double radius, final Point center) {
        this.prop.put(radius1, radius);
        this.prop.put(centerxx, (double)center.x);
        this.prop.put(centeryy, (double)center.y);
    }

    @Override
    public void draw(final Graphics canvas) {
        canvas.setColor(this.col);
        canvas.drawOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TheShape.allshapes.add(new Circle());
        return allshapes.get(allshapes.size() - 1 );
    }

    @Override
    public void save(final BufferedWriter bfW) {
        // TODO Auto-generated method stub

    }

    @Override
    public void load(final BufferedReader bfR) {
        // TODO Auto-generated method stub

    }
}
