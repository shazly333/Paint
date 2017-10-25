package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Ellipse extends TheShape {

    final String height = "height";
    final String width = "width";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Ellipse() {
        this.prop.put(height, 5.0);
        this.prop.put(width, 8.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
    }
    public Ellipse (final double wdth, final double high, final Point center)
    {
        this.prop.put(width, wdth);
        this.prop.put(height, high);
        this.prop.put(centerxx, (double)center.x);
        this.prop.put(centeryy, (double)center.y);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(width).intValue(), prop.get(height).intValue());
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        TheShape.allshapes.add(new Ellipse());
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
