package model.graph.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IGraph;
import model.graph.ground.Ground;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class Graph implements IGraph {

    private ArrayList<Edge> edges = new ArrayList<Edge>();

    private ArrayList<Node> nodes = new ArrayList<Node>();

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        addEdge(edge.getSource(), edge.getDestination(), edge.getLength(), edge.getGround());
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2) {
        addEdge(_node1, _node2, 0.0, null);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2, Double length, Ground ground) {
        _node1.setLinked(true);
        _node2.setLinked(true);
        addNode(_node1);
        addNode(_node2);
        edges.add(new Edge(_node1, _node2, length, ground));
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
        if(!nodes.contains(_node)) nodes.add(_node);
    }

    /**
     * @return tous les noeuds du graph
     */
    public List<Node> getAllNodes() {
        return nodes;
    }

    /**
     * @return toutes les arrêtes du graph
     */
    @Override
    public List<Edge> getAllEdges() {
        return edges;
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
        ArrayList<Node> adjNodes = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getDestination().equals(_n)) {
                adjNodes.add(edge.getSource());
            }
            if (edge.getSource().equals(_n)) {
                adjNodes.add(edge.getDestination());
            }
        }
        return adjNodes;
    }


    /**
     * renvoie tous les noeuds du graph qui sont voisins de _n
     *
     * @param _n
     */
    public List<Edge> getAdjEdges(Node _n) {
        return edges.stream().filter(edge -> edge.getSource().equals(_n) || edge.getDestination().equals(_n)).collect(Collectors.toList());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        if (!edges.equals(graph.edges)) return false;
        return nodes.equals(graph.nodes);

    }

    @Override
    public int hashCode() {
        int result = edges.hashCode();
        result = 31 * result + nodes.hashCode();
        return result;
    }
}
