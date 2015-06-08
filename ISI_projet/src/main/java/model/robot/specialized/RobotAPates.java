package model.robot.specialized;

import model.graph.IGraph;
import model.graph.Node;
import model.pathSearch.IShorterPathSearch;
import model.robot.Robot;
import util.CapacityUtil;
import util.Picture;
import util.struct.Observer;

import java.io.File;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class RobotAPates extends Robot {
    private static String URLImage = Picture.ROBOT_A_PATES.getURL();

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _graph                     graph sur lequel le robot se déplace
     * @param _startNode                 noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder                méthode de calcul du plus court chemin
     * @param _decreaseFireLevelCapacity nombre d'unité de réduction de l'intensité d'un feu
     */
    public RobotAPates(IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder,
                       Integer _decreaseFireLevelCapacity, Observer o) {
        super(CapacityUtil.aPateCapacity(), _graph, _startNode, _pathFinder, _decreaseFireLevelCapacity, o);
    }

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _graph      graph sur lequel le robot se déplace
     * @param _startNode  noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder méthode de calcul du plus court chemin
     */
    public RobotAPates(IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Observer o) {
        this(_graph, _startNode, _pathFinder, null, o);
    }

    @Override
    public String getURLImage() {
        return URLImage;
    }

    public static void setURLImage(String URLImage) {
        if (new File(URLImage).exists()) RobotAPates.URLImage = URLImage;
    }
}