package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class Point {
    /**
     * Coordonnee x du point
     */
    public Integer x = 0;
    /**
     * Coordonne y du point
     */
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

    protected void setXString(String _x) {
        this.x = Integer.parseInt(_x);
    }

    protected void setYString(String _y) {
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

    public Point opposite() {
        return new Point(-x, -y);
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point sub(Point p) {
        return add(p.opposite());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!x.equals(point.x)) return false;
        return y.equals(point.y);

    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }
}
