package model.graph.edge;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.graph.Node;
import model.graph.ground.Ground;
import model.graph.label.Label;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe representant une arrete orientee et valuee
 * @author gael,corinne,alexandre,laura
 *
 */
@XmlRootElement
public class Edge {
    private static final Logger logger = LogManager.getLogger();

    private Long id = 0L;

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
     * Terrain associé à l'arrête
     */
    @XmlElement
    private Ground ground;

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
	 * la valeur de l'id
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 
	 * @param id	id du noeud
	 */
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
    public void setDestination(Node v2) {
        this.destination = v2;
    }

    public Ground getGround() {
        return ground;
    }

    public Boolean updateGround() {
        if(ground.updateType()) {
            logger.info(String.format("L'arrête %s est maintenant inondée.", id));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        return source.getLabel().toString() + " ==> " + destination.getLabel().getLabel() + "(\"" + valuation + "\" " + ground.toString() + ")";
    }


}
