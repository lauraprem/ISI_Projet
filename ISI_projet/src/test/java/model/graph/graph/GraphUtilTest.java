package model.graph.graph;

import model.graph.*;
import model.graph.impl.Graph;
import org.junit.Before;
import org.junit.Test;
import util.GraphUtil;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class GraphUtilTest {
    protected Node onFire = new Node(new Point(1, 2), 20);
    protected Node unlinkedNode = new Node(new Point(-1, -5));
    protected Node startNode = new Node(new Point(-1, -2));
    protected IGraph graph = new Graph(new Edge(onFire,
            startNode,
            3.0,
            new Ground(GroundType.FLAT, 1.0)));

    @Before
    public void setUp() throws Exception {
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

    @Test
    public void testGetFilteredGraph() throws Exception {
        ArrayList<GroundType> groundTypeList = new ArrayList<>();
        assertEquals(new Graph(), GraphUtil.getFilteredGraph(graph, groundTypeList));
    }
}