package eg.edu.alexu.csd.oop.draw.cs.fileManager;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.util.LinkedList;

public class Saver {
    public static void save(String path, LinkedList<Shape> shapes){
        if(path.toLowerCase().endsWith(".json")) {
            JsonSaver.save(path,shapes);
        }
        else if(path.toLowerCase().endsWith(".xml")) {
            XmlSaver.save(path,shapes);
        }
        else {
            TextSaver.save(path,shapes);
        }
    }
    public static synchronized LinkedList<Shape> load(String path) {
        LinkedList<Shape> shapes = new LinkedList<>();
        if(path.toLowerCase().endsWith(".json")) {
            try {
                shapes = JsonLoader.load(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(path.toLowerCase().endsWith(".xml")) {
            try {
                shapes = XmlLoader.load(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                shapes = TextLoader.load(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return shapes;
    }
}
