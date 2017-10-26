package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends TheShape {
    final String radius1 = "radius";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Circle() {
        this.prop.put(radius1, 100.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();
    }
    public Circle(final double radius, final Point center) {
        this.prop.put(radius1, radius);
        this.prop.put(centerxx, (double)center.x);
        this.prop.put(centeryy, (double)center.y);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();

    }

    @Override
    public void draw(final Graphics canvas) {
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());

    }
    public void draww( final GraphicsContext canvas) {
        canvas.setFill(fil);
        canvas.fillOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());
        (canvas).setStroke(this.colr);

        canvas.strokeOval(prop.get(centerxx).intValue(), prop.get(centeryy).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
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
