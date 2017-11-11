package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
    public void draw(final Graphics canvas) {
        // TODO Auto-generated method stub
        ((Graphics2D)canvas).setColor(this.fillCol);
        canvas.drawLine(prop.get(startxx).intValue(), prop.get(Startyy).intValue(), prop.get(endxx).intValue(), prop.get(endyy).intValue());
        ((Graphics2D)canvas).setColor(this.col);
        ((Graphics2D)canvas).setStroke(new BasicStroke(2));
        canvas.drawLine(prop.get(startxx).intValue(), prop.get(Startyy).intValue(), prop.get(endxx).intValue(), prop.get(endyy).intValue());

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        final TheShape newshape = new Circle();
        newshape.setColor(this.col);
        newshape.setFillColor(this.fillCol);
        newshape.setPosition(this.pos);
        final Map<String, Double> newprop = new HashMap<String, Double>();
        for (final Map.Entry<String, Double> entry : this.prop.entrySet()) {
            newprop.put(entry.getKey(), entry.getValue());
        }
        newshape.setProperties(newprop);
        TheShape.allshapes.add(newshape);
        return newshape;
    }


    @Override
    public void draw(final GraphicsContext canvas) {
        // TODO Auto-generated method stub
        final javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(this.fillCol.getRed(), this.fillCol.getGreen(), this.fillCol.getBlue(), this.fillCol.getAlpha()/255.0);
        canvas.setFill(fxColor);
        canvas.strokeLine(prop.get(startxx).intValue(), prop.get(Startyy).intValue(), prop.get(endxx).intValue(), prop.get(endyy).intValue());

    }

}
