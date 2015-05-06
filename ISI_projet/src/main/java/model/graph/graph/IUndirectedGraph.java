package model.graph.graph;

import model.graph.IGraph;
import model.graph.Node;

public interface IUndirectedGraph extends IGraph {

    /**
     * Ajoute une arrete au graph entre deux noeuds
     */
    public void addEdge(Node _node1, Node _node2);

    /**
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    public boolean hasEdge(Node _node1, Node _node2);


}
