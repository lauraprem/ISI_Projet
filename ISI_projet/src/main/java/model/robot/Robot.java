package model.robot;

import model.graph.*;
import util.struct.Observable;
import util.PointUtil;
import model.pathSearch.IShorterPathSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.struct.Observer;
import util.struct.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Robots pompiers
 *
 * @author Laura
 */
public abstract class Robot implements Observable, Updatable {
    private static Double vitesse = 3.0;
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
     * Le noeud sur lequel se dirige le robot
     */
    private Node nextNode = null;

    /**
     * La position du robot
     */
    private Point position;


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
        position = new Point(currentNode);
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
        if (!positionIsEqualToCurrentNode()) return Boolean.FALSE;

        if (!currentNode.isOnFire()) return Boolean.TRUE;
        if (decreaseFireLevelCapacity == null) currentNode.decreaseFireLevel(currentNode.getFireLevel());
        else currentNode.decreaseFireLevel(decreaseFireLevelCapacity);
        graph.getAdjEdges(currentNode).forEach(edge -> {
            edge.getGround().increaseChancesOfGettingFlooded(0.01);
            edge.update();
        });
        if(!currentNode.isOnFire()) currentNode.setTakenCareOf(Boolean.FALSE);
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
        return pathFinder == null ? -1.0 : (
                nextNode == null ?
                        pathFinder.findShorterPath(graph, currentNode, destination, capacity, path) :
                        pathFinder.findShorterPath(graph, nextNode, destination, capacity, path) + PointUtil.getDelta(position, nextNode).getLength());
    }

    public void acceptPath() {
        if(path.getDestination() != null) {
            busy = Boolean.TRUE;
            path.getDestination().setTakenCareOf(Boolean.TRUE);
            path.accept();
            logger.info(String.format("A robot has been assigned to stop the fire at \"%s\"", path.getDestination()));
        }
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
            observers.stream().filter(obs -> obs != null).forEach(Observer::update);
    }

    public abstract String getURLImage();

    public Point getPosition() {
        return position;
    }

    private void setPosition(Point position) {
        this.position = position;
        notifyObserver();
    }

    public Node getNextNode() {
        return nextNode;
    }

    private void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    private Boolean positionIsEqualToCurrentNode() {
        return new Point(currentNode).equals(position);
    }

    public static Double getVitesse() {
        return vitesse;
    }


    public void update() {
        if (nextNode == null) {
            nextNode = (path.hasNext() ? path.next() : null);
            if (currentNode.equals(nextNode)) nextNode = (path.hasNext() ? path.next() : null);
        }

        if (nextNode != null && !currentNode.equals(nextNode)) {
            if(!positionIsEqualToCurrentNode() && !capacity.contains(graph.getEdgeFromNodes(currentNode, nextNode).getGround().getType())) {
                if(path.getDestination() == null) nextNode.setTakenCareOf(Boolean.FALSE);
                else path.getDestination().setTakenCareOf(Boolean.FALSE);
                nextNode = null;
                path = new NodePath();
            }
            else if (PointUtil.getDistance(position, nextNode) < vitesse) {
                setPosition(new Point(nextNode));
                currentNode = nextNode;
                nextNode = null;
            } else {
                setPosition(position.add(PointUtil.getUnitaryDelta(position, nextNode).scale(vitesse)));
            }
            logger.info(String.format("A robot is now at \"%s\"", position));
        } else if (currentNode.isOnFire() && stopFire()) {
            setBusy(Boolean.FALSE);
            logger.info(String.format("A robot has stop a fire at \"%s\". " +
                    "It's now available for annother task.", currentNode));
        }

        if(busy == Boolean.TRUE && nextNode == null && !positionIsEqualToCurrentNode()) {
            busy = Boolean.FALSE;
            nextNode = null;
        }
        notifyObserver();
    }
}
