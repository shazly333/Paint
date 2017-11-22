package eg.edu.alexu.csd.oop.draw.cs.shapes;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Square extends TheShape {
    final String length = "length";
    final String startx = "Upper left x";
    final String Starty = "Upper left y";

    public Square () {
        this.prop.put(length, 1.0);
        this.prop.put(startx, 2.0);
        this.prop.put(Starty, 2.0);
        this.pos.x = prop.get(startx).intValue();
        this.pos.y = prop.get(Starty).intValue();
    }
    public Square (final double len, final Point bottomleft) {
        this.prop.put(length, len);
        this.prop.put(startx, (double) bottomleft.x);
        this.prop.put(Starty, (double) bottomleft.y);
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
        final double len =( Math.sqrt(Math.pow(this.pos.x-this.second_point.x,2)+Math.pow(this.pos.y-this.second_point.y,2)))/Math.sqrt(2);
        this.prop.put(length, len);
    }
    @Override
    public void draw(final GraphicsContext canvas) {
        canvas.setFill(this.fillCol);
        canvas.fillRect(this.prop.get(startx), this.prop.get(Starty), this.prop.get(length), this.prop.get(length));
        canvas.setStroke(this.col);
        canvas.strokeRect(this.prop.get(startx), this.prop.get(Starty), this.prop.get(length), this.prop.get(length));
    }

}