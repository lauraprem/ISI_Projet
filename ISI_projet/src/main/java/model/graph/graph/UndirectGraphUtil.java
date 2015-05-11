package model.graph.graph;

import java.util.List;
import java.util.stream.Collectors;

import model.graph.Node;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class UndirectGraphUtil {
    public static List<Node> getUnlinkedNodes(IUndirectedGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isLinked()).collect(Collectors.toList());
    }

    public static List<Node> getNodesOnFire(IUndirectedGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isOnFire()).collect(Collectors.toList());
    }
}
