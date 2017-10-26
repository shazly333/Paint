package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

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
        this.pos.x = prop.get(pointax).intValue();
        this.pos.y = prop.get(pointay).intValue();
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
        this.pos.x = prop.get(pointax).intValue();
        this.pos.y = prop.get(pointay).intValue();
        final int [] xx = {a.x, b.x, c.x};
        final int [] yy = {a.y, b.y, c.y};
        allx = xx;
        ally = yy;
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillPolygon(allx, ally, 3);
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawPolygon(allx, ally, 3);

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        final TheShape newshape = new Circle();
        newshape.setColor(this.col);
        newshape.setFillColor(this.fillCol);
        newshape.setPosition(this.pos);
        final Map<String, Double> newprop = new HashMap<String, Double>();
        for (final Map.Entry<String, Double> entry : this.prop.entrySet()) {
            newprop.put(entry.getKey(), entry.getValue());
        }
        newshape.setProperties(newprop);
        TheShape.allshapes.add(newshape);
        return newshape;
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
