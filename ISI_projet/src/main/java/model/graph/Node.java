package model.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import model.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.Observer;

/**
 * Classe representant un noeud etiquete pour un graph
 */

public class Node extends Point implements Observable, Cloneable {

    /**
     * Logger de la classe
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * liste des ids de tous les noeuds existants
     */
    private static LinkedList<Long> ids = new LinkedList<>();
    /**
     * liste des observeurs du noeud
     */
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
     * Niveau de l'incendie
     */
    private Integer fireLevel = 0;

    /**
     * permet de savoir si le noeud est lié au graphe
     */
    private Boolean linked = Boolean.FALSE;

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
        this(_label, _point, 0);
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
        try {
            setIfUniqueId(getMaxId());
        } catch (IdAlreadyUsedException e) {
            e.printStackTrace();
        }
        setFireLevel(fireLevel);
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     */
    public Node() {
        this("", new Point(), 0);
    }

    /**
     * Construit un noeud avec une etiquette
     * et une position
     *
     * @param _id    identifiant du noeud
     * @param _label etiquette du noeud
     * @param _point position du noeud
     */
    private Node(Long _id, String _label, Point _point, Integer fireLevel, Boolean linked) {
        super(_point);
        this.label = _label;
        this.id = _id;
        this.fireLevel = fireLevel;
        this.linked = linked;
    }

    /**
     * Renvoi le prochain ID a utiliser pour la création d'un nouveau noeud
     *
     * @return ID
     */
    private synchronized static Long getMaxId() {
        ids.sort(Comparator.<Long>naturalOrder());
        return ids.isEmpty() ? 0 : ids.getLast() + 1;
    }

    public static void resetIds() {
        ids = new LinkedList<>();
    }

    public static void addId(Long id) {
        ids.add(id);
        ids.sort(Comparator.<Long>naturalOrder());
    }

    public Boolean isLinked() {
        return linked;
    }

    public void setLinked(Boolean linked) {
        this.linked = linked;
    }

    /**
     * @return l etiquette du noeud
     */
    public String getLabel() {
        return label;
    }

    /**
     * Specifie l etiquette du noeud
     *
     * @param _label
     */
    public void setLabel(String _label) {
        this.label = _label;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("label='").append(label).append('\'');
        sb.append(", id=").append(id);
        sb.append(", fireLevel=").append(fireLevel);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return l'unique ID du noeud
     */
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        if (!ids.contains(id)) addId(id);
        this.id = id;
    }

    public void setIdToNextAvailable() {
        setId(getMaxId());
    }

    /**
     * Permet de changer l'ID du noeud. L'operation ne sera effectuee que si l'id est disponible
     *
     * @param id nouvel id du noeud
     * @throws IdAlreadyUsedException l'id souhaite est déjà utlise
     */
    public void setIfUniqueId(Long id) throws IdAlreadyUsedException {
        if (ids.isEmpty()) ids.add(id);
        else {
            int idsSize = ids.size();
            if (id > ids.getLast()) {
                ids.addLast(id);
            } else
                for (int i = 0; i < idsSize; i++) {
                    if (id.equals(ids.get(i))) throw new IdAlreadyUsedException();
                    if (id < ids.get(i)) {
                        ids.add(i, id);
                        break;
                    }
                }
        }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass().equals(obj.getClass()))
            return false;
        Node other = (Node) obj;
        if (!id.equals(other.id))
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
        return new Node(id, label, super.clone(), fireLevel, linked);
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
        if (observers != null)
            observers.stream().filter(obs -> obs != null).forEach(view.Observer::update);
    }

    protected void setXString(String _x) {
        this.x = Integer.parseInt(_x);
    }

    protected void setYString(String _y) {
        this.y = Integer.parseInt(_y);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (observers != null ? observers.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (fireLevel != null ? fireLevel.hashCode() : 0);
        result = 31 * result + (linked != null ? linked.hashCode() : 0);
        return result;
    }
}