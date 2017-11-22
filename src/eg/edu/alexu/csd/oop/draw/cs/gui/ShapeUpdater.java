package eg.edu.alexu.csd.oop.draw.cs.gui;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs.drawingengine.MyDrawingEngine;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class ShapeUpdater {
    private Shape shape;
    private MyDrawingEngine drawingEngine;
    private Button updateButton;
    private GraphicsContext canvas;
    public ShapeUpdater(MyDrawingEngine drawingEngine, Shape shape, Button updateButton, GraphicsContext canvas) {
        this.updateButton = updateButton;
        this.drawingEngine = drawingEngine;
        this.shape = shape;
        this.canvas = canvas;
        updateButton.setOnAction(e-> updateShape());
    }

    private void updateShape() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Hello World!");
        Button btn = new Button("set Postion = (1,1)");
        btn.setOnAction(e->{
            shape.setPosition(new Point(1,1));
            drawingEngine.refresh(canvas);
            updateStage.close();
        });
        StackPane layout = new StackPane();
        layout.getChildren().add(btn);
        updateStage.setScene(new Scene(layout, 300, 250));
        updateStage.show();
    }
}
