package eg.edu.alexu.csd.oop.draw.cs;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends TheShape {
    final String radius1 = "radius";
    final String centerxx = "centerx";
    final String centeryy = "centery";
    public Circle() {
        this.prop.put(radius1, 100.0);
        this.prop.put(centerxx, 0.0);
        this.prop.put(centeryy, 0.0);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();
    }
    public Circle(final double radius, final double xx, final double yy) {
        this.prop.put(radius1, radius);
        this.prop.put(centerxx, xx);
        this.prop.put(centeryy, yy);
        this.pos.x = prop.get(centerxx).intValue();
        this.pos.y = prop.get(centeryy).intValue();

    }

    @Override
    public void draw(final GraphicsContext canvas) {

        final double len =( Math.sqrt(Math.pow(this.pos.x-this.second_point.x,2)+Math.pow(this.pos.y-this.second_point.y,2)));

        this.prop.put(radius1, len);
        this.prop.put(centerxx,(double) this.pos.x);
        this.prop.put(centeryy, (double) this.pos.x);
        canvas.setFill(this.fillCol);
        canvas.fillOval(prop.get(centerxx).intValue() - prop.get(radius1).intValue(), prop.get(centeryy).intValue() - prop.get(radius1).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());
        canvas.setStroke(this.col);
        canvas.strokeOval(prop.get(centerxx).intValue() - prop.get(radius1).intValue(), prop.get(centeryy).intValue() - prop.get(radius1).intValue(), prop.get(radius1).intValue(), prop.get(radius1).intValue());

    }
}
