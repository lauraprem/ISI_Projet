package model.graph.graph;

import model.graph.Node;
import model.graph.graph.impl.UndirectedGraph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import model.graph.label.impl.IntegerLabel;
import model.graph.label.impl.StringLabel;
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
    private Node unlinkedNode = new Node(new StringLabel("unlinkedNode"), new Point(-1, -5));

    @Before
    public void setUp() throws Exception {
        graph = new UndirectedGraph();
        onFire = new Node(new StringLabel("test1"), new Point(1, 2));
        onFire.increaseFireLevel(20);
        graph.addEdge(onFire,
                new Node(new StringLabel("test2"), new Point(-1, -2)),
                new IntegerLabel(3),
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
}