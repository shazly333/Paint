package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Triangle extends TheShape {

    final String pointax = "pointa";
    final String pointay = "pointay";
    final String pointcx = "pointcx";
    final String pointcy = "pointcy";
    final String pointbx = "pointbx";
    final String pointby = "pointby";
    int [] allx;
    int [] ally;
    public Triangle ()
    {
        this.prop.put(pointax, 1.0);
        this.prop.put(pointay, 1.0);
        this.prop.put(pointbx, 5.0);
        this.prop.put(pointby, 5.0);
        this.prop.put(pointcx, 9.0);
        this.prop.put(pointcy, 9.0);
        final int [] xx = {1, 5, 9};
        final int [] yy = {1, 5, 9};
        allx = xx;
        ally = yy;
    }
    public Triangle (final Point a, final Point b, final Point c)
    {
        this.prop.put(pointax, (double) a.x);
        this.prop.put(pointay, (double) a.y);
        this.prop.put(pointbx, (double) b.x);
        this.prop.put(pointby, (double) b.y);
        this.prop.put(pointcx, (double) c.x);
        this.prop.put(pointcy, (double) c.y);
        final int [] xx = {a.x, b.x, c.x};
        final int [] yy = {a.y, b.y, c.y};
        allx = xx;
        ally = yy;
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawPolygon(allx, ally, 3);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        TheShape.allshapes.add(new Triangle());
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
