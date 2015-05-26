package model.graph.graph;

import model.graph.Node;
import model.graph.graph.impl.Graph;
import model.graph.ground.GroundType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class GraphUtil {
    public static List<Node> getUnlinkedNodes(IGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isLinked()).collect(Collectors.toList());
    }

    public static List<Node> getNodesOnFire(IGraph graph) {
        return graph.getAllNodes().stream().filter(node -> node.isOnFire()).collect(Collectors.toList());
    }

    public static IGraph getFilteredGraph(IGraph graph, List<GroundType> groundTypes) {
        IGraph newGraph = new Graph();
        if (groundTypes != null || groundTypes.size() != 0)
            graph.getAllEdges().forEach(edge -> {
                if (groundTypes.contains(edge.getGround().getType())) newGraph.addEdge(edge);
            });
        return newGraph;
    }
}
