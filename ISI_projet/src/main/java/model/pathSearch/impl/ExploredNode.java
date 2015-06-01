package model.pathSearch.impl;

import model.graph.Node;

/**
 * Noeud explorés par l'algorithme du Djikstra
 *
 * @author Laura
 */
public class ExploredNode {

    /**
     * Le noeud actuellement exploré
     */
    private Node currentNode;

    /**
     * Le noeud duquel on est venu pour atteindre le currentNode
     */
    private Node previousNode;

    /**
     * Le cout depuis le noeud d'origine pour atteindre le currentNode
     */
    private Double pathCost;

    public ExploredNode(Node _currentNode, Node _previousNode, Double _pathCost) {
        currentNode = _currentNode;
        previousNode = _previousNode;
        pathCost = _pathCost;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(Double pathCost) {
        this.pathCost = pathCost;
    }

}
