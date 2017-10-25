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
    public Triangle (final Point a, final Point b, final Point c)
    {
        this.prop.put(pointax, (double) a.x);
        this.prop.put(pointax, (double) a.y);
        this.prop.put(pointax, (double) b.x);
        this.prop.put(pointax, (double) b.y);
        this.prop.put(pointax, (double) c.x);
        this.prop.put(pointax, (double) c.y);
        final int [] xx = {a.x, b.x, c.x};
        final int [] yy = {a.y, b.y, c.y};
        allx = xx;
        ally = yy;
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        //   canvas.drawpo(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
        canvas.drawPolygon(allx, ally, 3);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return null;
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
