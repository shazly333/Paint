package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Square extends TheShape {
    final String length = "len";
    public Square (final double len)
    {
        this.prop.put(length, len);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawRect(this.pos.x, this.pos.y, prop.get(length).intValue(), prop.get(length).intValue());
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
