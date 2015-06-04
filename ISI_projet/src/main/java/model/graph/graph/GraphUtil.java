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
    /**
     * permet de connaitre les noeud du graphe qui ne sont pas reliés
     *
     * @param graph dont on veut connaitre les noeuds non liés
     * @return la liste des noeuds non reliés
     */
    public static List<Node> getUnlinkedNodes(IGraph graph) {
        return graph.getAllNodes().stream().filter(node -> !node.isLinked()).collect(Collectors.toList());
    }

    /**
     * permet de connaitre les noeud du graphe qui sont en feu
     *
     * @param graph dont on veut connaitre les noeuds en feu
     * @return la liste des noeuds en feu
     */
    public static List<Node> getNodesOnFire(IGraph graph) {
        return graph.getAllNodes().stream().filter(node1 -> node1 != null).filter(node -> node.isOnFire()).collect(Collectors.toList());
    }

    /**
     * permet de connaitre les noeud du graphe qui sont en feu
     *
     * @param graph dont on veut connaitre les noeuds en feu
     * @return la liste des noeuds en feu
     */
    public static List<Node> getUntakenCareOfNodesOnFire(IGraph graph) {
        return graph.getAllNodes().stream().filter(node1 -> node1 != null).filter(node -> node.isOnFire() && !node.isTakenCareOf()).collect(Collectors.toList());
    }

    /**
     * Permet d'obtenir un graphe dont on supprime les arretes sur lesquelles on ne peut pas passer. Il se peut que certains noeuds ne soient plus présents au terme de l'operation.
     *
     * @param graph       dont on veut supprimer les arretes non parcourable
     * @param groundTypes type d'arete sur lesquelles on est capable de marcher
     * @return un IGraph dépouillé des arretes non utilisables
     */
    public static IGraph getFilteredGraph(IGraph graph, List<GroundType> groundTypes) {
        IGraph newGraph = new Graph();
        if (groundTypes != null || groundTypes.size() != 0)
            graph.getAllEdges().forEach(edge -> {
                if (groundTypes.contains(edge.getGround().getType())) newGraph.addEdge(edge);
            });
        return newGraph;
    }
}
