package model.robot;

import model.graph.graph.GraphUtilTest;
import model.manager.Manager;
import model.pathSearch.impl.Djikstra;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;
import org.junit.Before;
import org.junit.Test;
import util.Util;

import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class RobotTest extends GraphUtilTest {
    private Robot robot = new RobotChenille(graph, startNode, new Djikstra(), new Manager());

    @Before
    public void setUp() throws Exception {
        graph.addNode(unlinkedNode);
    }

    @Test
    public void testStopFire() throws Exception {
        robot = new RobotAPates(graph, onFire, new Djikstra(), new Manager());
        robot.stopFire();
        assertFalse(onFire.isOnFire());
    }

    @Test
    public void testProposePossibleNode() throws Exception {
        Double distance = robot.proposeNode(onFire);
        assertTrue(distance >= 0.0);
    }

    @Test
    public void testProposeImpossibleNode() throws Exception {
        Double distance = robot.proposeNode(unlinkedNode);
        assertTrue(distance < 0.0);
    }

    @Test
    public void testUpdatePosition() throws Exception {
        NodePath nodePath = new NodePath();
        nodePath.addLast(onFire);
        Util.invokeMethod(robot, "setPath", nodePath);
        robot.acceptPath();
        robot.update();
        assertEquals(onFire, robot.getCurrentNode());
    }

    @Test
    public void testUpdateStopFire() throws Exception {
        robot = new RobotChenille(graph, onFire, new Djikstra(), new Manager());
        robot.update();
        assertFalse(onFire.isOnFire());
    }
}