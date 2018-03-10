import java.lang.*;
import java.util.*;
import javafx.geometry.Point2D;

public class Snake {
    private Direction direction = Direction.RIGHT;
    private Point2D head;
    private Point2D previousTail;
    List<Point2D> body = new ArrayList<>();

    public Snake(Point2D head){
        this.head = head;
        this.previousTail = head;
        body.add(head);
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void update(){
        head = head.add(direction.getPosition());

        previousTail = body.remove(body.size()-1);
        body.add(0,head);
    }

    public Point2D getPosition() {
        return head;
    }

    public boolean eatFood(Food food){
        return head.equals(food.getPosition());
    }


    public void grow(){
        this.body.add(previousTail);
    }

    public boolean isDead() {
        return body.lastIndexOf(head) > 0;
    }

    public int getLength(){
        return this.body.size();
    }

    public List<Point2D> getBody() {
        return this.body;
    }

    public boolean isOutOfBounds(int size){
        if(this.head.getX()<0||this.head.getY()<0||this.head.getX()>size||this.head.getY()>size){
            return true;
        }
        return false;
    }


}