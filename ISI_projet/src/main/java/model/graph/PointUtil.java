package model.graph;

/**
 * @author Alexandre
 *         28/05/2015
 */
public class PointUtil {
	/**
	 * Donne la distance entre deux points
	 * @param p1 premier point
	 * @param p2 second point
	 * @return un Double contenant la distance entre les deux points
	 */
    public static Double getDistance(Point p1, Point p2) {
        return getDelta(p1, p2).getLength();
    }

    /**
     * Donne la distance au carré entre deux points (plus perfomant pour la comparaison)
     * @param p1 premier point
     * @param p2 second point
     * @return un Double contenant la distance au carré entre les deux points
     */
    public static Double getSquaredDistance(Point p1, Point p2) {
        return getDelta(p1, p2).getSquaredLength();
    }

    /**
     * Renvoie le vecteur entre deux points
     * @param p1 premier point
     * @param p2 second point
     * @return un Point contenant les coordonnees du vecteur separant les deux points
     */
    public static Point getDelta(Point p1, Point p2) {
        return p2.sub(p1);
    }
}
