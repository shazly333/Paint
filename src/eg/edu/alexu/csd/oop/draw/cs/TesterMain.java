package eg.edu.alexu.csd.oop.draw.cs;

import com.sun.beans.finder.ClassFinder;

public class TesterMain {
    public static void main(final String[] args) throws ClassNotFoundException {
        MyDrawingEngine myDrawingEngine = MyDrawingEngine.getInstance();
        Circle circle = new Circle();
        System.out.println(ClassFinder.findClass("Circle").getName());
        System.out.println(Class.class.getDeclaredClasses()[4].getName());
        Class<? extends Class> classes = Class.class.getClass();
        System.out.println(classes.getClasses().length);
        /*MyDrawingEngine myDrawingEngine = MyDrawingEngine.getInstance();
        List<Class<? extends Shape>> supportedShapes = myDrawingEngine.getSupportedShapes();
        System.out.println(supportedShapes.size());*/
    }
}
