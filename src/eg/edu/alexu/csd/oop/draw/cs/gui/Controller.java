package eg.edu.alexu.csd.oop.draw.cs.gui;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs.TheShape;
import eg.edu.alexu.csd.oop.draw.cs.Triangle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    final MyDrawingEngine engine =new MyDrawingEngine();
    TheShape final_shape = null;
    String savepath = "";
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
    ListView<HBox> shapelist = new ListView();
    @FXML
    Canvas mycanvs = new Canvas();
    @FXML
    ColorPicker color = new ColorPicker();
    @FXML
    ColorPicker fillcolor = new ColorPicker();
    @FXML
    ColorPicker updfillcol = new ColorPicker();
    @FXML
    ColorPicker updcol = new ColorPicker();

    @FXML
    Pane backcanvs =new Pane();

    @FXML
    ToolBar toolBar = new ToolBar();
    @FXML
    Button apply = new Button();
    GraphicsContext gc;
    Rectangle selectrectangle = null ;
    int check_fist_excute=1;
    public HashMap<Button , TheShape > mapbetweenbuttonandshape = new HashMap<>();
    ArrayList<Point> triangle_point = new ArrayList<>();
    ArrayList<Class<? extends Shape>> plugins = new ArrayList<>();
    ArrayList<TheShape> selectedshapes = new ArrayList<>();
    ArrayList<TheShape> copiedshape = new ArrayList<>();
    ArrayList<TheShape> movedshapes = new ArrayList<>();

    double globalX = 200;
    double globalY = 100;
    int selection_mode = 0;
    public void initialize() {
        //  updcol.setValue(null);
        // updfillcol.setValue(null);
        toolBar.setDisable(true);
        shape_combo.setPromptText("Select Your Shape");
        fill_combo(engine);
        gc = mycanvs.getGraphicsContext2D();
        gc.setStroke(color.getValue());
        gc.setFill(fillcolor.getValue());
        (guide_text).setDisable(true);
        color.setValue(Color.BLACK);
        fillcolor.setValue(Color.WHITE);
    }
    private void removeShape(final TheShape shape) {
        engine.removeShape(shape);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        engine.refresh(gc);
        update_select_list();
    }

    private void updateShape(final TheShape shape) {
        final Stage updateStage = new Stage();
        updateStage.setTitle("Update Shape");
        final VBox updatebox = new VBox();
        final HashMap< String, TextField> mapbetweentextfieldanddata = new HashMap<>();
        final Button apply = new Button();
        for (final Map.Entry<String, Double> entry : shape.getProperties().entrySet()) {
            final Label dataname = new Label();
            final Label space1 = new Label();
            final Label sapce2 = new Label();
            dataname.setText(entry.getKey());
            final TextField datafield = new TextField();
            datafield.setText(entry.getValue().toString());
            mapbetweentextfieldanddata.put(entry.getKey(), datafield);
            updatebox.getChildren().add(dataname);
            updatebox.getChildren().add(space1);
            updatebox.getChildren().add(datafield);
            updatebox.getChildren().add(sapce2);
        }
        final Label space = new Label();
        final Label space3 = new Label();

        space.setText("Update Color");
        final Label space2 = new Label();
        space2.setText("Update Fill Color");
        final ColorPicker updatecol = new ColorPicker();
        final ColorPicker updatefillcol = new ColorPicker();
        updatecol.setValue(shape.getColor());
        updatefillcol.setValue(shape.getFillColor());
        updatebox.getChildren().add(space);
        updatebox.getChildren().add(updatecol);
        updatebox.getChildren().add(space2);
        updatebox.getChildren().add(updatefillcol);
        updatebox.getChildren().add(space3);

        apply.setText("Apply Changes");
        apply.getStyleClass().add("hbox");


        apply.setOnAction(e->{
            TheShape newshape = null;
            try {
                newshape = (TheShape) shape.clone();
            } catch (final CloneNotSupportedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            final Map<String, Double> newprop = new HashMap<String, Double>();
            for (final Entry<String, TextField> entry : mapbetweentextfieldanddata.entrySet()) {
                final String input = entry.getValue().getText();
                if(!input.matches("^\\d+(\\.\\d+)?")) {
                    final Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Ooops, Input Should Be Doubles Or Integers!");

                    alert.showAndWait();
                    return;
                }
                newprop.put(entry.getKey(),Double.parseDouble( entry.getValue().getText()));
            }
            newshape.setColor(updatecol.getValue());
            newshape.setFillColor(updatefillcol.getValue());
            newshape.setProperties(newprop);
            engine.updateShape(shape, newshape);
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 100000, 100000);
            engine.refresh(gc);
            updateStage.close();
        });
        final StackPane layout = new StackPane();
        updatebox.getChildren().add(apply);
        layout.getChildren().add(updatebox);
        update_select_list();
        updateStage.setScene(new Scene(layout, 600, 600));
        updateStage.show();
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
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
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
                e1.printStackTrace();
            }
        }
    }
    public void  saveas()
    {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose location To Save Yor Draw");
        File selectedFile = null;
        while(selectedFile== null){
            selectedFile = chooser.showSaveDialog(null);
        }
        final File savefile = new File(selectedFile.getPath());
        if(!savefile.equals(null)){
            this.savepath = savefile.getPath();
            engine.save(savefile.getPath());
        }
    }
    public void  save()
    {
        if(savepath.equals(""))
            saveas();
        engine.save(savepath);
    }
    public void  load()
    {

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        final File loadfile=  fileChooser.showOpenDialog(root.getScene().getWindow());
        if(!loadfile.equals(null)){
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 100000, 100000);
            engine.removeallshape();
            engine.load(loadfile.getPath());
            engine.refresh(gc);
            update_select_list();
            check_fist_excute = 1;
            triangle_point.clear();
            plugins.clear();
            savepath = "";
            shape_combo.setValue("Select Your Shape");
        }
    }
    public void  undo()
    {
        engine.undo();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 100000, 100000);
        engine.refresh(gc);
        update_select_list();
    }
    public void  redo()
    {
        engine.redo();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 100000, 100000);
        engine.refresh(gc);
        update_select_list();
    }

    public void  release_canvs()
    {
        // System.out.println(engine.getShapes().size());
        if(check_fist_excute == 0&&selection_mode == 0&&!is_plugin(shape_combo.getValue())&&!shape_combo.getValue().equals("Triangle"))
        {
            engine.addShape(final_shape);
            update_select_list();
        }
        check_fist_excute = 1;
        gc.setLineDashes(0);
        engine.refresh(gc);
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
                        selection_mode = 0;
                        toolBar.setDisable(true);
                        movedshapes.clear();
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
                        selection_mode=0;
                        movedshapes.clear();
                        toolBar.setDisable(true);
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

        shapelist.getItems().clear();
        mapbetweenbuttonandshape.clear();
        for(int i=0;i<engine.getShapes().length;i++) {
            final HBox h = new HBox();
            shapelist.getItems().add(h);
            final Label lb =new Label();
            //  shapelist.getChildrenUnmodifiable().add(lb);
            final Label shapenumber = new Label();
            final Button update = new Button();
            final Button remove = new Button();
            update.getStyleClass().add("hbox");
            remove.getStyleClass().add("hbox");
            shapenumber.getStyleClass().add("hbox");
            shapenumber.setText("Shape "+(i+1));
            h.getChildren().add(update);
            h.getChildren().add(remove);
            h.getChildren().add(shapenumber);
            update.setText("Update");
            remove.setText("Remove");
            mapbetweenbuttonandshape.put(update, (TheShape) engine.getShapes()[i]);
            mapbetweenbuttonandshape.put(remove, (TheShape) engine.getShapes()[i]);
            final TheShape updatedshape =(TheShape) engine.getShapes()[i];
            update.setOnAction(e-> updateShape(updatedshape));
            remove.setOnAction(e-> removeShape(updatedshape));

        }
    }

    public void drag_canvs(final MouseEvent event)
    {

        if(shape_combo.getValue().equals("Select")) {
            movedshapes.clear();
            final Rectangle newtriangle = new Rectangle() ;
            selectedshapes.clear();
            final double startX = event.getX();
            final double startY = event.getY();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 10000, 10000);
            if(check_fist_excute==1) {
                globalX = startX;
                globalY = startY;
                check_fist_excute = 0;
            }
            newtriangle.setPosition (new Point((int)globalX,(int) globalY));
            newtriangle.set_second_position ( new Point((int) startX, (int) startY));

            newtriangle.setColor(Color.BLACK);
            newtriangle.setFillColor(Color.TRANSPARENT);
            gc.setLineDashes(10.0);
            engine.permatlyAddShape(newtriangle);
            engine.refresh(gc);
            engine.permatlyRemoveShape();
            selection_mode = 1;
            selectrectangle = newtriangle;
            selectedshapes.clear();
            for(int i=0;i<engine.getShapes().length;i++)
            {
                final TheShape shape = (TheShape) engine.getShapes()[i];
                if(shape.getPosition().x>=newtriangle.pos.x&&shape.getPosition().x<=newtriangle.second_point.x&&shape.getPosition().y>=newtriangle.pos.y&&shape.getPosition().y<=newtriangle.second_point.y)
                {
                    selectedshapes.add(shape);
                    System.out.println(1);
                }
            }

        }
        else if(!is_plugin(shape_combo.getValue())&&!shape_combo.getValue().equals("Select Your Shape")&&!shape_combo.getValue().equals("Triangle"))
        {
            selection_mode = 0;
            movedshapes.clear();
            toolBar.setDisable(true);
            // root.setOnMouseMoved(null);
            final double startX = event.getX();
            final double startY = event.getY();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 10000, 10000);
            TheShape new_shape = null;
            try {
                new_shape =  shapefactory(shape_combo.getValue());
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
            // System.out.println(check_fist_excute);
            if(check_fist_excute == 1){
                globalX = startX;
                globalY = startY;
                check_fist_excute = 0;
            }
            selection_mode = 0;
            new_shape.setPosition (new Point((int)globalX,(int) globalY));
            new_shape.set_second_position ( new Point((int) startX, (int) startY));
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

        if(selection_mode == 1)
        {
            toolBar.setDisable(false);
        }
        else
        {
            toolBar.setDisable(true);
        }

    }
    public void close()
    {
        Platform.exit();
    }
    public void paste() throws CloneNotSupportedException
    {
        for( int i=0;i<copiedshape.size();i++)
        {
            final TheShape newshape = (TheShape) copiedshape.get(i).clone();
            gc.clearRect(0, 0, 100000, 100000);
            engine.refresh(gc);
            engine.addShape(newshape);
        }
        update_select_list();
    }
    public void copy()
    {
        for( int i=0;i<selectedshapes.size();i++)
        {
            copiedshape.add(selectedshapes.get(i));
        }
    }
    public void removeall()
    {
        final int numberOfShapes = engine.getShapes().length;
        while(engine.getShapes().length!=0)
        {
            engine.removeShape(engine.getShapes()[0]);
            System.out.println(engine.getShapes().length);
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        engine.refresh(gc);
        update_select_list();
    }
    public void applyChanges()
    {
        for(int i=0;i<movedshapes.size();i++)
        {
            for(int j=0;j<engine.getShapes().length;j++)
            {
                if(engine.getShapes()[j].equals(selectedshapes.get(i)))
                {
                    engine.updateShape(engine.getShapes()[j], movedshapes.get(i));


                }
            }
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        engine.refresh(gc);
        selection_mode = 0;
        toolBar.setDisable(true);
        update_select_list();
    }
    public Point setpoint(final Point p,final String movedirection)
    {
        if(movedirection.equals("Up"))
        {
            p.y -=20;
            return p;
        }
        if(movedirection.equals("Down"))
        {
            p.y +=20;
            return p;
        }if(movedirection.equals("Right"))
        {
            p.x +=20;
            return p;
        }if(movedirection.equals("Left"))
        {
            p.x -=20;
            return p;
        }
        return null;
    }
    public void up() throws CloneNotSupportedException
    {
        moveshapes("Up");
    }
    public void down() throws CloneNotSupportedException
    {
        moveshapes("Down");
    }
    public void left() throws CloneNotSupportedException
    {
        moveshapes("Left");
    }public void right() throws CloneNotSupportedException
    {
        moveshapes("Right");
    }
    public void updcolor() throws CloneNotSupportedException
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        engine.refresh(gc);
        if(movedshapes.size() == 0)
        {
            for(int i=0;i<selectedshapes.size();i++)
                movedshapes.add((TheShape) selectedshapes.get(i).clone());
        }

        for(int i=0;i<movedshapes.size();i++)
        {

            movedshapes.get(i).setColor(updcol.getValue());
            movedshapes.get(i).draw(gc);
        }

    }
    public void updfillcolor() throws CloneNotSupportedException
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        engine.refresh(gc);
        if(movedshapes.size() == 0)
        {
            for(int i=0;i<selectedshapes.size();i++)
                movedshapes.add((TheShape) selectedshapes.get(i).clone());
        }

        for(int i=0;i<movedshapes.size();i++)
        {
            movedshapes.get(i).setFillColor(updfillcol.getValue());
            movedshapes.get(i).draw(gc);
        }
    }
    public void moveshapes(final String moveDirection) throws CloneNotSupportedException
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10000, 10000);
        if(movedshapes.size() == 0)
        {
            for(int i=0;i<selectedshapes.size();i++)
                movedshapes.add((TheShape) selectedshapes.get(i).clone());
        }
        engine.refresh(gc);
        for(int i=0;i<movedshapes.size();i++)
        {

            if(movedshapes.get(i).getClass().getSimpleName().equals("Triangle"))
            {
                Point newposition =new Point();
                newposition = movedshapes.get(i).second_point;
                newposition= setpoint(newposition, moveDirection);
                movedshapes.get(i).second_point = newposition;
                newposition = movedshapes.get(i).thirdPoint;
                newposition= setpoint(newposition, moveDirection);
                movedshapes.get(i).thirdPoint = newposition;
            }
            Point newposition =new Point();
            newposition = movedshapes.get(i).getPosition();
            newposition= setpoint(newposition, moveDirection);
            movedshapes.get(i).setPosition(newposition);
            movedshapes.get(i).draw(gc);
            engine.refresh(gc);
            // selectrectangle.draw(gc);
        }

    }
    public void disselect()
    {
        root.getScene().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(final KeyEvent event) {
                final KeyCombination disselect = new KeyCodeCombination(KeyCode.ESCAPE);
                final KeyCombination undo = new KeyCodeCombination(KeyCode.Z,KeyCodeCombination.CONTROL_DOWN);
                final KeyCombination redo = new KeyCodeCombination(KeyCode.Y,KeyCodeCombination.CONTROL_DOWN);
                final KeyCombination copy = new KeyCodeCombination(KeyCode.C,KeyCodeCombination.CONTROL_DOWN);
                final KeyCombination paste = new KeyCodeCombination(KeyCode.V,KeyCodeCombination.CONTROL_DOWN);

                if(disselect.match(event)){
                    gc.clearRect(0, 0, 100000, 100000);
                    engine.refresh(gc);
                    selection_mode = 0;
                    selectrectangle = null;
                    selectedshapes.clear();
                    movedshapes.clear();
                    selectedshapes.clear();
                    toolBar.setDisable(true);
                }
                if(undo.match(event)){
                    undo();
                }else if(redo.match(event)){
                    redo();
                }
                else  if(copy.match(event)){
                    copy();
                }
                else if(paste.match(event)){
                    try {
                        paste();
                    } catch (final CloneNotSupportedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}