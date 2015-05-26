package model.graph.edge;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.graph.Node;
import model.graph.ground.Ground;
<<<<<<< HEAD
import model.graph.label.Label;
=======
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe representant une arrete orientee et valuee
 */
@XmlRootElement
public class Edge {
    private static final Logger logger = LogManager.getLogger();
    private static Long maxId = 0L;

    private Long id = 0L;

    /**
     * Longueur de l'arrete
     */
    private Double length;
    /**
     * Noeud source de l'arrete
     */
    private Node source;
    /**
     * Noeud destination de l'arrete
     */
    private Node destination;

    /**
     * Terrain associé à l'arrête
     */
    private Ground ground;

    /**
     * construit une arrete valuee
     *
     * @param _v1        noeud source
     * @param _v2        noeud destination
     * @param _valuation valeur de l'arrete
     * @param ground terrain de l'arrête
     */
    public Edge(Node _v1, Node _v2, Double _valuation, Ground ground) {
        _v1.setLinked(Boolean.TRUE);
        _v2.setLinked(Boolean.TRUE);
        this.length = _valuation;
        this.source = _v1;
        this.destination = _v2;
        this.ground = ground;
        this.setId(getMaxId());
        incrementMaxId();
    }

    /**
     * construit une arrete valuee
     *
     * @param _v1 noeud source
     * @param _v2 noeud destination
     */
    public Edge(Node _v1, Node _v2) {
        this(_v1, _v2, null, null);
    }

    private synchronized static Long getMaxId() {
        return maxId;
    }

    private synchronized static void setMaxId(Long maxId) {
        Edge.maxId = maxId;
    }

    private void incrementMaxId() {
        setMaxId(getMaxId() + 1);
    }


    /**
     * @return la valeur de l'arrete
     */
    public Double getLength() {
        return length;
    }

    /**
     * @param length
     */
    @XmlElement
    public void setLength(Double length) {
        this.length = length;
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
    @XmlElement
    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Boolean updateGround() {
        if (ground.updateType()) {
            logger.info(String.format("L'arrête %s est maintenant inondée.", id));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }


    public Edge opposite() {
        Edge opposite = this.clone();
        opposite.setDestination(getSource());
        opposite.setSource(getDestination());
        return opposite;
    }

    @XmlAttribute
	private void setId(Long id) {
		this.id = id;
	}
    @Override
    public String toString() {
        return source.getLabel().toString() + " ==> " + destination.getLabel() + "(\"" + length + "\" " + ground.toString() + ")";
    }

    @Override
    public Edge clone() {
        return new Edge(source.clone(), destination.clone(), length.doubleValue(), ground.clone());
    }
}