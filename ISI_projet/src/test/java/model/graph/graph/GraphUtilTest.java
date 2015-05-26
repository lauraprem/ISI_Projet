package model.graph.graph;

import model.graph.Node;
import model.graph.graph.impl.Graph;
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
public class GraphUtilTest {
    private IGraph graph;
    private Node onFire;
    private Node unlinkedNode = new Node("unlinkedNode", new Point(-1, -5));

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        onFire = new Node("test1", new Point(1, 2));
        onFire.increaseFireLevel(20);
        graph.addEdge(onFire,
                new Node("test2", new Point(-1, -2)),
                3.0,
                new Ground(GroundType.FLAT, 1.0));
        graph.addNode(unlinkedNode);
    }

    @Test
    public void testGetUnlinkedNodes() throws Exception {
        ArrayList unlinkedNodes = new ArrayList<Node>();
        Node n = unlinkedNode;
        unlinkedNodes.add(n);
        assertEquals(unlinkedNodes, GraphUtil.getUnlinkedNodes(graph));
    }

    @Test
    public void testGetOnFireNodes() throws Exception {
        ArrayList onFireNodes = new ArrayList<Node>();
        onFireNodes.add(onFire);
        assertEquals(onFireNodes, GraphUtil.getNodesOnFire(graph));
    }
}