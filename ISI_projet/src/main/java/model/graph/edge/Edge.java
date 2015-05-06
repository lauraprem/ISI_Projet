package model.graph.edge;

import model.graph.label.Label;
import model.graph.Node;

/**
 * Classe representant une arrete orientee et valuee
 */
public class Edge {
    /**
     * valeur de l'arrete
     */
    private Label valuation;
    /**
     * Noeud source de l'arrete orientee
     */
    private Node source;
    /**
     * Noeud destination de l'arrete orientee
     */
    private Node destination;

    /**
     * construit une arrete orientee et valuee
     *
     * @param _v1        noeud source
     * @param _v2        noeud destination
     * @param _valuation valeur de l'arrete
     */
    public Edge(Node _v1, Node _v2, Label _valuation) {
        this.valuation = _valuation;
        this.source = _v1;
        this.destination = _v2;
    }

	/**
	 * construit une arrete orientee et valuee
	 *
	 * @param _v1        noeud source
	 * @param _v2        noeud destination
	 */
	public Edge(Node _v1, Node _v2) {
		this(_v1, _v2, null);
	}


	/**
     * @return la valeur de l'arrete
     */
    public Label getValuation() {
        return valuation;
    }

    /**
     * @param valuation
     */
    public void setValuation(Label valuation) {
        this.valuation = valuation;
    }

    /**
     * @return le noeud source de l'arrete
     */
    public Node getSource() {
        return source;
    }

    /**
     * @param v1 noeud source
     */
    public void setSource(Node v1) {
        this.source = v1;
    }

    /**
     * @return le noeud destination de l'arrete
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * @param v2 noeud destination
     */
    public void setDestination(Node v2) {
        this.destination = v2;
    }

    @Override
    public String toString() {
        return source.getLabel().toString() + " ==> " + destination.getLabel().getLabel() + "(" + valuation + ")";
    }


}
