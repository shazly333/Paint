package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Linesegment extends TheShape {
    final String endxx = "endx";
    final String endyy = "endy";
    public Linesegment (final double endx, final double endy)
    {
        this.prop.put(endxx, endx);
        this.prop.put(endyy, endx);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawLine(this.pos.x, this.pos.y, prop.get(endxx).intValue(), prop.get(endyy).intValue());
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
