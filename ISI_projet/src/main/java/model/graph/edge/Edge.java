package model.graph.edge;

import model.graph.Node;
import model.graph.ground.Ground;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe representant une arrete orientee et valuee
 */
public class Edge {
    private static final Logger logger = LogManager.getLogger();

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
     * @param ground     terrain de l'arrête
     */
    public Edge(Node _v1, Node _v2, Double _valuation, Ground ground) {
        _v1.setLinked(Boolean.TRUE);
        _v2.setLinked(Boolean.TRUE);
        this.length = _valuation;
        this.source = _v1;
        this.destination = _v2;
        this.ground = ground;
    }

    /**
     * construit une arrete valuee
     */
    public Edge() {
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

    /**
     * @return la valeur de l'arrete
     */
    public Double getLength() {
        return length;
    }

    /**
     * @param length
     */
    public void setLength(Double length) {
        this.length = length;
    }

    private void setLengthString(String length) {
        this.setLength(Double.parseDouble(length));
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

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Boolean updateGround() {
        if (ground.updateType()) {
            logger.info(String.format("An edge now flooded."));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Renvoi l'arrete inversée de l'arrete courante : la source devient destination et vice versa
     *
     * @return une arrete inversée
     */
    public Edge opposite() {
        Edge opposite = this.clone();
        opposite.setDestination(getSource());
        opposite.setSource(getDestination());
        return opposite;
    }

    public String toString2() {
        return source.getLabel().toString() + " ==> " + destination.getLabel() + "(\"" + length + "\" " + ground.toString() + ")";
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Edge{");
        sb.append("source=").append(source);
        sb.append(", destination=").append(destination);
        sb.append(", length=").append(length);
        sb.append(", ground=").append(ground);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Edge clone() {
        return new Edge(source.clone(), destination.clone(), length.doubleValue(), ground.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (length != null ? !length.equals(edge.length) : edge.length != null) return false;
        if (source != null ? !source.equals(edge.source) : edge.source != null) return false;
        if (destination != null ? !destination.equals(edge.destination) : edge.destination != null) return false;
        return !(ground != null ? !ground.equals(edge.ground) : edge.ground != null);

    }

    @Override
    public int hashCode() {
        int result = length != null ? length.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (ground != null ? ground.hashCode() : 0);
        return result;
    }
}
