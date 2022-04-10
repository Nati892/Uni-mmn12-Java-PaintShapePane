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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class MyLayoutController {

    private GraphicsContext gc;

    private boolean filled = true;
    private boolean hasbeendragged = false;
    private MouseEvent startpos = null;
    private int sizee = 40;

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
    private ComboBox<Shape> shape_selector;

    @FXML
    private Button undo_button;


    @FXML
    public void initialize() {
        shape_selector.getItems().addAll(

        );

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
        int startx = 0, starty = 0;
        int sizex = (int) (endpos.getSceneX() - startpos.getSceneX());
        int sizey = (int) (endpos.getSceneY() - startpos.getSceneY());


        if (sizex < 0) {
            System.out.println("x is negative");
            sizex *= -1;
            startx = (int) endpos.getSceneX();
        } else {
            startx = (int) startpos.getSceneX();
            System.out.println("x is positive");
        }


        if (sizey < 0) {
            sizey *= -1;
            starty = (int) endpos.getSceneY();
            System.out.println("y is negative");
        } else {
            starty = (int) startpos.getSceneY();
            System.out.println("y is positive");
        }
        starty -= paint_pane.getLayoutY();

        System.out.println("startx: " + startx + ". starty: " + starty + ". sizex: " + sizex + ". sizey: " + sizey);
        Rectangle rect = new Rectangle(startx, starty, sizex, sizey);
        System.out.println(sizee + "<------");
        //rect =new Rectangle(sizee, sizee ,sizee, sizee);
        sizee += 10;

        rect.setFill(Color.DEEPPINK);
        paint_pane.getChildren().add(rect);


    }


}
