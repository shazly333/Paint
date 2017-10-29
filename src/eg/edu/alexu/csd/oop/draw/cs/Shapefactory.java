package eg.edu.alexu.csd.oop.draw.cs;

public class Shapefactory {

    TheShape getshapetype(final String type)
    {
        if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Circle")) {
            return new Circle();
        }
        else  if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Ellipse")) {
            return new Ellipse();
        }
        else   if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Triangle")) {
            return new Triangle();
        }
        else   if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Linesegment")) {
            return new Linesegment();
        }
        else   if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Square")) {
            return new Square();
        }
        else if (type.equals("eg.edu.alexu.csd.oop.draw.cs.Rectangle")) {
            return new Rectangle();
        }
        return null;
    }
}
