package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Triangle extends TheShape {

    final String pointax = "pointa";
    final String pointay = "pointay";
    final String pointcx = "pointcx";
    final String pointcy = "pointcy";
    final String pointbx = "pointbx";
    final String pointby = "pointby";
    int [] allofx;
    int [] allofy;
    public Triangle ()
    {
        this.prop.put(pointax, 1.0);
        this.prop.put(pointay, 1.0);
        this.prop.put(pointbx, 5.0);
        this.prop.put(pointby, 5.0);
        this.prop.put(pointcx, 9.0);
        this.prop.put(pointcy, 9.0);
        final int [] x = {1, 5, 9};
        final int [] y = {1, 5, 9};
        this.pos.x = prop.get(pointax).intValue();
        this.pos.y = prop.get(pointay).intValue();
        allofx = x;
        allofy = y;
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
        final int [] x = {a.x, b.x, c.x};
        final int [] y = {a.y, b.y, c.y};
        allofx = x;
        allofy = y;
    }

    @Override
    public void draw( final GraphicsContext canvas) {
        final double[] yfx = new double[3];
        final double[] xfx = new double[3];
        for (int i = 0; i < 3; i++)
        {
            yfx[i] = allofy[i];
            xfx[i] = allofx[i];
        }
        canvas.setFill(this.fillCol);
        canvas.fillPolygon(xfx, yfx, 3);
        canvas.setStroke(this.col);
        canvas.strokePolygon(xfx, yfx, 3);
    }
}
