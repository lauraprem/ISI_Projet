package model.graph.graph;

import java.util.List;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.label.Label;

public interface IUndirectedGraph {

	
    /**
     * ajoute un noeud au graph
     */
    public void addNode(Node _node);

    /**
     * @return tous les noeuds du graph
     */
    public List<Node> getAllNodes();

    /**
     * @return le nombre de noeuds du graph
     */
    public int getNbNodes();

    /**
     * renvoie tous les noeuds du graph qui sont destination d'un arc dont la source est _n
     */
    public List<Node> getAdjNodes(Node _n);

	
    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    public void addEdge(Edge edge);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    public void addEdge(Node _node1, Node _node2);

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    public void addEdge(Node _node1, Node _node2, Label valuation, Ground ground);

    /**
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    public boolean hasEdge(Node _node1, Node _node2);


}
