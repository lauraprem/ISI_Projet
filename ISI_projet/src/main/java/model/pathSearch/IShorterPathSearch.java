package model.pathSearch;

import java.util.List;

import model.graph.Node;
import model.graph.graph.IUndirectedGraph;
import model.graph.ground.Ground;

/**
 * permet de trouver le plus court chemin
 * @author Laura
 *
 */
public interface IShorterPathSearch {

	/**
	 * Permet de trouver le plus court chemin sur un graph d'un point de départ jusqu'à un point d'arrivée
	 * @param graph sur lequel on se déplace
	 * @param start noeud de départ
	 * @param goal noeud destination
	 * @param capacity : terrain sur lesquels on peut passer
	 * @return la liste des noeuds qui composent le plus court chemin si le noeud est atteignable, liste vide si le noeud n'est pas atteignable
	 */
	public List<Node> findShorterPath(IUndirectedGraph graph, Node start, Node goal, List<Ground> capacity);
}
