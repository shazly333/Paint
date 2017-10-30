package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends TheShape {

    final String height = "height";
    final String width = "width";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Ellipse() {
        this.typeind = 2;
        this.prop.put(height, 5.0);
        this.prop.put(width, 8.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();
    }
    public Ellipse (final double wdth, final double high, final Point center) {
        this.typeind = 2;
        this.prop.put(width, wdth);
        this.prop.put(height, high);
        this.prop.put(centerxx, (double)center.x);
        this.prop.put(centeryy, (double)center.y);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(width).intValue(), prop.get(height).intValue());
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(width).intValue(), prop.get(height).intValue());

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
        return newshape;    }


    @Override
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub

    }

}
