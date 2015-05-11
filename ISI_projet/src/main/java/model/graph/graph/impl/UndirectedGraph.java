package model.graph.graph.impl;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IDirectedGraph;
import model.graph.graph.IUndirectedGraph;
import model.graph.ground.Ground;
import model.graph.label.Label;

import java.util.List;
import java.util.Set;

/**
 * Created by alexandreg on 11/03/2015.
 */
public class UndirectedGraph implements IUndirectedGraph {
    private IDirectedGraph directedGraph = new DirectedGraph();

    /**
     * Ajoute une arrete au graph entre deux noeuds
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        directedGraph.addArc(edge);
        directedGraph.addArc(edge.opposite());
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
        directedGraph.addArc(new Edge(_node1, _node2, valuation, ground));
        directedGraph.addArc(new Edge(_node2, _node1, valuation, ground));

    }

    /**
     * @param _node1
     * @param _node2
     * @return vrai si le graph possede une arrete entre les noeuds _n1 et _n2
     */
    public boolean hasEdge(Node _node1, Node _node2) {
        return directedGraph.hasArc(_node1, _node2) || directedGraph.hasArc(_node2, _node1);
    }

    /**
     * ajoute un noeud au graph
     *
     * @param _node
     */
    public void addNode(Node _node) {
        directedGraph.addNode(_node);
    }

    /**
     * @return tous les noeuds du graph
     */
    public Set<Node> getAllNodes() {
        return directedGraph.getAllNodes();
    }

    /**
     * @return le nombre de noeuds du graph
     */
    public int getNbNodes() {
        return directedGraph.getNbNodes();
    }

    /**
     * renvoie tous les noeuds du graph qui sont destination d'un arc dont la source est _n
     *
     * @param _n
     */
    public List<Node> getAdjNodes(Node _n) {
        return directedGraph.getAdjNodes(_n);
    }

    public List<Edge> getArc(Node _n) {
		return directedGraph.getArc(_n);
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
                if (i != 0) str += ", ";
                str += String.format("%s <=> %s", n.getLabel(), node.getLabel());
            }
            str += "]\n";
        }
        return str;
    }
}
