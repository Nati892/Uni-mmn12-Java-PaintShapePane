/*had to use this class because I wanted the x,y values to be editable so that I could normalize them*/

import javafx.scene.input.MouseEvent;

public class Point {

    private double _x_pos;
    private double _y_pos;

    /**
     * trivial constructor
     */
    public Point(double _x_pos, double _y_pos) {
        this._x_pos = _x_pos;
        this._y_pos = _y_pos;
    }

    /**
     * Constructs point from the event's scene x,y coordinates
     *
     * @param event is a MouseEvent
     */
    public Point(MouseEvent event) {
        this._x_pos = event.getSceneX();
        this._y_pos = event.getSceneY();
    }

    public double get_x_pos() {
        return _x_pos;
    }

    public void set_x_pos(double _x_pos) {
        this._x_pos = _x_pos;
    }

    public double get_y_pos() {
        return _y_pos;
    }

    public void set_y_pos(double _y_pos) {
        this._y_pos = _y_pos;
    }

    //needed for debugging
    @Override
    public String toString() {
        return "Point{" +
                "_x_pos=" + _x_pos +
                ", _y_pos=" + _y_pos +
                '}';
    }
}
