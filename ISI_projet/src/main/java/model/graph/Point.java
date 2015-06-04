package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class Point implements Cloneable {
    /**
     * Coordonnee x du point
     */
    public Double x = 0.0;
    /**
     * Coordonne y du point
     */
    public Double y = 0.0;

    public Point(Integer x, Integer y) {
        this.x = x.doubleValue();
        this.y = y.doubleValue();
    }


    public Point(Double x, Double y) {
        this(x.intValue(), y.intValue());
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point(java.awt.Point p) {
        this(p.x, p.y);
    }

    public Point() {
        this(0, 0);
    }

    public Integer getX() {
        return x.intValue();
    }

    public void setX(Integer x) {
        this.x = x.doubleValue();
    }

    public void setX(Double x) {
        setX(x.intValue());
    }

    public Integer getY() {
        return y.intValue();
    }

    public void setY(Integer y) {
        this.y = y.doubleValue();
    }

    public void setY(Double y) {
        setY(y.intValue());
    }

    public Double getLength() {
        return Math.sqrt(getSquaredLength());
    }

    public Double getSquaredLength() {
        return x * x + y * y;
    }

    protected void setXString(String _x) {
        this.x = ((Integer) Integer.parseInt(_x)).doubleValue();
    }

    protected void setYString(String _y) {
        this.y = ((Integer) Integer.parseInt(_y)).doubleValue();
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

    public Point scale(Double s) {
        return new Point(x * s, y * s);
    }
}
