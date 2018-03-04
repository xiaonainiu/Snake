import javafx.geometry.Point2D;

public enum Direction{
    LEFT(new Point2D(-1,0)),
    RIGHT(new Point2D(1,0)),
    UP(new Point2D(0,-1)),
    DOWN(new Point2D(0,1));

    private Point2D position;

    private Direction (Point2D position){
        this.position =position;
    }

    public Point2D getPosition() {
        return position;
    }
}
