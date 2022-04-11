import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    //field indicating whether a drag has started
    private boolean drag_started = false;
    private Point mouse_start_pos = null;
    private Point mouse_end_pos = null;

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
        populateShapeSelector(shape_selector);
        //init color picker with black
        color_picker.setValue(Color.BLACK);
    }

    @FXML
    void clearOnClick(ActionEvent event) {
        //clear screen from all shapes
        paint_pane.getChildren().clear();

    }


    @FXML
    void paintPaneOnMousePressed(MouseEvent event) {
        mouse_start_pos = new Point(event);

    }

    @FXML
    void paintPaneOnMouseReleased(MouseEvent event) {
        mouse_end_pos = new Point(event);
        normalize_end_point(mouse_end_pos);

        if (mouse_end_pos.get_x_pos() != mouse_start_pos.get_x_pos() || mouse_end_pos.get_y_pos() != mouse_start_pos.get_y_pos()) {
            drag_started = false;
            paintShape();
        }
    }

    @FXML
    void undoOnClick(ActionEvent event) {
        if (paint_pane.getChildren().size() > 0) {
            paint_pane.getChildren().remove(paint_pane.getChildren().size() - 1);
        }
    }

    /**
     * determines the shapes parameters and draws it on the pane
     */
    private void paintShape() {
        Shape shape;
        shape = determineShape();//generate shape

        //handled filled/hollow option for shape
        if (shape_fill_select.isSelected()) {
            shape.setFill(Color.TRANSPARENT);
            shape.setStrokeWidth(1.0);
            shape.setStroke(color_picker.getValue());

        } else {
            shape.setFill(color_picker.getValue());

        }
        //paint the shape
        paint_pane.getChildren().add(shape);
    }

    private Shape determineShape() {
        Shape shape = null;

        switch (shape_selector.getValue()) {
            case "Rectangle":
                shape = makeRect();
                break;

            case "Circle":
                shape = makeCircle();
                break;

            case "line":
                shape = makeLine();
                break;
        }
        return shape;

    }


    private Rectangle makeRect() {
        //determine positions
        int startx = 0, starty = 0;
        int sizex = (int) (mouse_end_pos.get_x_pos() - mouse_start_pos.get_x_pos());
        int sizey = (int) (mouse_end_pos.get_y_pos() - mouse_start_pos.get_y_pos());

        //set x start pos
        if (sizex < 0) {

            sizex *= -1;
            startx = (int) mouse_end_pos.get_x_pos();
        } else {
            startx = (int) mouse_start_pos.get_x_pos();

        }

        //set y start pos
        if (sizey < 0) {
            sizey *= -1;
            starty = (int) mouse_end_pos.get_y_pos();
        } else {
            starty = (int) mouse_start_pos.get_y_pos();
        }
        starty -= paint_pane.getLayoutY();
        return new Rectangle(startx, starty, sizex, sizey);
    }


    private Line makeLine() {
        return new Line(mouse_start_pos.get_x_pos(), mouse_start_pos.get_y_pos() - paint_pane.getLayoutY(), mouse_end_pos.get_x_pos(), mouse_end_pos.get_y_pos() - paint_pane.getLayoutY());
    }

    private Circle makeCircle() {
        //determine radius by the Pythagorean theorem
        int radius = (int) Math.sqrt(Math.pow(Math.abs(mouse_start_pos.get_x_pos() - mouse_end_pos.get_x_pos()), 2) + Math.pow(Math.abs(mouse_start_pos.get_y_pos() - mouse_end_pos.get_y_pos()), 2)) / 2;

        int x_difference = (int) (mouse_end_pos.get_x_pos() - mouse_start_pos.get_x_pos()) / 2;
        int y_difference = (int) (mouse_end_pos.get_y_pos() - mouse_start_pos.get_y_pos()) / 2;

        int center_x = (int) (mouse_start_pos.get_x_pos() + x_difference);
        int center_y = (int) (mouse_start_pos.get_y_pos() + y_difference - bottom_pane.getHeight());

/*        normalize the radius so the circle won't overflow from the top of the pane
        but instead will be pushed down in the pain.*/

        if ((center_y - radius) < bottom_pane.getHeight()) {
            center_y += (radius - center_y);
        }
        radius = Math.abs(radius);
        return new Circle(center_x, center_y, radius);
    }

    //populates the combo box with the shapes as String
    private void populateShapeSelector(ComboBox<String> cb) {
        cb.getItems().addAll("Rectangle", "Circle", "line");
        //first value to be picked
        cb.setValue("Rectangle");
    }

    //this function makes sure that shapes won't be drawn above the upper bounds
    private void normalize_end_point(Point p) {

        if (p.get_y_pos() < bottom_pane.getHeight()) p.set_y_pos(bottom_pane.getHeight());
    }
}

