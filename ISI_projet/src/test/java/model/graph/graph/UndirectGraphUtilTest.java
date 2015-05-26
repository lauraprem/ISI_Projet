package model.graph.graph;

import model.graph.Node;
import model.graph.graph.impl.UndirectedGraph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class UndirectGraphUtilTest {
    private IUndirectedGraph graph;
    private Node onFire;
<<<<<<< HEAD
=======
    private Node unlinkedNode = new Node("unlinkedNode", new Point(-1, -5));
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782

    @Before
    public void setUp() throws Exception {
        graph = new UndirectedGraph();
<<<<<<< HEAD
        onFire = new Node(new StringLabel("test1"), new Point(1, 2));
        onFire.increaseFireLevel(20);
        graph.addEdge(onFire,
                new Node(new StringLabel("test2"), new Point(-1, -2)),
                new IntegerLabel(3),
=======
        onFire = new Node("test1", new Point(1, 2));
        onFire.increaseFireLevel(20);
        graph.addEdge(onFire,
                new Node("test2", new Point(-1, -2)),
                3.0,
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782
                new Ground(GroundType.FLAT, 1.0));
        graph.addNode(unlinkedNode);
    }

    @Test
    public void testGetUnlinkedNodes() throws Exception {
        ArrayList unlinkedNodes = new ArrayList<Node>();
        Node n = unlinkedNode;
        unlinkedNodes.add(n);
        assertEquals(unlinkedNodes, UndirectGraphUtil.getUnlinkedNodes(graph));
    }

    @Test
    public void testGetOnFireNodes() throws Exception {
        ArrayList onFireNodes = new ArrayList<Node>();
        onFireNodes.add(onFire);
        assertEquals(onFireNodes, UndirectGraphUtil.getNodesOnFire(graph));
    }

    @Test
    public void testGetOnFireNodes() throws Exception {
        ArrayList onFireNodes = new ArrayList<Node>();
        onFireNodes.add(onFire);
        assertEquals(UndirectGraphUtil.getUnlinkedNodes(graph), onFireNodes);
    }
}