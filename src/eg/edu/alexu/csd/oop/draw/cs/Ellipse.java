package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Ellipse extends TheShape {

    final String height = "height";
    final String width = "width";
    public Ellipse (final double endx, final double endy)
    {
        this.prop.put(width, endx);
        this.prop.put(height, endx);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawOval(this.pos.x, this.pos.y, prop.get(width).intValue(), prop.get(height).intValue());
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return null;
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
