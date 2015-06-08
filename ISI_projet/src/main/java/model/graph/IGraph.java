package model.graph;

import model.graph.Node;
import model.graph.Edge;
import model.graph.Ground;
import util.struct.Observer;
import util.struct.Updatable;

import java.util.List;

/**
 * @author Laura
 */
public interface IGraph extends Updatable {


    /**
     * ajoute un noeud au graph
     */
    Boolean addNode(Node node);

    /**
     * ajoute un noeud au graph
     */
    Boolean addNode(Node node, Observer o);

    /**
     * @return tous les noeuds du graph
     */
    List<Node> getAllNodes();


    /**
     * @return toutes les arrêtes du graph
     */
    List<Edge> getAllEdges();

    /**
     * renvoie tous les noeuds du graph qui sont destination d'un arc dont la source est _n
     */
    List<Node> getAdjNodes(Node n);

    /**
     * renvoie toutes les arrêtes du graph qui sont adjacents à _n
     */
    List<Edge> getAdjEdges(Node n);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    void addEdge(Edge edge);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    void addEdge(Edge edge, Observer o);

    void addEdge(Node _node1, Node _node2, Double length, Ground ground, Observer o);
    /**
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    boolean hasEdge(Node node1, Node node2);

    /**
     * Permet de connaitre l'arrete qui relie deux noeuds
     *
     * @param node1
     * @param node2
     * @return Edge reliant _node1 et _node2
     */
    Edge getEdgeFromNodes(Node node1, Node node2);


    boolean equals(Object o);

}
