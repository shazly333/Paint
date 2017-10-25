package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Linesegment extends TheShape {

    final String endxx = "endx";
    final String endyy = "endy";
    final String startxx = "startxx";
    final String Startyy = "Starty";

    public Linesegment () {
        this.prop.put(endxx, 1.0);
        this.prop.put(endyy, 1.0);
        this.prop.put(startxx, 2.0);
        this.prop.put(Startyy, 2.0);
    }

    public Linesegment (final double endx, final double endy, final double startx, final double Starty) {
        this.prop.put(startxx, startx);
        this.prop.put(Startyy, Starty);
        this.prop.put(endxx, endx);
        this.prop.put(endyy, endy);
    }
    @Override
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        canvas.setColor(this.col);
        canvas.drawLine(prop.get(startxx).intValue(), prop.get(Startyy).intValue(), prop.get(endxx).intValue(), prop.get(endyy).intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        TheShape.allshapes.add(new Linesegment());
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
