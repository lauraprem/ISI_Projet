package util.struct;

/**
 * @author Corinne
 */

public interface Observable {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    // Dès raffraichissement.
    void notifyObserver();
}
