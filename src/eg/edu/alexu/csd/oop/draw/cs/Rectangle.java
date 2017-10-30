package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends TheShape {
    final String width = "width";
    final String height = "height";
    final String startxx = "startxx";
    final String Startyy = "Starty";

    public Rectangle () {
        this.typeind = 1;
        this.prop.put(width, 1.0);
        this.prop.put(height, 1.0);
        this.prop.put(startxx, 2.0);
        this.prop.put(Startyy, 2.0);
        this.pos.x = prop.get(startxx).intValue();
        this.pos.y = prop.get(Startyy).intValue();
    }

    public Rectangle (final double lenx, final double leny, final Point bottomleft) {
        this.typeind = 1;
        this.prop.put(width, lenx);
        this.prop.put(height, leny);
        this.prop.put(startxx, (double) bottomleft.x);
        this.prop.put(Startyy, (double) bottomleft.y);
        this.pos.x = prop.get(startxx).intValue();
        this.pos.y = prop.get(Startyy).intValue();

    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.fillRect(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawRect(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
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
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub

    }

}
