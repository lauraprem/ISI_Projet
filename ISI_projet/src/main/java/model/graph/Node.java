package model.graph;

import java.awt.Point;
import java.util.ArrayList;

import model.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Observer;

/**
 * Classe representant un noeud etiquete pour un graph
 */

public class Node extends Point implements Observable {
    private static final Logger logger = LogManager.getLogger();

    protected ArrayList<Observer> observers = new ArrayList<>();
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
     * @param _point position du noeud
     */
    public Node(String _label, Point _point) {
        super(_point);
        this.label = _label;
        this.id = getMaxId();
        incrementMaxId();
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position, et un niveau de feu
     *
     * @param _label etiquette du noeud
     * @param _point position du noeud
     */
    public Node(String _label, Point _point, Integer fireLevel) {
        super(_point);
        this.label = _label;
        this.id = getMaxId();
        incrementMaxId();
        setFireLevel(fireLevel);
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     *
     */
    public Node() {
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     *
     * @param _id identifiant du noeud
     * @param _label etiquette du noeud
     * @param _point position du noeud
     */
    private Node(Long _id, String _label, Point _point) {
        super(_point);
        this.label = _label;
        this.id = _id;
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

    /**
     * @return l'unique ID du noeud
     */
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
    private void setIdString(String id) {
        this.setId(Long.parseLong(id));
    }

    public Integer getFireLevel() {
        return fireLevel;
    }

    private void setFireLevel(Integer fireLevel) {
        if (fireLevel < 0) {
            this.fireLevel = 0;
            logger.warn(String.format("The level of the fire can't be negative, has been set to 0."));
        } else
            this.fireLevel = fireLevel;

        notifyObserver();
    }
    private void setFireLevelString(String fireLevel) {
    	this.setFireLevel(Integer.parseInt(fireLevel));
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
    private void setXString(String _x)
    {
    	this.x=Integer.parseInt(_x);
    }
    private void setYString(String _y)
    {
    	this.x=Integer.parseInt(_y);
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
        Node node = new Node(id, label, (Point) super.clone());
        node.setFireLevel(fireLevel);
        node.setLinked(linked);
        return node;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        if(observers != null)
            observers.stream().filter(obs -> obs != null).forEach(view.Observer::Update);
    }
}