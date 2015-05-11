package model.manager;

import model.graph.Node;
import model.graph.graph.IUndirectedGraph;
import model.graph.graph.UndirectGraphUtil;
import model.robot.Robot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class Manager extends Thread {
    private List<Robot> robots = Collections.synchronizedList(new ArrayList<Robot>());
    private IUndirectedGraph graph;
    private Boolean exit = Boolean.FALSE;
    private Long refreshTime = 1000L;

    public Manager(IUndirectedGraph graph, List<Robot> robots) {
        this.graph = graph;
        this.robots = robots;
    }

    public synchronized IUndirectedGraph getGraph() {
        return graph;
    }

    public synchronized void setGraph(IUndirectedGraph graph) {
        this.graph = graph;
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
            askDistanceToRobots(UndirectGraphUtil.getNodesOnFire(graph),
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
        return UndirectGraphUtil.getNodesOnFire(graph);
    }


}
