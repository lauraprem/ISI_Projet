package model;

import view.Observer;


/**
 * @author Corinne
 */

public interface Observable {
    public void addObserver(Observer o);

    public void removeObserver(Observer o);

    // Dès raffraichissement.
    public void notifyObserver();
}
