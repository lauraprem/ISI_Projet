package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class PointUtil {
    public static Double getDistance(Point p1, Point p2) {
        return getDelta(p1, p2).getLength();
    }

    public static Double getSquaredDistance(Point p1, Point p2) {
        return getDelta(p1, p2).getSquaredLength();
    }

    public static Point getDelta(Point p1, Point p2) {
        return p2.sub(p1);
    }
}
