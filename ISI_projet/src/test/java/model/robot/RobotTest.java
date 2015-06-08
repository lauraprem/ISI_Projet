package model.robot;

import model.graph.Node;
import model.graph.Point;
import model.graph.PointUtil;
import model.graph.graph.GraphUtilTest;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
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
        robot.update();
        assertEquals(onFire, robot.getCurrentNode());
    }

    @Test
    public void testUpdatePositionChangeEdge() throws Exception {
        NodePath nodePath = new NodePath();
        nodePath.addLast(onFire);
        Util.invokeMethod(robot, "setPath", nodePath);
        robot.acceptPath();
        robot.update();
        graph.getEdgeFromNodes(robot.getCurrentNode(), robot.getNextNode()).setGround(new Ground(GroundType.STEEP));
        robot.update();
        assertNotEquals(onFire, robot.getCurrentNode());
    }

    @Test
    public void testUpdatePositionChangeEdgeTwice() throws Exception {
        NodePath nodePath = new NodePath();
        nodePath.addLast(onFire);
        Util.invokeMethod(robot, "setPath", nodePath);
        Node sourceNode = robot.getCurrentNode();
        robot.acceptPath();
        robot.update();
        graph.getEdgeFromNodes(robot.getCurrentNode(), robot.getNextNode()).setGround(new Ground(GroundType.STEEP));
        robot.update();
        graph.getEdgeFromNodes(robot.getCurrentNode(), onFire).setGround(new Ground(GroundType.FLAT));
        robot.update();
        assertEquals(sourceNode, robot.getCurrentNode());
    }

    @Test
    public void testMultiUpdatePositionTrue() throws Exception {
        NodePath nodePath = new NodePath();
        onFire.setX(robot.getPosition().getX() + Robot.getVitesse()*1.5);
        onFire.setY(robot.getPosition().getY());
        nodePath.addLast(onFire);
        Util.invokeMethod(robot, "setPath", nodePath);
        robot.acceptPath();
        robot.update();
        robot.update();
        assertEquals(onFire, robot.getCurrentNode());
        assertEquals(new Point(robot.getCurrentNode()), robot.getPosition());
    }

    @Test
    public void testMultiUpdatePositionFalse() throws Exception {
        NodePath nodePath = new NodePath();
        onFire.setX(robot.getPosition().getX() + Robot.getVitesse()*1.5);
        onFire.setY(robot.getPosition().getY());
        nodePath.addLast(onFire);
        Util.invokeMethod(robot, "setPath", nodePath);
        robot.acceptPath();
        robot.update();
        assertNotEquals(onFire, robot.getCurrentNode());
        assertEquals(Robot.getVitesse(), PointUtil.getDistance(robot.getCurrentNode(), robot.getPosition()));
    }

    @Test
    public void testUpdateStopFire() throws Exception {
        robot = new RobotChenille(graph, onFire, new Djikstra(), new Manager());
        robot.update();
        assertFalse(onFire.isOnFire());
    }
}