package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class Point {
    private Integer x = 0;
    private Integer y = 0;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Double getLength() {
        return Math.sqrt(getSquaredLength());
    }

    public Double getSquaredLength() {
        Integer delta = x*x + y*y;
        return delta.doubleValue();
    }

    public Point clone() {
        return new Point(x, y);
    }
}
