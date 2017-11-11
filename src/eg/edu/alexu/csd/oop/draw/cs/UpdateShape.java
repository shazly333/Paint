package eg.edu.alexu.csd.oop.draw.cs;

import eg.edu.alexu.csd.oop.draw.Shape;

public class UpdateShape implements Command{

    public Shape oldShape;
    public Shape newShape;
    Command removeShape;
    Command addShape;
    public UpdateShape(final Shape oldShape,final Shape newShape){
        this.newShape = newShape;
        this.oldShape = oldShape;
        removeShape = new RemoveShape(this.oldShape);
        addShape = new AddShape(this.newShape);
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
