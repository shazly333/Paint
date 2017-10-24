package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Rectangle extends TheShape {
    final String lengthx = "lenx";
    final String lengthy = "leny";
    public Rectangle (final double lenx, final double leny)
    {
        this.prop.put(lengthx, lenx);
        this.prop.put(lengthy, leny);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawRect(this.pos.x, this.pos.y, prop.get(lengthx).intValue(), prop.get(lengthy).intValue());
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
