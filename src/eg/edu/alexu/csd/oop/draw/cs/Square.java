package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Square extends TheShape {
    final String length = "length";
    final String startx = "start x";
    final String Starty = "Start y";

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
    public void draw(final GraphicsContext canvas) {
        final double len =( Math.sqrt(Math.pow(this.pos.x-this.second_point.x,2)+Math.pow(this.pos.y-this.second_point.y,2)))/Math.sqrt(2);
        this.prop.put(length, len);
        this.prop.put(startx, (double) this.pos.x);
        this.prop.put(Starty, (double) this.pos.y);

        canvas.setFill(this.fillCol);
        canvas.fillRect(this.pos.x, this.pos.y, len, len);
        canvas.setStroke(this.col);
        canvas.strokeRect(this.pos.x, this.pos.y, len,len);
    }

}