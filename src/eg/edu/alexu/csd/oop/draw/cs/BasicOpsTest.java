package eg.edu.alexu.csd.oop.draw.cs;


import java.util.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class BasicOpsTest extends Application {
    double globalX = 100;
    int state = 0;
    double radius=0;

    final Canvas canvas = new Canvas(1000, 1000);
    final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    MyDrawingEngine drawingEngine = MyDrawingEngine.getInstance();
    double globalY = 100;
    final Circle firstCircle =new Circle(100, globalX, globalY);
    public static void main(final String[] args) {
        launch(args);
    }



    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        final Group root = new Group();
        drawingEngine.addShape(firstCircle);
        state = 0;
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(final MouseEvent events) {
                final double startX = events.getSceneX();
                final double startY = events.getSceneY();
                graphicsContext.clearRect(0, 0, 1000, 1000);
                //   System.out.println(drawingEngine.shapes.size());

                final Circle movedCircle =new Circle(100, startX, startY);
                //  drawingEngine.updateShape(firstCircle, movedCircle);

                //drawingEngine.addShape(firstCircle);
                drawingEngine.refresh(graphicsContext);

                /*if(TheShape.allshapes.size()!=0)
                {
                    TheShape.allshapes.remove(TheShape.allshapes.size()-1);
                    TheShape.drawall(graphicsContext);
                }*/
                globalX = startX;
                globalY = startY;
                //TheShape.allshapes.add(n);
                //TheShape.all(graphicsContext);
                root.setOnMouseMoved(new EventHandler<MouseEvent>()
                {

                    @Override
                    public void handle(final MouseEvent event) {
                        if(state == 3){
                            root.setOnMouseMoved(null);
                            try {
                                final Circle finalCircle = (Circle)firstCircle.clone();
                                final Map<String, Double> finaCircleProp = finalCircle.getProperties();
                                finaCircleProp.put("radius", radius);
                                finalCircle.setProperties(finaCircleProp);
                                drawingEngine.updateShape(movedCircle, finalCircle);
                            } catch (final CloneNotSupportedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        final double startnX = event.getSceneX();
                        final double startnY = event.getSceneY();
                        radius =Math.sqrt(Math.pow(-globalX+startX,2.0)+Math.pow(-globalY+startY,2.0));
                        try {
                            final Circle finalCircle = (Circle)firstCircle.clone();
                            final Map<String, Double> finaCircleProp = finalCircle.getProperties();
                            finaCircleProp.put("radius", radius);
                            finalCircle.setProperties(finaCircleProp);
                            drawingEngine.updateShape(firstCircle, finalCircle);
                        } catch (final CloneNotSupportedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        graphicsContext.clearRect(0, 0, 10000, 10000);
                        drawingEngine.refresh(graphicsContext);
                        state = 2;
                        root.setOnMouseClicked(new EventHandler<MouseEvent>()
                        {
                            @Override
                            public void handle(final MouseEvent event) {
                                if(state == 2)
                                    state=3;

                            }

                        });

                    }

                });

            }
        });



        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(final GraphicsContext gc) {
        // graphicsContext.setFill(Color.GREEN);
        //   graphicsContext.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        // graphicsContext.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }
}

