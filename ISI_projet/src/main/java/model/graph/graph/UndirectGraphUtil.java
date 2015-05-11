package model.graph.graph;

import model.graph.IGraph;
import model.graph.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class UndirectGraphUtil {
    public static List<Node> getUnlinkedNodes(IGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isLinked()).collect(Collectors.toList());
    }
}
