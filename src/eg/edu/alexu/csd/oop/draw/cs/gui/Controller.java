package eg.edu.alexu.csd.oop.draw.cs.gui;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs.TheShape;
import eg.edu.alexu.csd.oop.draw.cs.Triangle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Controller {
    final MyDrawingEngine engine =new MyDrawingEngine();
    TheShape final_shape = null;
    @FXML
    public BorderPane root =new BorderPane();
    @FXML
    public  Label scene = new Label();
    @FXML
    public ComboBox<String> shape_combo =new ComboBox<String>();
    @FXML
    TextArea guide_text =new TextArea();
    @FXML
    VBox vcanvas =new VBox();
    @FXML
    VBox select_list = new VBox();
    @FXML
    Canvas mycanvs = new Canvas();
    @FXML
    ColorPicker color = new ColorPicker();
    @FXML
    ColorPicker fillcolor = new ColorPicker();
    @FXML
    Pane backcanvs =new Pane();
    GraphicsContext gc;
    int check_fist_excute=1;
    //   public HashMap<String, String> map_between_simple_name_and_class_name = new HashMap<>();
    ArrayList<Point> triangle_point = new ArrayList<>();
    ArrayList<Class<? extends Shape>> plugins = new ArrayList<>();

    double globalX = 200;
    double globalY = 100;
    int selection_mode = 1;
    public void initialize() {
        shape_combo.setPromptText("Select Your Shape");
        fill_combo(engine);
        gc = mycanvs.getGraphicsContext2D();
        gc.setStroke(color.getValue());
        gc.setFill(fillcolor.getValue());
        (guide_text).setDisable(true);
        color.setValue(Color.BLACK);
        fillcolor.setValue(Color.WHITE);
    }
    public void plugin() throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException
    {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        final File jar=  fileChooser.showOpenDialog(root.getScene().getWindow());



        final JarFile jarFile = new JarFile(jar.getPath());
        final Enumeration<JarEntry> e = jarFile.entries();

        final URL[] urls = { new URL("jar:file:" + jar.getPath()+"!/") };
        final ClassLoader mainLoader = getClass().getClassLoader();
        final ClassLoader loader = URLClassLoader.newInstance(urls,mainLoader);

        while (e.hasMoreElements()) {
            final JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');

            // className = className.substring(0,je.getName().length()-6);
            try {
                final Class<? extends Shape> cl =   (Class<? extends Shape>) loader.getClass().forName(className, true, loader);
                /*if(cl.newInstance() instanceof Shape){
                    System.out.println(cl.getSimpleName());
                }*/
                engine.setSupportedShapes(cl);
                plugins.add(cl);







                fill_combo(engine);
                System.out.println(engine.getSupportedShapes().size());
            } catch (final ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    public void  release_canvs()
    {
        System.out.println(engine.getShapes().length);
        if(!is_plugin(shape_combo.getValue())&&!shape_combo.getValue().equals("Select Your Shape")&&!shape_combo.getValue().equals("Triangle"))
        {
            engine.addShape(final_shape);
            engine.refresh(gc);
            update_select_list();
        }
        check_fist_excute = 1;
    }
    boolean is_plugin(final String shapename)
    {
        for(int i=0;i<plugins.size();i++)
            if(plugins.get(i).getSimpleName().equals(shapename))
            {
                return true;
            }

        return false;
    }
    public void draw_triangleandplugnis(final MouseEvent ev) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        backcanvs.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(final MouseEvent ev) {

                // root.setOnMouseMoved(null);
                final double startX = ev.getX();
                final double startY = ev.getY();

                if(shape_combo.getValue().equals("Triangle"))
                {

                    if(triangle_point.size() == 2)
                    {
                        triangle_point.add(new Point((int) startX,(int)startY));
                        final Triangle shape =new Triangle(triangle_point.get(0), triangle_point.get(1), triangle_point.get(2));
                        shape.setColor(color.getValue());
                        shape.setFillColor(fillcolor.getValue());
                        engine.addShape(shape);
                        engine.refresh(gc);
                        triangle_point.clear();
                        update_select_list();
                    }
                    else
                    {

                        triangle_point.add(new Point((int) startX,(int)startY));
                    }
                }

                else
                {
                    triangle_point.clear();
                    if(is_plugin(shape_combo.getValue())) {
                        TheShape shape = null;
                        try {
                            shape = shapefactory(shape_combo.getValue());
                        } catch (final InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (final IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        shape.setPosition(new Point((int)startX,(int)startY));
                        shape.setColor(color.getValue());
                        shape.setFillColor(fillcolor.getValue());
                        engine.addShape(shape);
                        engine.refresh(gc);
                        update_select_list();
                    }
                }
            }
        });
    }
    public void update_select_list()
    {


        final HBox h = new HBox();
        select_list.getChildren().add(h);
        final Label lb = new Label();
        lb.setText("Shape");
        final Label lb2 = new Label();
        final Button bt = new Button();
        final CheckBox ch = new CheckBox();
        ch.getStyleClass().add("hbox");
        bt.getStyleClass().add("hbox");
        lb.getStyleClass().add("hbox");

        h.getChildren().add(ch);

        h.getChildren().add(bt);
        h.getChildren().add(lb);
        bt.setText("Update");
        final Label lb3 = new Label();
        h.getChildren().add(lb3);
        final Label lb5 = new Label();
        h.getChildren().add(lb5);

    }

    public void drag_canvs(final MouseEvent event)
    {

        if(shape_combo.getValue().equals("Select")){

            final double startX = event.getX();
            final double startY = event.getY();
            Rectangle select_rectangle ;
            gc.clearRect(0, 0, 100000, 100000);
            if(check_fist_excute==1){
                globalX = startX;
                globalY = startY;
                check_fist_excute = 0;
                select_rectangle = new Rectangle(100,500,new Point((int) globalX,(int) globalY));
            }
            else{

                select_rectangle = new Rectangle(Math.abs(globalX -startX), Math.abs(globalY -startY), new Point((int) globalX,(int) globalY));
            }

            select_rectangle.setColor(Color.BLACK);
            select_rectangle.setFillColor(Color.TRANSPARENT);
            engine.permatlyAddShape(select_rectangle);
            engine.refresh(gc);
            engine.permatlyRemoveShape();



        }
        else if(!is_plugin(shape_combo.getValue())&&!shape_combo.getValue().equals("Select Your Shape"))
        {
            // root.setOnMouseMoved(null);
            final double startX = event.getX();
            final double startY = event.getY();
            gc.clearRect(0, 0, 100000, 100000);
            TheShape new_shape = null;
            try {
                new_shape =  shapefactory(shape_combo.getValue());
            } catch (final InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (final IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(check_fist_excute);
            if(check_fist_excute==1){
                globalX = startX;
                globalY = startY;
                check_fist_excute = 0;
            }
            new_shape.pos= (new Point((int)globalX,(int) globalY));
            new_shape.second_point = new Point((int) startX, (int) startY);
            new_shape.setColor(color.getValue());
            new_shape.setFillColor(fillcolor.getValue());
            engine.permatlyAddShape(new_shape);
            engine.refresh(gc);
            engine.permatlyRemoveShape();
            final_shape = new_shape;
        }

    }

    public TheShape shapefactory(final String classname) throws InstantiationException, IllegalAccessException
    {
        for(int i=0;i<engine.getSupportedShapes().size();i++)
        {
            if(engine.getSupportedShapes().get(i).getSimpleName().equals(classname))
            {
                //         final TheShape p = (TheShape) engine.getSupportedShapes().get(i).newInstance();
                return (TheShape) engine.getSupportedShapes().get(i).newInstance();
            }
        }
        return null;
    }
    public void fill_combo(final DrawingEngine engine)
    {
        shape_combo.getItems().clear();
        for(int i=0;i<engine.getSupportedShapes().size();i++)
        {
            shape_combo.getItems().add(engine.getSupportedShapes().get(i).getSimpleName());
        }
        shape_combo.getItems().add("Select");

    }
    public  void getscene(final MouseEvent event){
        scene.setText("Scene X: "+event.getSceneX()+", Scene Y:"+event.getSceneY());

    }
}