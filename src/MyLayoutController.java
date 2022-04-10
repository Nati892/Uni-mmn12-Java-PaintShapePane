import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class MyLayoutController {

    private GraphicsContext gc;

    private boolean filled = true;
    private boolean hasbeendragged = false;
    private MouseEvent startpos = null;


    @FXML
    private GridPane bottom_pane;

    @FXML
    private Button clear_button;

    @FXML
    private ColorPicker color_picker;

    @FXML
    private HBox my_box;

    @FXML
    private VBox my_vbox;

    @FXML
    private Pane paint_pane;

    @FXML
    private ToggleButton shape_fill_select;

    @FXML
    private ComboBox<String> shape_selector;

    @FXML
    private Button undo_button;


    @FXML
    public void initialize() {
        shape_selector.getItems().addAll(
    "Rectangle", "Circle", "line"
        );

        shape_selector.setValue("Rectangle");
        color_picker.setValue(Color.BLACK);
    }

    @FXML
    void clearOnClick(ActionEvent event) {
        paint_pane.getChildren().clear();

    }

    @FXML
    void paintPaneOnDragDetected(MouseEvent event) {
        System.out.println("on drag");

    }

    @FXML
    void paintPaneOnMouseClicked(MouseEvent event) {


    }

    @FXML
    void paintPaneOnMouseDragged(MouseEvent event) {
        System.out.println("on mouse dragged");
        if (!hasbeendragged) {
            hasbeendragged = true;

        } else {


        }

    }

    /*TODO remove event*/
    @FXML
    void paintPaneOnMousePressed(MouseEvent event) {
        startpos = event;

    }

    @FXML
    void paintPaneOnMouseReleased(MouseEvent event) {
        System.out.println("on mouse released");
        int xpos;
        int ypos;

        if (hasbeendragged && startpos != null) {
            hasbeendragged = !hasbeendragged;
            paintShape(event);
        }
        if (startpos != null) {
            System.out.println("START event");
            System.out.println(startpos.toString());
        }
        System.out.println("END event");
        System.out.println(event.toString());
    }

    @FXML
    void undoOnClick(ActionEvent event) {
        if (paint_pane.getChildren().size() > 0) {
            paint_pane.getChildren().remove(paint_pane.getChildren().size() - 1);
        }
    }

    private void paintShape(MouseEvent endpos) {
        Shape shape;
        shape = determineShape(startpos, endpos);


        if (shape_fill_select.isSelected()) {
            shape.setFill(Color.TRANSPARENT);
            shape.setStrokeWidth(1.0);
            shape.setStroke(color_picker.getValue());

        } else {
            shape.setFill(color_picker.getValue());
        }
        paint_pane.getChildren().add(shape);
    }

    private Shape determineShape(MouseEvent start, MouseEvent end_pos) {
        int startx = 0, starty = 0;
        int sizex = (int) (end_pos.getSceneX() - start.getSceneX());
        int sizey = (int) (end_pos.getSceneY() - start.getSceneY());

        //set x start pos
        if (sizex < 0) {
            System.out.println("x is negative");
            sizex *= -1;
            startx = (int) end_pos.getSceneX();
        } else {
            startx = (int) start.getSceneX();
            System.out.println("x is positive");
        }

        //set y start pos
        if (sizey < 0) {
            sizey *= -1;
            starty = (int) end_pos.getSceneY();
            System.out.println("y is negative");
        } else {
            starty = (int) start.getSceneY();
            System.out.println("y is positive");
        }
        starty -= paint_pane.getLayoutY();

        Shape shape = null;
        switch (shape_selector.getValue()) {
            case "Rectangle":
                shape = new Rectangle(startx, starty, sizex, sizey);
                break;

            case "Circle":
                shape = new Circle(startx + Math.min(sizex, sizey) / 2, starty + Math.min(sizex, sizey) / 2, Math.min(sizex, sizey));
                break;

            case "line":
                shape = new Line(start.getSceneX(), start.getSceneY()-paint_pane.getLayoutY(), end_pos.getSceneX(), end_pos.getSceneY()-paint_pane.getLayoutY());
                break;



        }
        return shape;

    }


}
