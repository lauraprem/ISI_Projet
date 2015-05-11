package model.graph;

import model.graph.label.Label;
import model.graph.label.impl.IntegerLabel;

import java.awt.*;

/**
 * Classe representant un noeud etiquete pour un graph
 */

public class Node extends Point {
    /**
     * etiquette du noeud
     */
    private Label label;
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
    public Node(Label _label) {
        this(_label, new Point(0, 0));
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     *
     * @param _label etiquette du noeud
     */
    public Node(Label _label, Point _point) {
        super(_point);
        this.label = _label;
        this.id = getMaxId();
        incrementMaxId();
    }


    private synchronized static Long getMaxId() {
        return maxId;
    }

    private synchronized static void setMaxId(Long maxId) {
        Node.maxId = maxId;
    }

    private void incrementMaxId() {
        setMaxId(getMaxId()+1);
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
    public void setLabel(Label _label) {
        this.label = _label;
    }

    /**
     * @return l etiquette du noeud
     */
    public Label getLabel() {
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
        this.fireLevel = fireLevel;
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


}


