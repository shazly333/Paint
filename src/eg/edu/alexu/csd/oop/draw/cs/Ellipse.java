package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends TheShape {

    final String height = "height";
    final String width = "width";
    final String centerx = "centerx";
    final String centery = "centery";
    public Ellipse() {
        this.prop.put(height, 5.0);
        this.prop.put(width, 8.0);
        this.prop.put(centerx, 0.0);
        this.prop.put(centery, 0.0);
        this.pos.x = prop.get(centerx).intValue();
        this.pos.y = prop.get(centery).intValue();
    }
    public Ellipse (final double wdth, final double high, final Point center) {
        this.prop.put(width, wdth);
        this.prop.put(height, high);
        this.prop.put(centerx, (double)center.x);
        this.prop.put(centery, (double)center.y);
        this.pos.x = prop.get(centerx).intValue();
        this.pos.y = prop.get(centery).intValue();
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());

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
        final javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(this.fillCol.getRed(), this.fillCol.getGreen(), this.fillCol.getBlue(), this.fillCol.getAlpha()/255.0);
        canvas.setFill(fxColor);
        canvas.fillOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());
        final javafx.scene.paint.Color fillfx = javafx.scene.paint.Color.rgb(this.col.getRed(), this.col.getGreen(), this.col.getBlue(), this.col.getAlpha()/255.0);
        canvas.setFill(fillfx);
        canvas.strokeOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());


    }

}
