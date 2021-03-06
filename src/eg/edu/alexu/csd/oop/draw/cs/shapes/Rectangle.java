package eg.edu.alexu.csd.oop.draw.cs.shapes;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends TheShape {
    final String width = "width";
    final String height = "height";
    final String startx = "Upper left x";
    final String Starty = "Upper left y";

    public Rectangle () {
        this.prop.put(width, 1.0);
        this.prop.put(height, 1.0);
        this.prop.put(startx, 2.0);
        this.prop.put(Starty, 2.0);
        this.pos.x = prop.get(startx).intValue();
        this.pos.y = prop.get(Starty).intValue();
    }

    public Rectangle (final double lenx, final double leny, final Point upperleft) {
        this.prop.put(width, lenx);
        this.prop.put(height, leny);
        this.prop.put(startx, (double) upperleft.x);
        this.prop.put(Starty, (double) upperleft.y);
        this.pos.x = prop.get(startx).intValue();
        this.pos.y = prop.get(Starty).intValue();

    }
    @Override
    public void setPosition(final Point position) {
        pos = position;
        this.prop.put(startx, (double) this.pos.x);
        this.prop.put(Starty, (double) this.pos.y);
    }
    @Override
    public void set_second_position(final Point point)
    {
        second_point = point;
        this.prop.put(width, (double) Math.abs(this.pos.x-this.second_point.x));
        this.prop.put(height, (double) Math.abs(this.pos.y-this.second_point.y));
    }
    @Override
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub
        canvas.setFill(this.fillCol);
        canvas.fillRect(this.prop.get(startx), this.prop.get(Starty), prop.get(width).intValue(), prop.get(height).intValue());
        canvas.setStroke(this.col);
        canvas.strokeRect(this.prop.get(startx), this.prop.get(Starty), prop.get(width).intValue(), prop.get(height).intValue());
    }


}
