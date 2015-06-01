package model.robot;

import java.util.ArrayList;
import java.util.List;

import model.Observable;
import model.graph.Node;
import model.graph.graph.IGraph;
import model.graph.ground.GroundType;
import model.pathSearch.IShorterPathSearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.Observer;

/**
 * Robots pompiers
 *
 * @author Laura
 */
public abstract class Robot implements Observable {
    /**
     * Logger de la classe
     */
    private final static Logger logger = LogManager.getLogger();
    /**
     * Liste des observeurs du robot
     */
    protected ArrayList<Observer> observers = new ArrayList<>();

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
    private NodePath path = new NodePath();

    /**
     * Graph sur lequel le robot se deplace
     */
    private IGraph graph;

    /**
     * Capacité du robot à réduire l'intensité d'un feu
     */
    private Integer decreaseFireLevelCapacity;

    /**
     * Constructeur du robot. Le robot n'est pas occupé.
     *
     * @param _capacity   liste des terrains sur lesquels les robots peuvent evoluer
     * @param _graph      graph sur lequel le robot se déplace
     * @param _startNode  noeud sur lequel se trouve le robot pour commencer
     * @param _pathFinder méthode de calcul du plus court chemin
     */
    public Robot(List<GroundType> _capacity, IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Observer o) {
        this(_capacity, _graph, _startNode, _pathFinder, null, o);
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
    public Robot(List<GroundType> _capacity, IGraph _graph, Node _startNode, IShorterPathSearch _pathFinder, Integer _decreaseFireLevelCapacity, Observer o) {
        busy = false;
        capacity = _capacity;
        currentNode = _startNode;
        graph = _graph;
        pathFinder = _pathFinder;
        decreaseFireLevelCapacity = _decreaseFireLevelCapacity;
        addObserver(o);
    }

    /**
     * Le robot tente d'éteindre un feu. Il va diminuer l'intensité du feu d'autant qu'il en est capable.
     * Si le feu est éteind à l'issu de l'opération, la probabilité que les arretes adjacentes au noeud soient inondés augmente. Il est même possible que ces arretes soient effectivement inondées.
     *
     * @return true si le robot a éteind le feu, false sinon
     */
    public Boolean stopFire() {
        if (!currentNode.isOnFire()) return Boolean.TRUE;
        if (decreaseFireLevelCapacity == null) currentNode.decreaseFireLevel(currentNode.getFireLevel());
        else currentNode.decreaseFireLevel(decreaseFireLevelCapacity);
        graph.getAdjEdges(currentNode).forEach(edge -> {
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
    public Double proposeNode(Node destination) {
        path = new NodePath();
        return pathFinder == null ? -1.0 : pathFinder.findShorterPath(graph, currentNode, destination, capacity, path);
    }

    public void acceptPath() {
        busy = Boolean.TRUE;
        path.accept();
        logger.info(String.format("A robot has been assigned to stop the fire at \"%s\"", path.getDestination()));
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
        notify();
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
        Node next = (path.hasNext() ? path.next() : null);
        if (next != null && !next.equals(currentNode)) {
            currentNode = next;
            logger.info(String.format("A robot is now at \"%s\"", currentNode));
        } else if (currentNode.isOnFire() && stopFire()) {
            setBusy(Boolean.FALSE);
            logger.info(String.format("A robot has stop a fire at \"%s\". " +
                    "It's now available for annother task.", currentNode));
        }
        notifyObserver();
    }

    private void setPath(NodePath path) {
        this.path = path;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        if (observers != null)
            observers.stream().filter(obs -> obs != null).forEach(view.Observer::Update);
    }

    public abstract String getURLImage();
}
