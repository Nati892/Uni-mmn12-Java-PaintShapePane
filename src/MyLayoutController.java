import javafx.event.ActionEvent;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class MyLayoutController {

    private GraphicsContext gc;

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
                "jacob.smith@example.com",
                "isabella.johnson@example.com",
                "ethan.williams@example.com",
                "emma.jones@example.com",
                "michael.brown@example.com");

    }

    @FXML
    void clearOnClick(ActionEvent event) {
    }

    @FXML
    void paintPaneOnDragDetected(MouseEvent event) {
    }

    @FXML
    void paintPaneOnMouseClicked(MouseEvent event) {
    }

    @FXML
    void paintPaneOnMouseDragged(MouseEvent event) {
    }

    @FXML
    void paintPaneOnMousePressed(MouseEvent event) {
    }

    @FXML
    void paintPaneOnMouseReleased(MouseEvent event) {
    }

    @FXML
    void undoOnClick(ActionEvent event) {

    }


}
