package eg.edu.alexu.csd.oop.draw.cs.shapes;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends TheShape {

    final String pointax = "pointa x";
    final String pointay = "pointa y";
    final String pointcx = "pointc x";
    final String pointcy = "pointc y";
    final String pointbx = "pointb x";
    final String pointby = "pointb y";
    public Triangle ()
    {
        this.prop.put(pointax, 1.0);
        this.prop.put(pointay, 12.0);
        this.prop.put(pointbx, 52.0);
        this.prop.put(pointby, 5.0);
        this.prop.put(pointcx, 39.0);
        this.prop.put(pointcy, 9.0);
        this.pos.x = prop.get(pointax).intValue();
        this.pos.y = prop.get(pointay).intValue();
    }
    public Triangle (final Point a, final Point b, final Point c)
    {
        this.prop.put(pointax, (double) a.x);
        this.prop.put(pointay, (double) a.y);
        this.prop.put(pointbx, (double) b.x);
        this.prop.put(pointby, (double) b.y);
        this.prop.put(pointcx, (double) c.x);
        this.prop.put(pointcy, (double) c.y);
        this.pos = a;
        this.second_point = b;
        this.thirdPoint = c;
    }
    @Override
    public void setPosition(final Point a) {
        pos =a;
        // TODO Auto-generated method stub
        this.prop.put(pointax, (double) a.x);
        this.prop.put(pointay, (double) a.y);
        this.prop.put(pointbx, (double) second_point.x);
        this.prop.put(pointby, (double) second_point.y);
        this.prop.put(pointcx, (double) thirdPoint.x);
        this.prop.put(pointcy, (double) thirdPoint.y);
        pos = a;
    }
    @Override
    public  Object clone() throws CloneNotSupportedException
    {
        TheShape newshape = null;
        try {
            newshape = this.getClass().newInstance();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        newshape.setColor(this.col);
        newshape.setFillColor(this.fillCol);
        newshape.pos = this.pos;
        newshape.second_point = this.second_point;
        newshape.thirdPoint = this.thirdPoint;

        final Map<String, Double> newprop = new HashMap<String, Double>();
        for (final Map.Entry<String, Double> entry : this.prop.entrySet()) {
            newprop.put(entry.getKey(), entry.getValue());
        }
        newshape.setProperties(newprop);
        TheShape.allshapes.add(newshape);
        return newshape;
    }

    @Override
    public void draw( final GraphicsContext canvas) {


        /*   this.prop.put(pointax, (double) this.pos.x);
        this.prop.put(pointay, (double)  this.pos.y);
        this.prop.put(pointbx, (double) this.second_point.x);
        this.prop.put(pointby, (double) this.second_point.y);
        //    this.prop.put(pointcx, (double) c.x);
        //  this.prop.put(pointcy, (double) c.y);
         */
        canvas.setFill(this.fillCol);
        canvas.fillPolygon(new double[]{this.prop.get(pointax),this.prop.get(pointbx),this.prop.get(pointcx)},new double[]{ this.prop.get(pointay),this.prop.get(pointby),this.prop.get(pointcy)}, 3);
        canvas.setStroke(this.col);
        canvas.strokePolygon(new double[]{this.prop.get(pointax),this.prop.get(pointbx),this.prop.get(pointcx)},new double[]{ this.prop.get(pointay),this.prop.get(pointby),this.prop.get(pointcy)}, 3);
    }
}