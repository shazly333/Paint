package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;

public class UpdateShape implements Command{

    public Shape oldShape;
    public Shape newShape;
    Command removeShape;
    Command addShape;
    public UpdateShape(final Shape oldShape,final Shape newShape,MyDrawingEngine myDrawingEngine){
        this.newShape = newShape;
        this.oldShape = oldShape;
        removeShape = new RemoveShape(this.oldShape,myDrawingEngine);
        addShape = new AddShape(this.newShape,myDrawingEngine);
    }

    @Override
    public void execute() {
        removeShape.execute();
        addShape.execute();
    }

    @Override
    public void reverse() {
        addShape.reverse();
        removeShape.reverse();
    }
}
