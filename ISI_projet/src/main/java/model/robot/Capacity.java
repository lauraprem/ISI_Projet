package model.robot;

import model.graph.ground.GroundType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class Capacity {
    public static List<GroundType> ToutTerrainCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.STEEP);
        grounds.add(GroundType.FLOODED);
        return grounds;
    }

    public static List<GroundType> ChenilleCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.FLOODED);
        return grounds;
    }

    public static List<GroundType> APateCapacity() {
        List<GroundType> grounds = new ArrayList<>();
        grounds.add(GroundType.FLAT);
        grounds.add(GroundType.STEEP);
        return grounds;
    }
}
