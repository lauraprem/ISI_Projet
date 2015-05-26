package model.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Observable;
import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.GraphUtil;
import model.graph.graph.IGraph;
import model.graph.graph.impl.Graph;
import model.robot.Robot;
import view.Observer;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class Manager extends Thread implements Observable{
    private List<Robot> robots = Collections.synchronizedList(new ArrayList<Robot>());
    private IGraph graph = new Graph();
    private Boolean exit = Boolean.FALSE;
    private Long refreshTime = 1000L;
    
    protected ArrayList<Observer> observers;

    public Manager(IGraph graph, List<Robot> robots) {
    	observers = new ArrayList<Observer>();
        this.graph = graph;
        this.robots = robots;
    }

    public Manager() {
    	observers = new ArrayList<Observer>();
    }

    public synchronized IGraph getGraph() {
        return graph;
    }

    public synchronized void setGraph(IGraph graph) {
        this.graph = graph;
        this.NotifierObservateur();
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
        Long startLoop, endLoop;
        while(!exit) {
            startLoop = System.currentTimeMillis();
            askDistanceToRobots(GraphUtil.getNodesOnFire(graph),
                    getUnoccupiedRobots());
            endLoop = System.currentTimeMillis();
            if(endLoop - startLoop < refreshTime) try {
                sleep(refreshTime - endLoop + startLoop);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void askDistanceToRobots(List<Node> nodesOnFire, List<Robot> unoccupiedRobots) {
        Map<Node, Robot> commands = Collections.synchronizedMap(new HashMap());
        Map<Robot, Integer> distances = Collections.synchronizedMap(new HashMap());
        for(Node node : nodesOnFire) {
            distances.clear();
            unoccupiedRobots.stream()
                    .filter(robotCommanded -> !commands.containsValue(robotCommanded)).forEach(robot -> {
                Integer distance = robot.proposeNode(node);
                if(distance != -1) distances.put(robot, distance);
            });
            if(distances.size() != 0) commands.put(node,
                    Collections.min(
                            distances.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey());
        }
        commands.forEach((node, robot) -> robot.stopFire(node));
    }


    private List<Robot> getUnoccupiedRobots() {
        return getRobots().stream().filter(robot -> !robot.isBusy()).collect(Collectors.toList());
    }

    private List<Node> getNodesOnFire() {
        return GraphUtil.getNodesOnFire(graph);
    }

    public synchronized void addNode(Node n) {
        graph.addNode(n);
        this.NotifierObservateur();
    }

    public synchronized void addEdge(Edge e) {
        graph.addEdge(e);
        this.NotifierObservateur();
    }

    public synchronized void addRobot(Robot r) {
        robots.add(r);
        this.NotifierObservateur();
    }

    public synchronized void Exit() {
        exit = Boolean.TRUE;
    }

    @Override
	public void AjoutObservateur(Observer o) {
		observers.add(o);
	}

	@Override
	public void SupprimerObservateur(Observer o) {
		observers.remove(o);
	}

	@Override
	public void NotifierObservateur() {
		for (Observer obs : observers) {
			obs.Update();
		}
	}
}
