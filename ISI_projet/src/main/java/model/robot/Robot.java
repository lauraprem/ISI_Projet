package model.robot;

import model.graph.Node;
import model.graph.graph.IGraph;
import model.graph.ground.GroundType;
import model.pathSearch.IShorterPathSearch;

import java.util.List;

/**
 * Robots pompiers
 *
 * @author Laura
 */
public class Robot {
    /**
     * Types de terrains sur lesquels le robot est capable de se deplacer
     */
    private List<GroundType> capacity;

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
     * Chemin à parcourir
     */
    private NodePath path;

    /**
     * Graph sur lequel le robot se deplace
     */
    private IGraph graph;

    private Integer decreaseFireLevelCapacity;

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _capacity   liste des terrains sur lesquels les robots peuvent evoluer
     * @param _graph      graph sur lequel le robot se déplace
     * @param _startNode  noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder méthode de calcul du plus court chemin
     */
    public Robot(List<GroundType> _capacity, IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder) {
        this(_capacity, _graph, _startNode, _pathFinder, null);
    }

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _capacity                  liste des terrains sur lesquels les robots peuvent evoluer
     * @param _graph                     graph sur lequel le robot se déplace
     * @param _startNode                 noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder                méthode de calcul du plus court chemin
     * @param _decreaseFireLevelCapacity nombre d'unité de réduction de l'intensité d'un feu
     */
    public Robot(List<GroundType> _capacity, IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Integer _decreaseFireLevelCapacity) {
        busy = false;
        capacity = _capacity;
        currentNode = _startNode;
        graph = _graph;
        pathFinder = _pathFinder;
        decreaseFireLevelCapacity = _decreaseFireLevelCapacity;
    }

    public Boolean stopFire() {
        if (!currentNode.isOnFire()) return Boolean.TRUE;
        if (decreaseFireLevelCapacity == null) currentNode.decreaseFireLevel(currentNode.getFireLevel());
        else currentNode.decreaseFireLevel(decreaseFireLevelCapacity);
        graph.getAdjEdges(currentNode).forEach(edge -> {
            // TODO AGA : Voir pour changer la valeur
            edge.getGround().increaseChancesOfGettingFlooded(0.25);
            edge.updateGround();
        });
        return !currentNode.isOnFire();
    }

    /**
     * Permet au manager de proposer un noeud incendié à éteindre au robot
     *
     * @param destination noeud à atteindre
     * @return le cout pour aller à la destination et -1 s'il est impossible d'y aller
     */
    public Float proposeNode(Node destination) {
		return pathFinder.findShorterPath(graph, currentNode, destination, capacity, path);
    }

    public void acceptPath() {
        busy = Boolean.TRUE;
        path.accept();
    }

    public List<GroundType> getCapacity() {
        return capacity;
    }

    public void setCapacity(List<GroundType> capacity) {
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

    public IGraph getGraph() {
        return graph;
    }

    public void setGraph(IGraph graph) {
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

    public void update() {
        if (path.hasNext()) {
            currentNode = path.next();
        } else if (!currentNode.isOnFire() || currentNode.isOnFire() && stopFire())
            setBusy(Boolean.FALSE);
    }
}
