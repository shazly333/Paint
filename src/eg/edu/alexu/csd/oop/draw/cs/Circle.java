package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends TheShape {
    final String radius1 = "radius";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Circle() {
        this.typeind = 0;
        this.prop.put(radius1, 100.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();
    }
    public Circle(final double radius, final double xx, final double yy) {
        this.typeind = 0;
        this.prop.put(radius1, radius);
        this.prop.put(centerxx, xx);
        this.prop.put(centeryy, yy);
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
    @Override
    public void draw(final GraphicsContext canvas) {
        final javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(this.fillCol.getRed(), this.fillCol.getGreen(), this.fillCol.getBlue(), this.fillCol.getAlpha()/255.0);
        canvas.setFill(fxColor);
        canvas.fillOval(prop.get(centerxx).intValue() - prop.get(radius1).intValue()/2, prop.get(centeryy).intValue() - prop.get(radius1).intValue()/2, prop.get(radius1).intValue(), prop.get(radius1).intValue());
        final javafx.scene.paint.Color fillfx = javafx.scene.paint.Color.rgb(this.col.getRed(), this.col.getGreen(), this.col.getBlue(), this.col.getAlpha()/255.0);
        canvas.setFill(fillfx);
        canvas.strokeOval(prop.get(centerxx).intValue() - prop.get(radius1).intValue()/2, prop.get(centeryy).intValue() - prop.get(radius1).intValue()/2, prop.get(radius1).intValue(), prop.get(radius1).intValue());

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

}
