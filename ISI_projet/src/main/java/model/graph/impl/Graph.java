package model.graph.impl;

import model.graph.*;
import util.GraphUtil;
import util.struct.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class Graph implements IGraph {
    private final Random random = new Random();

    /**
     * Liste des arretes composant le graphe
     */
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    /**
     * Liste des noeuds composants le graphe
     */
    private ArrayList<Node> nodes = new ArrayList<Node>();

    public Graph() {
        Node.resetIds();
    }

    public Graph(Edge... edges) {
        this();
        for (int i = 0; i < edges.length; i++) addEdge(edges[i]);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        addEdge(edge, null);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge, Observer o) {
        addEdge(edge.getSource(), edge.getDestination(), edge.getLength(), edge.getGround(), o);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2, Double length, Ground ground, Observer o) {
        _node1.setLinked(true);
        _node2.setLinked(true);
        addNode(_node1, o);
        addNode(_node2, o);
        if (hasEdge(_node1, _node2)) getEdgeFromNodes(_node1, _node2).setGround(ground);
        else edges.add(new Edge(_node1, _node2, length, ground));
    }

    /**
     * @param node1
     * @param node2
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    public boolean hasEdge(Node node1, Node node2) {
        for (Edge edge : edges) {
            if ((edge.getDestination().equals(node1) && edge.getSource().equals(node2)) || (edge.getDestination().equals(node2) && edge.getSource().equals(node1))) {
                return true;
            }
        }
        return false;
    }

    public Edge getEdgeFromNodes(Node node1, Node node2) {
        for (Edge edge : edges) {
            if ((edge.getDestination().equals(node1) && edge.getSource().equals(node2)) || (edge.getDestination().equals(node2) && edge.getSource().equals(node1))) {
                return edge;
            }
        }
        return null;
    }


    /**
     * ajoute un noeud au graph
     *
     * @param node
     * @return retourne vrai si le noeud a été ajouté
     */
    public Boolean addNode(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * ajoute un noeud au graph
     *
     * @param node
     */
    public Boolean addNode(Node node, Observer o) {
        if (addNode(node)) {
            if (o != null) node.addObserver(o);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
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
     * renvoie tous les noeuds du graph qui sont voisins de _n
     *
     * @param n
     */
    public List<Node> getAdjNodes(Node n) {
        ArrayList<Node> adjNodes = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getDestination().equals(n)) {
                adjNodes.add(edge.getSource());
            }
            if (edge.getSource().equals(n)) {
                adjNodes.add(edge.getDestination());
            }
        }
        return adjNodes;
    }


    /**
     * renvoie tous les noeuds du graph qui sont voisins de _n
     *
     * @param n
     */
    public List<Edge> getAdjEdges(Node n) {
        return edges.stream().filter(edge -> edge.getSource().equals(n) || edge.getDestination().equals(n)).collect(Collectors.toList());
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(String.format("%s%n", getClass().getName()));
        List<Node> nodeList;
        Node node;
        for (Node n : getAllNodes()) {
            str.append(String.format("[noeud=%s : [", n.getId().toString()));
            nodeList = getAdjNodes(n);
            for (int i = 0; i < nodeList.size(); i++) {
                node = nodeList.get(i);
                if (i != 0)
                    str.append(", ");
                str.append(String.format("%s <=> %s", n.getId().toString(), node.getId().toString()));
            }
            str.append("]\n");
        }
        return str.toString();
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

    @Override
    public void update() {
        // Propagation  du feu
        GraphUtil.getNodesOnFire(this)
                .forEach(onFireNode ->
                        getAdjEdges(onFireNode).stream()
                                .filter(edge -> !GroundType.FLOODED.equals(edge.getGround().getType())).forEach(
                                edge1 -> {
                                    if (!edge1.getSource().getId().equals(onFireNode.getId()) && random.nextDouble() <= Node.fireProbability)
                                        edge1.getSource().increaseFireLevel(10);
                                    else if (!edge1.getDestination().getId().equals(onFireNode.getId()) && random.nextDouble() <= Node.fireProbability)
                                        edge1.getDestination().increaseFireLevel(10);
                                }
                        ));
    }
}
