package model.graph.graph.impl;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IDirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class DirectedGraph implements IDirectedGraph {
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<Node> nodes = new ArrayList<Node>();


    /**
     * @param _n1
     * @param _n2
     * @return vrai si le graph possede un arc de source _n1 et de destination _n2
     */
    public boolean hasArc(Node _n1, Node _n2) {
        Node source, destination;
        boolean condition;

        if (edges == null || _n1 == null || _n2 == null) return false;

        for (Edge edge : edges) {
            source = edge.getSource();
            destination = edge.getDestination();
            condition = _n1.equals(source) && _n2.equals(destination);
            if (condition) return condition;
        }
        return false;
    }

    /**
     * Ajoute un arc au graph
     *
     * @param _edge
     */
    public void addArc(Edge _edge) {
        if (edges == null && _edge == null) return;

        if (!hasArc(_edge.getSource(), _edge.getDestination())) edges.add(_edge);
    }

    /**
     * @param _n
     * @return tous les arcs du graph ayant pour source le noeud _n
     */
    public List<Edge> getArc(Node _n) {
        Node source;
        boolean condition;
        List<Edge> arcsDestination = new ArrayList<Edge>();

        if (edges == null || _n == null) return arcsDestination;

        for (Edge edge : edges) {
            source = edge.getSource();
            condition = _n.equals(source);
            if (condition) arcsDestination.add(edge);
        }
        return arcsDestination;
    }

    /**
     * ajoute un noeud au graph
     *
     * @param _node
     */
    public void addNode(Node _node) {
        if (_node == null) return;

        for (Node node : nodes) {
            if (_node.equals(node)) return;
        }

        nodes.add(_node);
    }

    /**
     * @return tous les noeuds du graph
     */
    public Set<Node> getAllNodes() {
        Set<Node> nodeSet = new HashSet<Node>();
        for (Node node : nodes) {
            nodeSet.add(node);
        }
        return nodeSet;
    }

    /**
     * @return le nombre de noeuds du graph
     */
    public int getNbNodes() {
        return nodes.size();
    }

    /**
     * renvoie tous les noeuds du graph qui sont destination d'un arc dont la source est _n
     *
     * @param _n
     */
    public List<Node> getAdjNodes(Node _n) {
        List<Edge> arcsDestination = getArc(_n);
        List<Node> nodeList = new ArrayList<Node>();

        for (Edge edge : arcsDestination) {
            nodeList.add(edge.getDestination());
        }

        return nodeList;
    }

    @Override
    public String toString() {
        String str = String.format("%s\n", getClass().getName());
        List<Edge> edgeList;
        Edge edge;
        for (Node n : getAllNodes()) {
            str += String.format("[noeud=%s : [", n.getLabel());
            edgeList = getArc(n);
            for (int i = 0; i < edgeList.size(); i++) {
                edge = edgeList.get(i);
                if (i != 0) str += ", ";
                str += String.format("%s => %s(%s)", n.getLabel(), edge.getDestination().getLabel(), edge.getValuation());
            }
            str += "]\n";
        }
        return str;
    }
}
