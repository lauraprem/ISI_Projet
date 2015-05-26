package model.graph.graph.impl;

import java.util.ArrayList;
import java.util.List;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IUndirectedGraph;
import model.graph.ground.Ground;
import model.graph.label.Label;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class UndirectedGraph implements IUndirectedGraph {

    private ArrayList<Edge> edges = new ArrayList<Edge>();

    private ArrayList<Node> nodes = new ArrayList<Node>();

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2) {
        addEdge(_node1, _node2, null, null);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2, Label valuation, Ground ground) {
        _node1.setLinked(true);
        _node2.setLinked(true);
        List<Node> nodes = getAllNodes();
        if (!nodes.contains(_node1)) addNode(_node1);
        if (!nodes.contains(_node2)) addNode(_node2);
        edges.add(new Edge(_node1, _node2, valuation, ground));
    }

    /**
     * @param _node1
     * @param _node2
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    public boolean hasEdge(Node _node1, Node _node2) {
        for (Edge edge : edges) {
            if ((edge.getDestination().equals(_node1) && edge.getSource().equals(_node2)) || (edge.getDestination().equals(_node2) && edge.getSource().equals(_node1))) {
                return true;
            }
        }
        return false;
    }


    /**
     * ajoute un noeud au graph
     *
     * @param _node
     */
    public void addNode(Node _node) {
        nodes.add(_node);
    }

    /**
     * @return tous les noeuds du graph
     */
    public List<Node> getAllNodes() {
        return nodes;
    }

    /**
     * @return le nombre de noeuds du graph
     */
    public int getNbNodes() {
        return nodes.size();
    }

    /**
     * renvoie tous les noeuds du graph qui sont voisins de _n
     *
     * @param _n
     */
    public List<Node> getAdjNodes(Node _n) {
        ArrayList<Node> adjEdge = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getDestination().equals(_n)) {
                adjEdge.add(edge.getSource());
            }
            if (edge.getSource().equals(_n)) {
                adjEdge.add(edge.getDestination());
            }
        }
        return adjEdge;
    }


    @Override
    public String toString() {
        String str = String.format("%s\n", getClass().getName());
        List<Node> nodeList;
        Node node;
        for (Node n : getAllNodes()) {
            str += String.format("[noeud=%s : [", n.getLabel());
            nodeList = getAdjNodes(n);
            for (int i = 0; i < nodeList.size(); i++) {
                node = nodeList.get(i);
                if (i != 0)
                    str += ", ";
                str += String.format("%s <=> %s", n.getLabel(), node.getLabel());
            }
            str += "]\n";
        }
        return str;
    }
}
