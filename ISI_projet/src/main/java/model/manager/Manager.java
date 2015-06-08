package model.manager;

import model.Observable;
import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.GraphUtil;
import model.graph.graph.IGraph;
import model.graph.graph.impl.Graph;
import model.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Observer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Le manager est celui qui va gérer les robots : il va identifier ceux qui sont les plus près des feu et assigner la tache d'éteindre le feu au robot qui en est le plus près.
 * Le manager va notifier la vue de tout changement du modèle qu'il effectuera (il est observé par la vue) et sera notifié des changements effectués en interne par les neuds ou les robots pour pouvoir egalement en avertir la vue (le manager observe le noeud et le robot)
 *
 * @author Alexandre
 *         11/05/2015
 */
public class Manager extends Thread implements Observable, Observer {
    /**
     * Temps entre les rafraichissements
     */
    private final static Long refreshTime = 200L;
    /**
     * logger de la classe
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * liste des observeurs du manager
     */
    protected ArrayList<Observer> observers = new ArrayList<>();
    /**
     * Liste des robots
     */
    private List<Robot> robots = Collections.synchronizedList(new ArrayList<Robot>());
    /**
     * Graphe a traiter
     */
    private IGraph graph = new Graph();
    /**
     * Permet de savoir si l'application doit etre quitté
     */
    private Boolean exit = Boolean.FALSE;
    /**
     * Permet de mettre l'application en pause
     */
    private Boolean pause = Boolean.FALSE;

    public Manager(IGraph graph, List<Robot> robots) {
        observers = new ArrayList<Observer>();
        this.graph = graph;
        this.robots = robots;
    }

    public Manager() {
    }

    public synchronized Graph getGraph() {
        return (Graph) graph;
    }

    public synchronized void setGraph(IGraph graph) {
        this.graph = graph;
        this.notifyObserver();
    }

    public synchronized List<Robot> getRobots() {
        return robots;
    }

    public synchronized void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        logger.info("Manager has been started.");
        Long start = System.currentTimeMillis();
        exit = Boolean.FALSE;
        Long startLoop = System.currentTimeMillis(), endLoop;
        while (!isExited()) {
            endLoop = System.currentTimeMillis();
            if (endLoop - startLoop > refreshTime) {
                startLoop = System.currentTimeMillis();
                robots.forEach(Robot::update);
                askDistanceToUnoccupiedRobots(GraphUtil.getUntakenCareOfNodesOnFire(graph));
                graph.update();
            }
            if (isPaused())
                logger.info("Manager has been paused.");
            while (isPaused()) {
                if (!isPaused())
                    logger.info("Manager has been unpaused.");
            }
        }
        logger.info("Manager has stopped, ran for %ss.", (System.currentTimeMillis() - start) / 1000);
    }

    /**
     * Permet de connaitre la distance à laquelle se trouve chaque robot de chaque feu
     *
     * @param nodesOnFire liste des noeuds en feu
     */
    private synchronized void askDistanceToUnoccupiedRobots(List<Node> nodesOnFire) {
        List<Robot> unoccupiedRobots;
        Map<Robot, Double> distances = Collections.synchronizedMap(new HashMap());
        for (Node node : nodesOnFire) {
            distances.clear();
            unoccupiedRobots = getUnoccupiedRobots();
            unoccupiedRobots.stream().forEach(robot -> {
                Double distance = robot.proposeNode(node);
                if (distance != -1)
                    distances.put(robot, distance);
            });
            if (distances.size() != 0)
                Collections.min(distances.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey().acceptPath();
        }
    }

    /**
     * Permet de connaitre la distance à laquelle se trouve chaque robot de chaque feu
     *
     * @param nodesOnFire liste des noeuds en feu
     */
    private synchronized void askDistanceToAllRobots(List<Node> nodesOnFire) {
        Map<Robot, Double> distances = Collections.synchronizedMap(new HashMap());
        for (Node node : nodesOnFire) {
            distances.clear();
            robots.stream().filter(robot1 -> !distances.containsKey(robot1)).forEach(robot -> {
                Double distance = robot.proposeNode(node);
                if (distance != -1)
                    distances.put(robot, distance);
            });
            if (distances.size() != 0)
                Collections.min(distances.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey().acceptPath();
        }
    }

    /**
     * Permet de connaitre la liste des robots innocupés
     *
     * @return la liste des robots innocupés
     */
    private synchronized List<Robot> getUnoccupiedRobots() {
        return getRobots().stream().filter(robot -> !robot.isBusy()).collect(Collectors.toList());
    }

    /**
     * Permet de connaitre la liste des noeuds en feu
     *
     * @return la liste des noeuds en feu
     */
    private synchronized List<Node> getNodesOnFire() {
        return GraphUtil.getNodesOnFire(graph);
    }

    public synchronized void addNode(Node n) {
        n.setIdToNextAvailable();
        graph.addNode(n, this);
        notifyObserver();
    }

    public synchronized void addEdge(Edge e) {
        graph.addEdge(e, this);
        notifyObserver();
    }

    public synchronized void addRobot(Robot r) {
        robots.add(r);
        notifyObserver();
    }

    public synchronized void exitManager() {
        exit = Boolean.TRUE;
    }

    public synchronized void pauseManager() {
        pause = Boolean.TRUE;
    }

    public synchronized void unPauseManager() {
        pause = Boolean.FALSE;
    }

    private synchronized Boolean isExited() {
        return exit;
    }

    public synchronized Boolean isPaused() {
        return pause;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void removeObserver(Observer o) {
        observers.remove(o);
    }


    @Override
    public synchronized void update() {
        notifyObserver();
    }

    public synchronized void reset() {
        robots = Collections.synchronizedList(new ArrayList<Robot>());
        graph = new Graph();
        exit = Boolean.FALSE;
        pause = Boolean.FALSE;
        notifyObserver();
    }

    @Override
    public synchronized void notifyObserver() {
        if (observers != null)
            observers.stream().filter(obs -> obs != null).forEach(view.Observer::update);
    }


}
