package model.robot;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IUndirectedGraph;
import model.graph.ground.Ground;
import model.pathSearch.IShorterPathSearch;

import java.util.List;

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
	private Boolean busy;

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

	private Integer decreaseFireLevelCapacity;

	/**
	 * Constructeur du robot. Le robot n'est pas occupé.
	 * @param _capacity liste des terrains sur lesquels les robots peuvent evoluer
	 * @param _graph graph sur lequel le robot se déplace
	 * @param _startNode noeud sur lequel se trouve le robot pour commencer
	 * @param _pathFinder méthode de calcul du plus court chemin
	 */
	public Robot(List<Ground> _capacity, IUndirectedGraph _graph, Node _startNode, IShorterPathSearch _pathFinder) {
		this(_capacity, _graph, _startNode, _pathFinder, null);
	}

	/**
	 * Constructeur du robot. Le robot n'est pas occupé.
	 * @param _capacity liste des terrains sur lesquels les robots peuvent evoluer
	 * @param _graph graph sur lequel le robot se déplace
	 * @param _startNode noeud sur lequel se trouve le robot pour commencer
	 * @param _pathFinder méthode de calcul du plus court chemin
	 * @param _decreaseFireLevelCapacity nombre d'unité de réduction de l'intensité d'un feu
	 */
	public Robot(List<Ground> _capacity, IUndirectedGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Integer _decreaseFireLevelCapacity) {
		busy = false;
		capacity = _capacity;
		currentNode = _startNode;
		graph = _graph;
		pathFinder = _pathFinder;
		decreaseFireLevelCapacity = _decreaseFireLevelCapacity;
	}

    public Boolean stopFire(Node fire) {
        if (decreaseFireLevelCapacity == null) fire.decreaseFireLevel(fire.getFireLevel());
        else fire.decreaseFireLevel(decreaseFireLevelCapacity);
        graph.getAdjEdges(fire).forEach(edge -> {
            // TODO AGA : Voir pour changer la valeur
            edge.getGround().increaseChancesOfGettingFlooded(0.25);
            edge.getGround().updateType();
        });
        return !fire.isOnFire();
    }

	/**
	 * Permet au manager de proposer un noeud incendié à éteindre au robot
	 * @param destination noeud à atteindre
	 * @return le cout pour aller à la destination et -1 s'il est impossible d'y aller
	 */
	public int proposeNode(Node destination) {
        // TODO LPR : Supprimer noeuds avec arrêtes non marchées + transformer Node en extendedNode dans le graphe
//		return pathFinder.findShorterPath(graph, currentNode, destination, capacity);
		return -1;
	}

	public List<Ground> getCapacity() {
		return capacity;
	}

	public void setCapacity(List<Ground> capacity) {
		this.capacity = capacity;
	}

	public Boolean isBusy() {
		return busy;
	}

	public void setBusy(Boolean busy) {
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

	public Integer getDecreaseFireLevelCapacity() {
		return decreaseFireLevelCapacity;
	}

	public void setDecreaseFireLevelCapacity(Integer decreaseFireLevelCapacity) {
		this.decreaseFireLevelCapacity = decreaseFireLevelCapacity;
	}
}
