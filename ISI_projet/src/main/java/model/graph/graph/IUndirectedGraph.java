package model.graph.graph;

import model.graph.IGraph;
import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.label.Label;

public interface IUndirectedGraph extends IGraph {

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
