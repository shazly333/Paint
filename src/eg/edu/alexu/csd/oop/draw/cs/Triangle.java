package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Triangle extends TheShape {

    final String pointax = "pointa";
    final String pointay = "pointay";
    final String pointcx = "pointcx";
    final String pointcy = "pointcy";
    final String pointbx = "pointbx";
    final String pointby = "pointby";

    public Triangle (final double ax, final double ay, final double bx, final double by, final double cx, final double cy)
    {
        this.prop.put(pointax, ax);
        this.prop.put(pointay, ay);
        this.prop.put(pointbx, bx);
        this.prop.put(pointby, by);
        this.prop.put(pointcx, cx);
        this.prop.put(pointcy, cy);

    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        //   canvas.drawpo(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
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
