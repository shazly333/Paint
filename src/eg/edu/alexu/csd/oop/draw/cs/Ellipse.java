package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends TheShape {
    final String height = "height";
    final String width = "width";
    final String centerx = "center x";
    final String centery = "center y";
    public Ellipse() {
        this.prop.put(height, 50.0);
        this.prop.put(width, 80.0);
        this.prop.put(centerx, 100.0);
        this.prop.put(centery, 500.0);
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
    public void setPosition(final Point position) {
        pos = position;
        this.prop.put(centerx, (double) this.pos.x);
        this.prop.put(centery, (double) this.pos.y);
    }
    @Override
    public void set_second_position(final Point point)
    {
        second_point = point;
        this.prop.put(height, (double) 2*Math.abs(this.pos.y-this.second_point.y));
        this.prop.put(width,  (double) 2*Math.abs(this.pos.x-this.second_point.x));
    }


    @Override
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub

        canvas.setFill(this.fillCol);
        canvas.fillOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());
        canvas.setStroke(this.col);
        canvas.strokeOval(prop.get(centerx).intValue(), prop.get(centery).intValue(), prop.get(width).intValue(), prop.get(height).intValue());


    }

}
