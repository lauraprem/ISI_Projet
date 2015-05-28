package model.robot;

import model.graph.graph.GraphUtilTest;
import model.robot.specialized.RobotChenille;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import util.Util;

import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         26/05/2015
 */
public class RobotTest extends GraphUtilTest {
    private Robot robot = new RobotChenille(graph, startNode, null);

    @Before
    public void setUp() throws Exception {
        graph.addNode(unlinkedNode);
    }

    @Test
    public void testStopFire() throws Exception {
        robot = new RobotChenille(graph, onFire, null);
        robot.stopFire();
        assertFalse(onFire.isOnFire());
    }

    // TODO : Quand le pathfinder sera prêt : enlever l'annotation
    @Ignore
    @Test
    public void testProposePossibleNode() throws Exception {
        Float distance = robot.proposeNode(onFire);
        assertTrue(distance >= 0.0);
    }

    @Test
    public void testProposeImpossibleNode() throws Exception {
        Float distance = robot.proposeNode(unlinkedNode);
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
        robot = new RobotChenille(graph, onFire, null);
        robot.update();
        assertFalse(onFire.isOnFire());
    }
}