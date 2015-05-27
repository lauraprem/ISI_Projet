package model.robot.specialized;

import model.graph.Node;
import model.graph.graph.IGraph;
import model.graph.ground.GroundType;
import model.pathSearch.IShorterPathSearch;
import model.robot.Capacity;
import model.robot.Robot;

import java.util.List;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class RobotChenille extends Robot {
    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _graph                     graph sur lequel le robot se déplace
     * @param _startNode                 noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder                méthode de calcul du plus court chemin
     * @param _decreaseFireLevelCapacity nombre d'unité de réduction de l'intensité d'un feu
     */
    public RobotChenille(IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Integer _decreaseFireLevelCapacity) {
        super(Capacity.ChenilleCapacity(), _graph, _startNode, _pathFinder, _decreaseFireLevelCapacity);
    }

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _graph      graph sur lequel le robot se déplace
     * @param _startNode  noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder méthode de calcul du plus court chemin
     */
    public RobotChenille(IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder) {
        this(_graph, _startNode, _pathFinder, null);
    }
}