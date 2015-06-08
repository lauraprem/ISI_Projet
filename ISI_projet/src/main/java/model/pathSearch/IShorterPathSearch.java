package model.pathSearch;

import model.graph.Node;
import model.graph.IGraph;
import model.graph.GroundType;
import model.robot.NodePath;

import java.util.List;

/**
 * permet de trouver le plus court chemin
 *
 * @author Laura
 */
public interface IShorterPathSearch {

    /**
     * Permet de trouver le plus court chemin sur un graph d'un point de départ jusqu'à un point d'arrivée
     *
     * @param graph    sur lequel on se déplace
     * @param start    noeud de départ
     * @param goal     noeud destination
     * @param capacity : terrain sur lesquels on peut passer
     * @return la liste des noeuds qui composent le plus court chemin si le noeud est atteignable, liste vide si le noeud n'est pas atteignable
     */
    Double findShorterPath(IGraph graph, Node start, Node goal, List<GroundType> capacity, NodePath nodePath);

}
