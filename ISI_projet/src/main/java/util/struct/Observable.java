package util.struct;

import util.struct.Observer;


/**
 * @author Corinne
 */

public interface Observable {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    // Dès raffraichissement.
    void notifyObserver();
}
