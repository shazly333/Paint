package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends TheShape {
    final String radiuss = "radius";
    final String centerx = "center x";
    final String centery = "center y";
    public Circle() {
        this.prop.put(radiuss, 100.0);
        this.prop.put(centerx, 0.0);
        this.prop.put(centery, 0.0);
        this.pos.x = prop.get(centerx).intValue();
        this.pos.y = prop.get(centery).intValue();
    }
    public Circle(final double radius, final double xx, final double yy) {
        this.prop.put(radiuss, radius);
        this.prop.put(centerx, xx);
        this.prop.put(centery, yy);
        this.pos.x = prop.get(centerx).intValue();
        this.pos.y = prop.get(centery).intValue();

    }
    @Override
    public void setPosition(final Point position) {
        pos = position;
        this.prop.put(centerx, (double) this.pos.x);
        this.prop.put(centery, (double) this.pos.y);
    }
    @Override
    public void set_second_position(final Point point)
    {
        second_point = point;
        final double len = Math.hypot(this.pos.x-this.second_point.x, this.pos.y-this.second_point.y);
        this.prop.put(radiuss, len);
    }

    @Override
    public void draw(final GraphicsContext canvas) {

        canvas.setFill(this.fillCol);
        canvas.fillOval(prop.get(centerx).intValue() - prop.get(radiuss).intValue(), prop.get(centery).intValue() - prop.get(radiuss).intValue(), prop.get(radiuss).intValue()*2, prop.get(radiuss).intValue()*2);
        canvas.setStroke(this.col);
        canvas.strokeOval(prop.get(centerx).intValue() - prop.get(radiuss).intValue(), prop.get(centery).intValue() - prop.get(radiuss).intValue(), prop.get(radiuss).intValue()*2, prop.get(radiuss).intValue()*2);

    }
}
