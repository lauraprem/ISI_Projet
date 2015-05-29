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
 * @author Alexandre
 *         11/05/2015
 */
public class Manager extends Thread implements Observable, Observer {
    private static Logger logger = LogManager.getLogger();
    protected ArrayList<Observer> observers = new ArrayList<>();
    private List<Robot> robots = Collections.synchronizedList(new ArrayList<Robot>());
    private IGraph graph = new Graph();
    private Boolean exit = Boolean.FALSE;
    private Boolean pause = Boolean.FALSE;
    private Long refreshTime = 1000L;

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
                askDistanceToRobots(GraphUtil.getNodesOnFire(graph),
                        ManagerUtil.getUnoccupiedRobots(this));
            }
            if (isPaused()) logger.info("Manager has been paused.");
            while (isPaused()) {
                if (!isPaused()) logger.info("Manager has been unpaused.");
            }
        }
        logger.info("Manager has stopped, ran for %ss.", (System.currentTimeMillis() - start)/1000);
    }

    private void askDistanceToRobots(List<Node> nodesOnFire, List<Robot> unoccupiedRobots) {
        Map<Robot, Double> distances = Collections.synchronizedMap(new HashMap());
        for (Node node : nodesOnFire) {
            distances.clear();
            unoccupiedRobots.stream()
                    .forEach(robot -> {
                        Double distance = robot.proposeNode(node);
                        if (distance != -1) distances.put(robot, distance);
                    });
            if (distances.size() != 0) Collections.min(
                    distances.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey().acceptPath();
        }
    }


    private List<Robot> getUnoccupiedRobots() {
        return getRobots().stream().filter(robot -> !robot.isBusy()).collect(Collectors.toList());
    }

    private List<Node> getNodesOnFire() {
        return GraphUtil.getNodesOnFire(graph);
    }

    public synchronized void addNode(Node n) {
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

    public synchronized void Unpause() {
        pause = Boolean.FALSE;
    }

    private synchronized Boolean isExited() {
        return exit;
    }

    private synchronized Boolean isPaused() {
        return pause;
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

    @Override
    public void Update() {
        notifyObserver();
    }

    public synchronized void reset() {
        robots = Collections.synchronizedList(new ArrayList<Robot>());
        graph = new Graph();
        exit = Boolean.FALSE;
        pause = Boolean.FALSE;
        refreshTime = 1000L;
        notifyObserver();
    }
}
