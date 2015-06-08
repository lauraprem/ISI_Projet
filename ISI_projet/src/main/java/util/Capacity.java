package util;

import model.graph.GroundType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class Capacity {
    /**
     * Capacité des robots tout terrains
     *
     * @return Liste des capacités des robots tout terrain
     */
    public static List<GroundType> toutTerrainCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.STEEP);
        grounds.add(GroundType.FLOODED);
        return grounds;
    }

    /**
     * Capacité des robots chenille
     *
     * @return liste des capacités des robots tout terrain
     */
    public static List<GroundType> chenilleCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.FLOODED);
        return grounds;
    }

    /**
     * Capacité des robots a pattes
     *
     * @return liste des capacités des robots à pattes
     */
    public static List<GroundType> aPateCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.STEEP);
        return grounds;
    }

    /**
     * Liste de toutes les capacités
     *
     * @return Liste de toutes les capacités
     */
    public static List<GroundType> allCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.STEEP);
        grounds.add(GroundType.FLOODED);
        return grounds;
    }
}
