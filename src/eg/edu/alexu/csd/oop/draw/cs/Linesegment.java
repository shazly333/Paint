package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

public class Linesegment extends TheShape {

    final String endxx = "endx";
    final String endyy = "endy";
    final String startxx = "startx";
    final String Startyy = "Starty";
    public Linesegment () {
        this.prop.put(endxx, 1.0);
        this.prop.put(endyy, 1.0);
        this.prop.put(startxx, 2.0);
        this.prop.put(Startyy, 2.0);
        this.pos.x = prop.get(startxx).intValue();
        this.pos.y = prop.get(Startyy).intValue();
    }

    public Linesegment (final double endx, final double endy, final double startx, final double Starty) {
        this.prop.put(startxx, startx);
        this.prop.put(Startyy, Starty);
        this.prop.put(endxx, endx);
        this.prop.put(endyy, endy);
        this.pos.x = prop.get(startxx).intValue();
        this.pos.y = prop.get(Startyy).intValue();
    }


    @Override
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub
        this.prop.put(startxx, (double) this.pos.x);
        this.prop.put(Startyy, (double) this.pos.y);
        this.prop.put(endxx, (double) this.second_point.x);
        this.prop.put(endyy, (double) this.second_point.y);

        canvas.setStroke(this.col);
        canvas.strokeLine(this.pos.x,this.pos.y, this.second_point.x,this.second_point.y);

    }

}
