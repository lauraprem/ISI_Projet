package model.robot;

import java.util.List;

import model.graph.Node;
import model.graph.graph.IUndirectedGraph;
import model.graph.ground.Ground;
import model.pathSearch.IShorterPathSearch;

/**
 * Robots pompiers
 * @author Laura
 *
 */
public class Robot {
	/**
	 * Terrains sur lesquels le robot est capable de se deplacer
	 */
	private List<Ground> capacity;

	/**
	 * True si le robot est occupé à une tache, et donc non disponible
	 */
	private boolean busy;

	/**
	 * Le noeud sur lequel se trouve le robot
	 */
	private Node currentNode;

	/**
	 * Stratégie d'obtention du plus court chemin
	 */
	private IShorterPathSearch pathFinder;

	/**
	 * Graph sur lequel le robot se deplace
	 */
	private IUndirectedGraph graph;

	/**
	 * Constructeur du robot. Le robot n'est pas occupé.
	 * @param _capacity liste des terrains sur lesquels les robots peuvent evoluer
	 * @param _graph graph sur lequel le robot se déplace
	 * @param _startNode noeud sur lequel se trouve le robot pour commencer
	 * @param _pathFinder méthode de calcul du plus court chemin 
	 */
	public Robot(List<Ground> _capacity, IUndirectedGraph _graph, Node _startNode, IShorterPathSearch _pathFinder) {
		this.busy = false;
		this.capacity = _capacity;
		this.currentNode = _startNode;
		this.graph = _graph;
		this.pathFinder = _pathFinder;
	}

	public boolean stopFire(Node fire) {
		// TODO LPR
		return false;
	}

	/**
	 * Permet au manager de proposer un noeud incendié à éteindre au robot
	 * @param destination noeud à atteindre
	 * @return le cout pour aller à la destination et -1 s'il est impossible d'y aller
	 */
	public int proposeNode(Node destination) {
//		return pathFinder.findShorterPath(graph, currentNode, destination, capacity);
		return -1;
	}

	public List<Ground> getCapacity() {
		return capacity;
	}

	public void setCapacity(List<Ground> capacity) {
		this.capacity = capacity;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public IUndirectedGraph getGraph() {
		return graph;
	}

	public void setGraph(IUndirectedGraph graph) {
		this.graph = graph;
	}

	public IShorterPathSearch getPathFinder() {
		return pathFinder;
	}

	public void setPathFinder(IShorterPathSearch pathFinder) {
		this.pathFinder = pathFinder;
	}

}
