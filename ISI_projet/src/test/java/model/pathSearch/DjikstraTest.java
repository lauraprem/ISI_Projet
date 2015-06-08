package model.pathSearch;

import model.graph.Node;
import model.graph.Edge;
import model.graph.IGraph;
import model.graph.impl.Graph;
import model.graph.Ground;
import model.graph.GroundType;
import model.pathSearch.impl.Djikstra;
import model.graph.NodePath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Laura
 */
public class DjikstraTest {

    private IGraph graph;

    private IShorterPathSearch pathSearch;

    private Node start;

    private Node goal;

    private List<GroundType> capacity;

    private NodePath nodePath;

    @Before
    public void setUp() throws Exception {
        start = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        goal = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();
        Node node8 = new Node();
        Node node9 = new Node();

        Ground g = new Ground(GroundType.FLAT);
        Edge edge12 = new Edge(start, node2, 2.0, g);
        Edge edge23 = new Edge(node2, node3, 1.0, g);
        Edge edge34 = new Edge(node3, goal, 1.0, g);
        Edge edge15 = new Edge(start, node5, 1.0, g);
        Edge edge56 = new Edge(node5, node6, 2.0, g);
        Edge edge67 = new Edge(node6, node7, 3.0, g);
        Edge edge74 = new Edge(node7, goal, 2.0, g);
        Edge edge68 = new Edge(node6, node8, 1.0, g);
        Edge edge28 = new Edge(node2, node8, 2.0, g);
        Edge edge89 = new Edge(node8, node9, 1.0, g);
        Edge edge94 = new Edge(node9, goal, 2.0, g);

        graph = new Graph(edge12, edge23, edge34, edge15, edge56, edge67, edge74, edge68, edge28, edge89, edge94);

        capacity = new ArrayList<GroundType>();
        capacity.add(GroundType.FLAT);

        nodePath = new NodePath();

        pathSearch = new Djikstra();

    }


    @Test
    public void testDjikstra() {
        Double got = pathSearch.findShorterPath(graph, start, goal, capacity, nodePath);
        Double expected = 4.0;
        assertEquals(String.format("Path cost is %s should be %s", got, expected), expected, got);
    }

}
