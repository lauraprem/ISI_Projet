package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class NodeUtil {
    public static Double getDistance(Node n1, Node n2) {
        return Math.sqrt(getSquaredDistance(n1, n2));
    }

    public static Double getSquaredDistance(Node n1, Node n2) {
        return getDelta(n1, n2).getLength();
    }

    public static Point getDelta(Node n1, Node n2) {
        return new Point(n2.getX() - n1.getX(), n2.getY() - n1.getY());
    }
}
