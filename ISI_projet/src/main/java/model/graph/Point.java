package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class Point {
    public Integer x = 0;
    public Integer y = 0;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(java.awt.Point p) {
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
        Integer delta = x * x + y * y;
        return delta.doubleValue();
    }

    private void setXString(String _x) {
        this.x = Integer.parseInt(_x);
    }

    private void setYString(String _y) {
        this.x = Integer.parseInt(_y);
    }

    public Point clone() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
