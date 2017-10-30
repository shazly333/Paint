package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

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
        this.typeind = 4;
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
        this.typeind = 4;
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
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillPolygon(allofx, allofy, 3);
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawPolygon(allofx, allofy, 3);

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
        final javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(this.fillCol.getRed(), this.fillCol.getGreen(), this.fillCol.getBlue(), this.fillCol.getAlpha()/255.0);
        canvas.setFill(fxColor);
        canvas.fillPolygon(yfx, xfx, 3);
        final javafx.scene.paint.Color fillfx = javafx.scene.paint.Color.rgb(this.col.getRed(), this.col.getGreen(), this.col.getBlue(), this.col.getAlpha()/255.0);
        canvas.setFill(fillfx);
        canvas.strokePolygon(yfx, xfx, 3);

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




}
