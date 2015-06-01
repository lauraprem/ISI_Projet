package model.robot.specialized;

import model.graph.Node;
import model.graph.graph.IGraph;
import model.pathSearch.IShorterPathSearch;
import model.robot.Capacity;
import model.robot.Robot;
import view.Observer;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class RobotAPates extends Robot {
    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _graph                     graph sur lequel le robot se déplace
     * @param _startNode                 noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder                méthode de calcul du plus court chemin
     * @param _decreaseFireLevelCapacity nombre d'unité de réduction de l'intensité d'un feu
     */
    public RobotAPates(IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Integer _decreaseFireLevelCapacity, Observer o) {
        super(Capacity.APateCapacity(), _graph, _startNode, _pathFinder, _decreaseFireLevelCapacity, o);
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
}