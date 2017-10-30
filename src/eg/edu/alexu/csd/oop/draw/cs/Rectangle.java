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
    final String startx = "startx";
    final String Starty = "Starty";

    public Rectangle () {
        this.typeind = 1;
        this.prop.put(width, 1.0);
        this.prop.put(height, 1.0);
        this.prop.put(startx, 2.0);
        this.prop.put(Starty, 2.0);
        this.pos.x = prop.get(startx).intValue();
        this.pos.y = prop.get(Starty).intValue();
    }

    public Rectangle (final double lenx, final double leny, final Point bottomleft) {
        this.typeind = 1;
        this.prop.put(width, lenx);
        this.prop.put(height, leny);
        this.prop.put(startx, (double) bottomleft.x);
        this.prop.put(Starty, (double) bottomleft.y);
        this.pos.x = prop.get(startx).intValue();
        this.pos.y = prop.get(Starty).intValue();

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
        final javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(this.fillCol.getRed(), this.fillCol.getGreen(), this.fillCol.getBlue(), this.fillCol.getAlpha()/255.0);
        canvas.setFill(fxColor);
        canvas.fillRect(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
        final javafx.scene.paint.Color fillfx = javafx.scene.paint.Color.rgb(this.col.getRed(), this.col.getGreen(), this.col.getBlue(), this.col.getAlpha()/255.0);
        canvas.setFill(fillfx);
        canvas.strokeRect(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());

    }

}
