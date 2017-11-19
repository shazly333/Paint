package eg.edu.alexu.csd.oop.draw.cs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.canvas.GraphicsContext;


public abstract class TheShape implements Shape {


    public  Point pos = new Point(0,0);
    public Point second_point = new Point(0,0);
    public Point thirdPoint = new Point(0,0);

    protected Map<String, Double> prop = new HashMap<String, Double>();
    public static ArrayList<TheShape> allshapes = new ArrayList<>();
    protected javafx.scene.paint.Color col = new javafx.scene.paint.Color(0, 0, 0, 0);
    protected javafx.scene.paint.Color fillCol = new javafx.scene.paint.Color(0, 0, 0, 0);

    @Override
    public void setPosition(final Point position) {
        pos = position;
    }
    public void set_second_position(final Point point)
    {
        second_point = point;
    }
    @Override
    public Point getPosition() {
        return pos;
    }

    @Override
    public void setProperties(final Map<String, Double> properties) {
        prop = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return prop;
    }

    @Override
    public void setColor(final javafx.scene.paint.Color  color) {
        col = color;
    }

    @Override
    public javafx.scene.paint.Color getColor() {
        return col;
    }

    @Override
    public void setFillColor(final javafx.scene.paint.Color color) {
        fillCol = color;
    }

    @Override
    public javafx.scene.paint.Color getFillColor() {
        return fillCol;
    }



    @Override
    public  Object clone() throws CloneNotSupportedException
    {
        TheShape newshape = null;
        try {
            newshape = this.getClass().newInstance();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    public abstract void draw(final GraphicsContext canvas);


    public static TheShape shapesFactory(final String shapeName) {
        try {
            for(int i = 0; i< MyDrawingEngine.supportedShapes.size(); i++ )
            {
                if(MyDrawingEngine.supportedShapes.get(i).getName().equals(shapeName))
                {
                    // return Class.forName(MyDrawingEngine.supportedShapes.get(i).getName()).newInstance();
                }
            }
            return (TheShape) Class.forName(shapeName).newInstance();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
