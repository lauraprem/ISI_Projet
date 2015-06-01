package model.graph.graph.impl;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IGraph;
import model.graph.ground.Ground;
import model.pathSearch.IShorterPathSearch;
import model.robot.Capacity;
import model.robot.NodePath;
import view.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class Graph implements IGraph {

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
    public void addEdge(Node _node1, Node _node2) {
        addEdge(_node1, _node2, null);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     * @param o
     */
    @Override
    public void addEdge(Node _node1, Node _node2, Observer o) {
        addEdge(_node1, _node2, 0.0, null, o);
    }

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param _node1
     * @param _node2
     */
    public void addEdge(Node _node1, Node _node2, Double length, Ground ground) {
        addEdge(_node1, _node2, length, ground, null);
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

    public Edge getEdgeFromNodes(Node _node1, Node _node2) {
        for (Edge edge : edges) {
            if ((edge.getDestination().equals(_node1) && edge.getSource().equals(_node2)) || (edge.getDestination().equals(_node2) && edge.getSource().equals(_node1))) {
                return edge;
            }
        }
        return null;
    }


    /**
     * ajoute un noeud au graph
     *
     * @param _node
     * @return retourne vrai si le noeud a été ajouté
     */
    public Boolean addNode(Node _node) {
        if (!nodes.contains(_node)) {
            nodes.add(_node);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * ajoute un noeud au graph
     *
     * @param _node
     */
    public Boolean addNode(Node _node, Observer o) {
        if (addNode(_node)) {
            if (o != null) _node.addObserver(o);
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
        StringBuilder str = new StringBuilder(String.format("%s%n", getClass().getName()));
        List<Node> nodeList;
        Node node;
        for (Node n : getAllNodes()) {
            str.append(String.format("[noeud=%s : [", n.getLabel()));
            nodeList = getAdjNodes(n);
            for (int i = 0; i < nodeList.size(); i++) {
                node = nodeList.get(i);
                if (i != 0)
                    str.append(", ");
                str.append(String.format("%s <=> %s", n.getLabel(), node.getLabel()));
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

    /**
     * Permet de savoir si le graphe est connexe en utlisant un path finder pour vérifier que l'on peut accéder à tous les noeuds depuis n'importe quel noeud
     *
     * @param pathSearch méthode de parcours du graphe
     * @return true si la graphe est connexe false sinon
     */
    public Boolean isValid(IShorterPathSearch pathSearch) {
        if (nodes.size() == 0) return Boolean.FALSE;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                if (pathSearch.findShorterPath(
                        this, nodes.get(i), nodes.get(j), Capacity.allCapacity(), new NodePath())
                        < 0.0) return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
