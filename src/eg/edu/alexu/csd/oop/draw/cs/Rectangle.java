package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Rectangle extends TheShape {
    final String width = "width";
    final String height = "height";
    final String startxx = "startxx";
    final String Startyy = "Starty";

    public Rectangle () {
        this.prop.put(width, 1.0);
        this.prop.put(height, 1.0);
        this.prop.put(startxx, 2.0);
        this.prop.put(Startyy, 2.0);
    }

    public Rectangle (final double lenx, final double leny, final Point bottomleft) {
        this.prop.put(width, lenx);
        this.prop.put(height, leny);
        this.prop.put(startxx, (double) bottomleft.x);
        this.prop.put(Startyy, (double) bottomleft.y);

    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawRect(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        TheShape.allshapes.add(new Rectangle());
        return allshapes.get(allshapes.size() - 1 );
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
