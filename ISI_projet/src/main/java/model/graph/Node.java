package model.graph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * Classe representant un noeud etiquete pour un graph
 */

public class Node extends Point {
    private static final Logger logger = LogManager.getLogger();
    /**
     * etiquette du noeud
     */
    private String label;
    /**
     * ID unique du noeud
     */
    private Long id;
    /**
     * nombre total d'instances de Noeud
     */
    private static Long maxId = 0L;

    /**
     * Niveau de l'incendie
     */
    private Integer fireLevel = 0;

    /**
     * Construit un noeud avec une etiquette
     *
     * @param _label etiquette du noeud
     */
    public Node(String _label) {
        this(_label, new Point(0, 0));
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     *
     * @param _label etiquette du noeud
     */
    public Node(String _label, Point _point) {
        super(_point);
        this.label = _label;
        this.id = getMaxId();
        incrementMaxId();
    }

    private Boolean linked = Boolean.FALSE;

    public Boolean isLinked() {
        return linked;
    }

    public void setLinked(Boolean linked) {
        this.linked = linked;
    }

    private synchronized static Long getMaxId() {
        return maxId;
    }

    private synchronized static void setMaxId(Long maxId) {
        Node.maxId = maxId;
    }

    private void incrementMaxId() {
        setMaxId(getMaxId() + 1);
    }

    /**
     * @return l'unique ID du noeud
     */
    public Long getID() {
        return id;
    }

    /**
     * Specifie l etiquette du noeud
     *
     * @param _label
     */
    public void setLabel(String _label) {
        this.label = _label;
    }

    /**
     * @return l etiquette du noeud
     */
    public String getLabel() {
        return label;
    }


    @Override
    public String toString() {
        return "" + label;
    }

    public Long getId() {
        return id;
    }

    public Integer getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(Integer fireLevel) {
        if (fireLevel < 0) {
            this.fireLevel = 0;
            logger.warn(String.format("The level of the fire can't be negative, has been set to 0."));
        } else
            this.fireLevel = fireLevel;
    }

    public void decreaseFireLevel(Integer diff) {
        if (diff < 0)
            logger.warn(String.format("The level of the fire has been decreased of a negative amount, so it has been increased."));
        setFireLevel(getFireLevel() - diff);
    }

    public void increaseFireLevel(Integer diff) {
        if (diff < 0)
            logger.warn(String.format("The level of the fire has been increased of a negative amount, so it has been decreased."));
        setFireLevel(getFireLevel() + diff);
    }

    public Boolean isOnFire() {
        return fireLevel != 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**
     * Creates a new object of the same class and with the
     * same contents as this object.
     *
     * @return a clone of this instance.
     * @throws OutOfMemoryError if there is not enough memory.
     * @see Cloneable
     * @since 1.2
     */
    @Override
    public Node clone() {
        Node node = new Node(new String(label), (Point) super.clone());
        node.setFireLevel(fireLevel);
        node.setLinked(linked);
        return node;
    }
}


