package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Circle extends TheShape {
    final String radius1 = "radius";
    public Circle(final double radius)
    {
        this.prop.put(radius1, radius);

    }

    @Override
    public void draw(final Graphics canvas) {
        canvas.setColor(this.col);
        canvas.drawOval(this.pos.x, this.pos.y, prop.get(radius1).intValue(), prop.get(radius1).intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
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
