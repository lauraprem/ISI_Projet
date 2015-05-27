package model.graph.graph;

import java.util.List;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;

public interface IGraph {

	
    /**
     * ajoute un noeud au graph
     */
    void addNode(Node _node);

    /**
     * @return tous les noeuds du graph
     */
    List<Node> getAllNodes();


    /**
     * @return toutes les arrêtes du graph
     */
    List<Edge> getAllEdges();

    /**
     * @return le nombre de noeuds du graph
     */
    int getNbNodes();

    /**
     * renvoie tous les noeuds du graph qui sont destination d'un arc dont la source est _n
     */
    List<Node> getAdjNodes(Node _n);

    /**
     * renvoie toutes les arrêtes du graph qui sont adjacents à _n
     */
    List<Edge> getAdjEdges(Node _n);
	
    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    void addEdge(Edge edge);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    void addEdge(Node _node1, Node _node2);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    void addEdge(Node _node1, Node _node2, Double length, Ground ground);

    /**
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    boolean hasEdge(Node _node1, Node _node2);
    
    public Edge getEdgeFromNodes(Node _node1, Node _node2);

    boolean equals(Object o);

}
