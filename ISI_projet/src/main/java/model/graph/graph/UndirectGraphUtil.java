package model.graph.graph;

import model.graph.Node;
import model.graph.graph.impl.UndirectedGraph;
import model.graph.ground.GroundType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class UndirectGraphUtil {
    public static List<Node> getUnlinkedNodes(IUndirectedGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isLinked()).collect(Collectors.toList());
    }

    public static List<Node> getNodesOnFire(IUndirectedGraph graph) {
        return graph.getAllNodes().stream().filter(node -> node.isOnFire()).collect(Collectors.toList());
    }

    public static IUndirectedGraph getFilteredGraph(IUndirectedGraph graph, List<GroundType> groundTypes) {
        IUndirectedGraph newGraph = new UndirectedGraph();
        if (groundTypes != null || groundTypes.size() != 0)
            graph.getAllEdges().forEach(edge -> {
                if (groundTypes.contains(edge.getGround().getType())) newGraph.addEdge(edge);
            });
        return newGraph;
    }
}
