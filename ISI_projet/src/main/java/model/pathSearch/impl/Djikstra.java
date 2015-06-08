package model.pathSearch.impl;

import model.graph.Node;
import util.GraphUtil;
import model.graph.IGraph;
import model.graph.GroundType;
import model.pathSearch.IShorterPathSearch;
import model.robot.NodePath;

import java.util.ArrayList;
import java.util.List;

/**
 * Trouver le plus court chemin en utilisant l'algorithme du Djikstra
 *
 * @author Laura
 */
public class Djikstra implements IShorterPathSearch {


    @Override
    public Double findShorterPath(IGraph _graph, Node _start, Node goal, List<GroundType> capacity, NodePath _path) {
        ArrayList<Node> closedNodes;
        ArrayList<ExploredNode> path;

        closedNodes = new ArrayList<>();
        path = new ArrayList<>();
        IGraph graph = GraphUtil.getFilteredGraph(_graph, capacity);
        if (!graph.getAllNodes().contains(goal)) {
            return -1.0;
        }
        closedNodes.add(_start.clone());
        path.add(new ExploredNode(_start, null, 0.0));

        Node currentNode = _start.clone();
        // tant qu'on n'a pas trouvé la fin
        while (!currentNode.equals(goal)) {
            ExploredNode betterNode = null;
            // pour chaque noeud qu'on a déjà visité
            for (ExploredNode exploredNode : path) {
                // pour chacun de ses voisins
                for (Node node : graph.getAdjNodes(exploredNode.getCurrentNode())) {
                    // s'il n'est pas déjà visité et si son cout est inferieur
                    // au précédent toruvé
                    if (!closedNodes.contains(node) && betterNode == null) {
                        betterNode = new ExploredNode(node, exploredNode.getCurrentNode(), graph.getEdgeFromNodes(node, exploredNode.getCurrentNode()).getLength()
                                + exploredNode.getPathCost());
                    } else if (!closedNodes.contains(node)
                            && graph.getEdgeFromNodes(node, exploredNode.getCurrentNode()).getLength() + exploredNode.getPathCost() < betterNode.getPathCost()) {
                        // on conserve ce noeud comme étant le prochain
                        betterNode = new ExploredNode(node, exploredNode.getCurrentNode(), graph.getEdgeFromNodes(node, exploredNode.getCurrentNode()).getLength()
                                + exploredNode.getPathCost());

                    }
                }
            }
            if (betterNode == null) {
                return -1.0;
            }
            int size = path.size();
            for (int i = 0; i < size; i++) {
                if (path.get(i).getCurrentNode().equals(betterNode.getCurrentNode())) {
                    path.remove(i);
                    break;
                }
            }
            path.add(betterNode);
            currentNode = betterNode.getCurrentNode();
            closedNodes.add(betterNode.getCurrentNode());

        }

        Node node = goal;
        _path.addFirst(node);
        while (!node.equals(_start)) {
            for (ExploredNode exploredNode : path) {
                if (exploredNode.getCurrentNode().equals(node)) {
                    node = exploredNode.getPreviousNode();
                    _path.addFirst(node);
                    break;
                }

            }
        }

        return path.get(path.size() - 1).getPathCost();
    }

}
